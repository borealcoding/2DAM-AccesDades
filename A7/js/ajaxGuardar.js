function guardar() {
    var dataPronostic = document.getElementById('dataPronostic').textContent;
    var poblacio = document.getElementById('poblacio').textContent;
    var pais = document.getElementById('pais').textContent;
    var descripcio = document.getElementById('descripcio').textContent;
    var temperatura = document.getElementById('temperatura').textContent;
    var temperaturaMax = document.getElementById('temperaturaMax').textContent;
    var temperaturaMin = document.getElementById('temperaturaMin').textContent;
    var humitat = document.getElementById('humitat').textContent;
    var sensacioTermica = document.getElementById('sensacioTermica').textContent;
    
    $.ajax({
            type: "POST", //metode POST per a enviar dades al servidor
            url: "../connexio.php", // ruta del fitxer PHP del servidor
            data: {
                dataPronostic: dataPronostic,
                poblacio: poblacio,
                pais: pais,
                descripcio: descripcio,
                temperatura: temperatura,
                temperaturaMax: temperaturaMax,
                temperaturaMin: temperaturaMin,
                humitat: humitat,
                sensacioTermica: sensacioTermica
            }, //dades que enviam a PHP
            success: function (response) { //resultat del PHP del servidor
            alert(response);
        },
            error: function () {
            alert("Error");
        }
    });
}