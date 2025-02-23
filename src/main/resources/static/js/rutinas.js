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

