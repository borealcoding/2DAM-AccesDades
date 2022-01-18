# 2 DAM - Accés a Dades
[![forthebadge](https://forthebadge.com/images/badges/made-with-javascript.svg)](https://forthebadge.com)

## AE7 - REST API

![openWeatherMapBackground](https://upload.wikimedia.org/wikipedia/commons/f/f6/OpenWeather-Logo.jpg)

Per a la realització d'aquest projecte, s'ha escollit utilitzar una API REST de [OpenWeatherMap](https://openweathermap.org/api), amb la que podrem obtenir informació sobre el temps a les diferents ciutats que volem.

La guia d'us oficial de la API la pots trobar en: [Call current weather data for one location](https://openweathermap.org/current)

**> Aquest programa implementa les següents funcionalitats:**

* **Part de client web (consultar la API):**
    * Connexió i consulta a la REST API elegida (o a diverses APIs).
    * Gestió dels documents obtinguts (preferiblement JSON).
    * Presentació dels resultats en una pàgina web d’una forma clara i ordenada.
<br/><br/>

* **Part de servidor i base de dades (guardar dades):**
    * Crea una base de dades MySQL amb els camps necessaris per a guardar la informació que manejaràs en les consultes.
    * Implementa un script PHP que permeta fer insercions a partir de paràmetres passats com a POST.
    * Implementa en el client web el codi Javascript necessari per a enviar al servidor les dades obtingudes en les consultes de la teua aplicació.
<br/><br/>

**> Observacions:**
* Hi han comentaris en cada secció del codi que expliquen el comportament dels procediments que s'executen
* Els fitxers JavaScript estàn separats i cadascún d'ells te la seua propia utilitat
    * [api.js](https://github.com/borealcoding/2DAM-AccesDades/blob/7374550fcc27ae13d47dffef2cfb4927cf63272b/AE7/js/api.js) Inclou tot el codi necessari per a poder treballar amb la API i mostrar els resultats obtinguts en la pàgina web
    * [ajaxGuardar.js](https://github.com/borealcoding/2DAM-AccesDades/blob/7374550fcc27ae13d47dffef2cfb4927cf63272b/AE7/js/ajaxGuardar.js) Te com a unica funcionalitat enviar al servidor les dades obtingudes, amb les que treballarem posteriorment al fitxer PHP
    * [animations.js](https://github.com/borealcoding/2DAM-AccesDades/blob/7374550fcc27ae13d47dffef2cfb4927cf63272b/AE7/js/animations.js) S'encarrega sols de aplicar animacions per a mostrar/ocultar les dades del pronòstic. (Més enfocat al CSS)

* S'inclou un [fitxer SQL](https://github.com/borealcoding/2DAM-AccesDades/blob/7374550fcc27ae13d47dffef2cfb4927cf63272b/AE7/db/openweather.sql) per a poder crear la base de dades necessària per a poder treballar amb els procediments de la API i arreplegar la informació obtinguda.

> S'aprecien enormement els suggeriments per a poder millorar el codi! :D

<br/>

**> Recursos**
* URL de la branca *Master*: https://github.com/borealcoding/2DAM-AccesDades
* URL de la branca *AE7*: https://github.com/borealcoding/2DAM-AccesDades/tree/AE7
