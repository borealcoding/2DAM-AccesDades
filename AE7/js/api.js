/*
 * @author Eduardo Ruá Chamorro | github.com/borealcoding
 * @version AE7.0 - 2022-01-18
 * @description OpenWeatherMap API
 * @copyright OpenWeatherMap.org
 */

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

/* Recollix la descripció (com a paràmetre) del pronòstic i la compara amb 
 * les possibles descripcions de la API per a mostrar la imatge corresponent.
 * @params {string} descripcio - Descripció del pronòstic.
 */
function mostrarIcona(descripcio) {
    if (descripcio.includes('clar') || descripcio.includes('net')) {
        document.getElementById('icoPronostic').src = "https://www.emoji.com/wp-content/uploads/filebase/thumbnails/3d%20icons/emoji-3d%20icons-glossy-3d-icons-sun-72dpi-forPersonalUseOnly.gif";
    } else if (descripcio.includes('núvol') || descripcio.includes('nuvols') || descripcio.includes('ennuvolat') || descripcio.includes('nublós') || descripcio.includes('nuvolositat')) {
        document.getElementById('icoPronostic').src = "https://www.emoji.com/wp-content/uploads/filebase/thumbnails/3d%20icons/emoji-3d%20icons-glossy-3d-icons-cloud-72dpi-forPersonalUseOnly.gif"
    } else if (descripcio.includes('pluja') || descripcio.includes('pluges')) {
        document.getElementById('icoPronostic').src = "https://www.emoji.com/wp-content/uploads/filebase/thumbnails/3d%20icons/emoji-3d%20icons-glossy-3d-icons-cloud-with-rain-72dpi-forPersonalUseOnly.gif"
    } else if (descripcio.includes('neu') || descripcio.includes('nevades') || descripcio.includes('nevada')) {
        document.getElementById('icoPronostic').src = "https://www.emoji.com/wp-content/uploads/filebase/thumbnails/3d%20icons/emoji-3d%20icons-glossy-3d-icons-cloud-with-snow-72dpi-forPersonalUseOnly.gif"
    } else if (descripcio.includes('boira') || descripcio.includes('boires')) {
        document.getElementById('icoPronostic').src = "https://monophy.com/media/kyuONYLMmhbcs1wtQi/monophy.gif"
    } else if (descripcio.includes('tempesta') || descripcio.includes('tempestes')) {
        document.getElementById('icoPronostic').src = "https://thumbs.gfycat.com/SimpleFeistyApisdorsatalaboriosa-size_restricted.gif"
    } else if (descripcio.includes('vent') || descripcio.includes('vents')) {
        document.getElementById('icoPronostic').src = "https://www.manosverdes.co/wp-content/uploads/2021/03/co2.gif"
    }
} // end mostrarIcona

/* Funció principal que treballarà amb l'API de OpenWeather fent ús d'Axios.
 * El seu funcionament es el següent:
    * - Obtenir la ciutat indicada al input i guardar-la en una variable
    * - Junt a l'API Key i la ciutat obtinguda, crear una URL amb la que obtindrem, des de un fitxer .json, les dades del pronòstic.
    * - Després de fer-ho, mostrar els elements del formulari amb les dades del pronòstic (també és cridarà a mostrarIcona per a mostrar la imatge corresponent).
 */
function getOratge() {
    var strCiutat = document.getElementById('ciutat').value;
    axios
    .get("https://api.openweathermap.org/data/2.5/weather?q="+strCiutat+"&appid=6e222653bf09c3ec2cb99d004e9119a1&lang=CA&units=metric")
    .then(response => {
        console.log(response.data);
        let dataPronostic = new Date().toISOString().substring(0, 19).replace('T', ' ');
        let poblacio = response.data.name;
        let pais = response.data.sys.country;
        let descripcio = response.data.weather[0].description;
        let temperatura = response.data.main.temp;
        let temperaturaMax = response.data.main.temp_max;
        let temperaturaMin = response.data.main.temp_min;
        let humitat = response.data.main.humidity;
        let sensacioTermica = response.data.main.feels_like;

        document.getElementById('ciutat').value = "";
        document.getElementById('poblacio').textContent = poblacio;
        document.getElementById('pais').textContent = pais;
        document.getElementById('descripcio').textContent = descripcio;
        document.getElementById('temperatura').textContent = temperatura.toFixed(2)+"ºC";
        document.getElementById('temperaturaMax').textContent = temperaturaMax.toFixed(2)+"ºC";
        document.getElementById('temperaturaMin').textContent = temperaturaMin.toFixed(2)+"ºC";
        document.getElementById('humitat').textContent = humitat+"%";
        document.getElementById('sensacioTermica').textContent = sensacioTermica.toFixed(2)+"ºC";
        document.getElementById('dataPronostic').textContent = dataPronostic;
        mostrarIcona(descripcio);
    })
    .catch(error => {
        console.error(error);
        alert("No s'ha pogut obtenir la informació de la ciutat");
    }); // end axios
} // end getOratge