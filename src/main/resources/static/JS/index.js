$(document).ready(function() {

    $('#formCreateAccount').on('submit', function(e) {
        if($('#password').val() !== $('#passwordConfirm').val()){
            e.preventDefault();
            alert('Contraseñas ingresadas no son idénticas! Ingrese de nuevo los datos.');
        }
    });

    // para cambiar en pantalla de iniciar sesión a crear cuenta en el mismo contendor(Pc)
    $('#registerButtonToggle').click(function() {
        $('#container').addClass('active');
    });
    $('#loginButtonToggle').click(function() {
        $('#container').removeClass('active');
    });

    // para cambiar en pantalla de iniciar sesión a crear cuenta en el mismo contendor(Teléfono)
    $('#buttonRegister').click(function() {
        $('.sign-in').css({
          opacity: 0,
          zIndex: 0,
        },600);
        $('.sign-up').css({
          opacity: 1,
          zIndex: 1,
        },600);
    });
    $('#buttonCreate').click(function() {
        $('.sign-up').css({
          opacity: 0,
          zIndex: 0,
        },600);
        $('.sign-in').css({
          opacity: 1,
          zIndex: 1,
        },600);
    });
});