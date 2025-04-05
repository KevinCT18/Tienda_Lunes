/* la siguiente función carga localmente una imagen en una página html*/
function readURL(input) {
    if (input.files && input.files[0]) {
        //Si lo que nos pasaron es un archivo
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result).height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// Esta función agrega al carrito un producto seleccionado en la página principal
function addCart(formulario) {
    //window.alert("está ingresando...");
    var idProducto = formulario.elements[0].value;
    var existencias = formulario.elements[1].value;
    if (existencias > 0) {
        var ruta = "/carrito/agregar/" + idProducto;
        $("#resultBlock").load(ruta);
    } else {
        window.alert("No hay existencias...");
    }
}
