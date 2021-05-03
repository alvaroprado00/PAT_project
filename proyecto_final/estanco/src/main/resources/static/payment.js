let btn = document.getElementById("btn-finish");

btn.addEventListener("click",function(e){

    var tarjeta = {};
    var userPurchase = {};

    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var address = document.getElementById("address").value;
    var country = document.getElementById("country").value;
    var zip = document.getElementById("zip").value;
    var cc_name = document.getElementById("cc-name").value;
    var cc_number = document.getElementById("cc-number").value;
    var cc_cvv = document.getElementById("cc-cvv").value;

    if(firstName == "" || lastName == "" || email == "" || address == "" || country == "" || zip == "" || cc_name == "" || cc_number == "" || cc_cvv == ""){

        alert("FILL ALL THE BLANKS");

    }else{

        var userLineas = JSON.parse(localStorage.getItem("userLineas"));
        var userName = JSON.parse(localStorage.getItem("activeUser"));

        debugger        

        tarjeta = {
            name:cc_name,
            number:cc_number,
            cvv:cc_cvv
        }
    
        userPurchase = {
            firstName: firstName,
            lastName:lastName,
            email:email,
            address:address,
            country:country,
            zip:zip,
            userName:userName.userName,
            lineasCompra:userLineas
        }

        debugger

        fetch("http://localHost:8080/api/user/purchase",
            {
                method:"POST",
                headers:{
                        "Content-Type":"application/json",
                    "Accept": "application/json"
                },
                body:JSON.stringify(userPurchase)
        })

        .then(res=>{
            if(res.ok){
                debugger
                return res.json();
            }else{
                throw res;
            }
        })
        
        .then(r=>{
            debugger
            alert("PURCHASE FINISHED YOU WILL RECIVE AN EMAIL AT : " + userPurchase.email);
            window.location.replace("home.html");
        })

        .catch(e =>{
            console.log(e);
        })
    }

});