

//JSON del searchModel de tabaco industrial y de liar que se inicializaran en los fetch

var industrialSearchModel={};
var liarSearchModel={};

//Variables globales para saber que lista de articulos estamos tratando, el articulo seleccionado y el tipo de tabaco
var articleSelected={};
var listArticlesSelected={};
var tipoDeTabaco;

//Array de JSONs de lineas de compra
var arrayTabacos = [];
var arrayLineas=[];

const modalArticle = new bootstrap.Modal(document.getElementById('articleModal'), {
		keyboard: false
});

const modalCarrito = new bootstrap.Modal(document.getElementById('carritoModal'), {
		keyboard: false
});

//Inicializmos tabacos
loadTabacoIndustrial();
loadTabacoLiar();



//Ahora añado un eventListener a window que se activa cada vez que cambia la # de la url

window.addEventListener("hashchange",function(){

	let hashValue= window.location.hash;

	if(hashValue=="#tabacoLiar"){
		formGrid("tabacoLiar");
	}

	if(hashValue=="#tabacoIndustrial"){
		formGrid("tabacoIndustrial");
	}

});


function loadTabacoIndustrial(){


	fetch("http://localHost:8080/api/tabacoIndustrial",{

		method:"GET",
		headers:{
			"Content-Type":"application/json",
			"Accept": "application/json"
		}

	})

	.then(function(response){
		if(response.ok){

			return response.json();
		}else{
			throw response;
		}
	})

	.then(function (r){

		//Ya tengo el tabaco industrial y formo el grid
		industrialSearchModel=r;
		formGrid("tabacoIndustrial");

	})

	.catch(error=>console.error(error))
}

function loadTabacoLiar(){


	fetch("http://localHost:8080/api/tabacoLiar",{

		method:"GET",
		headers:{
			"Content-Type":"application/json",
			"Accept": "application/json"
		}

	})

	.then(function(response){
		if(response.ok){

			return response.json();
		}else{
			throw response;
		}
	})

	.then(function (r){

		liarSearchModel=r;

	})

	.catch(error=>console.error(error))
}

function formGrid(type){
	
	//En funcion de como sea type inicializo el valor de la lista de articulos a mostrar

	let html="";
	let counter=null;

	if(type=="tabacoIndustrial"){
		listArticlesSelected = industrialSearchModel.items;
		tipoDeTabaco="industrial";
		counter= industrialSearchModel.count;
	}else{
		tipoDeTabaco="liar";
		listArticlesSelected=liarSearchModel.items;
		counter=liarSearchModel.count;
	}
		

	//La unica variable que no es igual entre tabaco industrial y liar es cigarrillos vs gramos
	//Asi que eso lo guardo en una variable a parte

	for (let i=0; i<counter; i++){
		//creacion de las cards

		let article= listArticlesSelected[i];
		let aux=null;
		

		if(type=="tabacoIndustrial"){
			aux=article.cigarrillos;
		}else{
			aux=article.gramos;
		}
			

		html+= '<div class="col">'+
		 '<div class="card m-auto my-5" style="width: 20rem">'+
		 `<img class="card-img-top" src="${article.imagen}" height="400" alt="Card image cap">`+
		 '<div class="card-body">'+
		 `<h5 class="card-title">${article.marca}</h5>`+
		 `<p class="card-text">${article.descripcion}</p>`+
		 `<p class="card-text">Precio: ${article.precio}</p>`+
		 `<p class="card-text">Cantidad: ${aux}</p>`+
		 `<button class="btn btn-primary button-card" id="btn${i}" type="button">Comprar</button>`+
		 '</div>'+
		 '</div>'+
		 '</div>';

	}

	const myGrid= document.getElementById("tabaco-grid");
	myGrid.innerHTML="";
	myGrid.innerHTML=html;
	debugger

	//Una vez hemos creado el contenido html dinamico recorremos los cards asignando eventListeners
	cardsEvents();
	cartEvent();

}

//PARA CALCULAR EL TOTAL
var totalAm = 0;
var anterior = 0;

function cartEvent(){

	//Evento de pulsar el boton del carrito
	let add = document.getElementById("carritoButton");
	add.addEventListener("click",function(e){

		//LOGICA PARA MOSTRAR PRODUCTOS
		while(anterior < arrayTabacos.length){

			//SACO INFO DEL PRODUCTO
			let marca = arrayTabacos[anterior].marca;
			console.log(marca);
			let image = arrayTabacos[anterior].imagen;
			let tipo = arrayTabacos[anterior].tipo;
			console.log(tipo);
			let uds = arrayTabacos[anterior].cantidad;
			console.log(uds);
			let precio = arrayTabacos[anterior].precio;
			console.log(precio);
			let total = precio*uds;
			console.log(total);

			totalAm = totalAm + total;

			//SELECCIONO DIVS DONDE VOY A AÑADIR INFO
			const colProd = document.getElementById("idProductos");
			const colUds = document.getElementById("uds");
			const colTot = document.getElementById("precioProducto");

			//CREO DIV MARCA y AÑADO AL DIV
			const divMarca = document.createElement("div");
			divMarca.id = `divMarca${anterior}`;
			divMarca.className = "row";
			const pMarca = document.createElement("p");
			pMarca.textContent = marca + ": " + tipo;

			divMarca.appendChild(pMarca);
			colProd.appendChild(divMarca);

			//CREO DIV UNIDADES y AÑADO AL DIV
			const divUds = document.createElement("div");
			divUds.id = `divUds${anterior}`;
			const pUds = document.createElement("p");
			pUds.textContent = uds + " uds.";

			divUds.appendChild(pUds);
			colUds.appendChild(divUds);
			
			//CREO DIV TOTAL y AÑADO AL DIV
			const divTot = document.createElement("div");
			divTot.id = `divTot${anterior}`;
			const pTot = document.createElement("p");
			pTot.textContent = total + " $";

			divTot.appendChild(pTot);
			colTot.appendChild(divTot);		

			anterior++;
		}	


		document.getElementById("total-amount").innerHTML = totalAm + " $";

		modalCarrito.show();

		
	})
}


function cardsEvents(){


	//Nos devuelve un vector con todos los buttons de los cards
	let cards = document.getElementsByClassName("button-card");

	//Añadimos un click listener a cada button de cada card
	for(let i = 0; i<cards.length;i++){

		cards[i].addEventListener("click",function(e){

			//Lo primero distinguimos en que card han dado click
			let evento = e.currentTarget.id;

			//Del id cogemos el numero
			let id=parseInt(evento.charAt(3));

			//Una vez sabemos que id tiene el boton nos guardamos la cajeta correspondiente
			articleSelected = listArticlesSelected[id];
			
			document.getElementById("marca-tabaco").innerHTML = articleSelected.marca;
			document.getElementById("precio-tabaco").innerHTML = articleSelected.precio;
			document.getElementById("unidades").innerHTML = "0"; //Si no se queda con el ultimo valor que tuviese

			modalArticle.show();

		})
	}
}


//MODAL DE UN ARTICLE
//===================

var buttonAddToChart = document.getElementById("buttonAdd");

//Click en addChart
buttonAddToChart.addEventListener("click",function(e){

let uds = document.getElementById("unidades").value;	


if(uds>0){

	//Por una parte relleno los articulos para mostrarlos y por otra parte las lineas de compra para hacer el post al back
	let lineaCompra = {};
	let newArticle={};

	newArticle={
		cantidad:uds,
		marca:articleSelected.marca,
		imagen:articleSelected.imagen,
		tipo:tipoDeTabaco,
		precio:articleSelected.precio
	}

	lineaCompra = {
		units:uds,
		codigo:articleSelected.codigo,
		tipoDeTabaco:tipoDeTabaco
	}
	
	arrayTabacos.push(newArticle);
	arrayLineas.push(lineaCompra);
}else{
	alert("NO HA SELECCIONADO NINGUNA CANTIDAD");
}

modalArticle.hide();

})


//MODAL DEL CARRITO
//=================

//Pulsa el boton cancel
var cancel = document.getElementById("cancel");
cancel.addEventListener("click",function(e){

	//Eliminamos array de tabacos creado
	arrayTabacos.splice(0);


	//Eliminamos contenido dinamico del carrito
	const colProd = document.getElementById("idProductos");
	colProd.innerHTML="";

	const colUds = document.getElementById("uds");
	colUds.innerHTML="";

	const colTot = document.getElementById("precioProducto");
	colTot.innerHTML="";

	//Reseteo totalAm tambien

	totalAm=0;

})

//Pulsa el boton finish
var finish = document.getElementById("finish");
finish.addEventListener("click",function(e){

	if(arrayTabacos.length > 0){
		
		var userPurchase = {};

		var user = localStorage.getItem("activeUser");

		userPurchase = {
			nameUser:user.userName,
			lineasCompra:arrayLineas
		}
	 
		window.location.replace("payment.html");
	}else{
		alert("NO HAY PRODUCTOS PARA COMPRAR SELECCIONADOS");
	}
})






