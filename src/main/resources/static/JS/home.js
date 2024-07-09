$(document).ready(function () {
    const balanceSpan = $("#balanceMenu");
    let realBalance = balanceSpan.text();

    $('#hideButton').on('click', function() {
        if (balanceSpan.text() === "*****") {
            balanceSpan.text(realBalance);
        } else {
            realBalance = balanceSpan.text();
            balanceSpan.text("*****");
        }
    });

    // MENU
    const list = document.querySelectorAll('.list');
    function activeLink() {
        list.forEach((item) => item.classList.remove('active'));
        this.classList.add('active');
    }
    list.forEach((item) => item.addEventListener('click', activeLink));
});