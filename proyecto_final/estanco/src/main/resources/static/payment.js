let btn = document.getElementById("btn-finish");

btn.addEventListener("click",function(e){

    var tarjeta = {};
    var userPurchase = {};
    var userPurchaseRecived = {};

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

        tarjeta = {
            cc_name:cc_name,
            cc_number:cc_number,
            cc_cvv:cc_cvv
        }
    
        userPurchase = {
            firstName: firstName,
            lastName:lastName,
            email:email,
            address:address,
            country:country,
            zip:zip,
            tarjeta:tarjeta
        }

        debugger

        fetch("http://localHost:8080/api/savePurchase",
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
                alert("ok");
                return res.json();
            }else{
                throw res;
            }
        })
        
        .then(r=>{
            userPurchaseRecived = r;
        })

        alert("PURCHASE FINISHED YOU WILL RECIVE AN EMAIL AT : " + userPurchaseRecived.email);
    }

});