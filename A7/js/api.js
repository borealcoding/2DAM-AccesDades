function missatge() {
    alert("Botó polsat");
} // end missatge

function getOratge() {
    var strCiutat = document.getElementById('ciutat').value;
    axios
    .get("https://api.openweathermap.org/data/2.5/weather?q="+strCiutat+"&appid=6e222653bf09c3ec2cb99d004e9119a1")
    .then(response => {
        console.log(response.data);
        let dataPronostic = new Date().toISOString().substring(0, 19).replace('T', ' ');
        let poblacio = response.data.name;
        let pais = response.data.sys.country;
        let descripcio = response.data.weather[0].description;
        let temperatura = response.data.main.temp - 273.15;
        let temperaturaMax = response.data.main.temp_max - 273.15;
        let temperaturaMin = response.data.main.temp_min - 273.15;
        let humitat = response.data.main.humidity;
        let sensacioTermica = response.data.main.feels_like - 273.15;

        document.getElementById('ciutat').value = "";
        document.getElementById('poblacio').textContent = poblacio;
        document.getElementById('pais').textContent = pais;
        document.getElementById('descripcio').textContent = descripcio;
        document.getElementById('temperatura').textContent = temperatura.toFixed(2)+"ºC";
        document.getElementById('temperaturaMax').textContent = temperaturaMax.toFixed(2)+"ºC";
        document.getElementById('temperaturaMin').textContent = temperaturaMin.toFixed(2)+"ºC";
        document.getElementById('humitat').textContent = humitat+"%";
        document.getElementById('sensacioTermica').textContent = sensacioTermica.toFixed(2)+"ºC";
        document.getElementById('icoPronostic').style.display = "block";
        document.getElementById('dataPronostic').textContent = dataPronostic;

        if (descripcio.includes('clear') || descripcio.includes('sunny')) {
            document.getElementById('icoPronostic').src = "https://www.iconninja.com/files/115/757/114/temperature-sunny-sun-weather-icon.png";
        } else if (descripcio.includes('cloud') || descripcio.includes('clouds')) {
            document.getElementById('icoPronostic').src = "https://www.iconninja.com/files/14/149/208/cloud-clouds-cloudy-weather-icon.png"
        } else if (descripcio.includes('rain') || descripcio.includes('rains')) {
            document.getElementById('icoPronostic').src = "https://www.iconninja.com/files/660/686/470/rain-weather-shower-storm-icon.png"
        } else if (descripcio.includes('snow') || descripcio.includes('snowy')) {
            document.getElementById('icoPronostic').src = "https://www.iconninja.com/files/227/840/936/clouds-winter-weather-snow-icon.png"
        } 
    })
    .catch(error => {
        console.error(error);
        alert("No s'ha pogut obtenir la informació de la ciutat");
        // document.getElementById('error').textContent = "ERROR EN LA CONSULTA: " + error;
    });
} // end getOratge