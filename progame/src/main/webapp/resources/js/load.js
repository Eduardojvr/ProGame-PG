function abreLoad(modalName){

        $("#"+modalName).modal("show");

}

function fechaLoad(modalName){
    $(document).ready(function() {
        $("#"+modalName).modal("hide");
    });
}