var userToSend={};
var gif={};
var userReceived={};

const formulario= document.getElementById("login-form");
formulario.addEventListener("submit", loginFunction);

function loginFunction(evento){

	evento.preventDefault();

	let userName= document.getElementById("userName").value;
	let password=document.getElementById("password").value;

	userToSend={userName:userName, password:password};


	fetch("http://localHost:8080/api/users/login",{

		method:"POST",
		headers:{
			"Content-Type":"application/json",
			"Accept": "application/json"
		},

		body:JSON.stringify(userToSend)

	})

	.then(function(response){
		if(response.ok){
			return response.json();
		}else{
			throw response;
		}
	})

	.then(function (r){
		userReceived=r;
		getGIF();

	})

	.catch(function(error){

		alert("No existe usuario: "+userName +" con la contraseña indicada");
	})

}

function getGIF(){


	fetch("http://localHost:8080/api/GIF",{

		method:"GET",
		headers:{
			"Content-Type":"application/json",
			"Accept":"application/json"
		}
	})

	.then(function(response){

		if(response.ok){
			return response.json();
		}else{
			throw response;
		}
	})

	.then(function(r){
		gif=r;
		showGIF(gif);
	})

	.catch(function(e){console.error(e)})
}

function storeUser(){
	//Muy importante guardar el User como JSON si no no va

	window.localStorage.setItem("activeUser",JSON.stringify(userReceived));
	console.log("Guardando al usuario en el localStorage");
}

function showGIF(gif){
	let containerForGIF=document.getElementById("contenido-dinamico");
	let containerHidden=document.getElementById("container-hidden");

	//Le quito el atributo hidden poniendole el style a vacio
	containerHidden.style="";

	containerForGIF.innerHTML="";
	containerForGIF.innerHTML=`<img src="${gif.contenido}" class="rounded mx-auto d-block">`

	//Para que vaya al GIF y no se quede arriba de la pagina 
	window.location.href="#GIF";

	//Provoco delay aposta para poder ver algo

	console.log("Inicia Espera...")
	window.setTimeout(function(){

		alert("Bienvenido: "+userReceived.userName);
		storeUser();
		window.location.href="home.html";
		console.log("Fin")
	}, 5000);



}

