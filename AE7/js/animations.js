/* S'ocupa de mostrar el formulari amb les dades del pronòstic.
 * El seu funcionament consta en mostrar els elements amb una sutil animació.*/
function lliscarAvall() {
    var infoPronostic = document.getElementById('infoPronostic');
    infoPronostic.style.display = "flex";
    infoPronostic.style.transition = "all 0.5s ease-in-out";
    infoPronostic.style.height = "250px";
    document.getElementById('inputPoblacio').style.display = "none";
    document.getElementById('guardar').style.display = "block";
    document.getElementById('icoPronostic').style.display = "block";
    document.getElementById('infoData').style.display = "block";
    document.getElementById('tornar').style.display = "flex";
} // end lliscarAvall

/* En diferència de lliscarAvall, aquesta funció executa una animació per a
 * ocultar els camps amb les dades del pronòstic.*/
function lliscarAmunt() {
    var infoPronostic = document.getElementById('infoPronostic');
    infoPronostic.style.transition = "all 0.5s ease-in-out";
    infoPronostic.style.height = "0px";
    document.getElementById('inputPoblacio').style.display = "block";
    document.getElementById('guardar').style.display = "none";
    document.getElementById('icoPronostic').style.display = "none";
    document.getElementById('infoData').style.display = "none";
    document.getElementById('tornar').style.display = "none";
} // end lliscarAmunt