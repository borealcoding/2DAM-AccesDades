window.onload = function() {
    var btnEnviar = document.getElementById('saludar');
    btnEnviar.onclick = missatge;
    var btnConsultar = document.getElementById('consultar');
    btnConsultar.onclick = getWeatherCity;
}

function missatge() {
    alert("Botó polsat");
}

// https://api.openweathermap.org/data/2.5/weather?q=valencia&appid=6e222653bf09c3ec2cb99d004e9119a1

function getWeatherCity() {
    var strCiutat = document.getElementById('ciutat').value;
    axios
    .get("https://api.openweathermap.org/data/2.5/weather?q="+strCiutat+"&appid=6e222653bf09c3ec2cb99d004e9119a1")
    .then(response => {
        console.log(response.data);
        let temperatura = response.data.main.temp - 273.15;
        document.getElementById('temperatura').value = temperatura.toFixed(2)+"ºC";
    })
    .catch(error => {
        console.error(error);
        document.getElementById('temperatura').value = "ERROR EN LA CONSULTA: " + error;
    });
}