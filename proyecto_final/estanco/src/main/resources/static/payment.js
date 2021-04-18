
var btn = document.getElementById("btn-finish");

const modalFinish = new bootstrap.Modal(document.getElementById('finishModal'),{
    keyboard: false
});

btn.addEventListener("click",function(e){

    modalFinish.show();

})