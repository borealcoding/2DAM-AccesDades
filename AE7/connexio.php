<?php
    if (isset($_POST["dataPronostic"])) {
        // arreplegem les dades rebudes per POST
        $dataPronostic = $_POST["dataPronostic"];
        $poblacio = $_POST["poblacio"];
        $pais = $_POST["pais"];
        $descripcio = $_POST["descripcio"];
        $temperatura = $_POST["temperatura"];
        $temperaturaMax = $_POST["temperaturaMax"];
        $temperaturaMin = $_POST["temperaturaMin"];
        $humitat = $_POST["humitat"];
        $sensacioTermica = $_POST["sensacioTermica"];

        // creem la connexió amb la base de dades
        $servidor = "localhost";
        $usuario = "root";
        $password = "";
        $dbname = "openweather";
        $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

        // comprovem la connexió
        if (!$conexion) {
            echo "Error en la connexio a MySQL: " . mysqli_connect_error();
            exit();
        } // end-if

        // preparem la consulta INSERT per a inserir el pronostic
        $sql = "INSERT INTO pronosticsdiaris (dataPronostic, descripcio, poblacio, pais, temp, tempMax, tempMin, humitat, sensTerm) VALUES ('$dataPronostic', '$descripcio', '$poblacio', '$pais', '$temperatura', '$temperaturaMax', '$temperaturaMin', '$humitat', '$sensacioTermica')";

        // executem la consulta
        if (mysqli_query($conexion, $sql)) {
            echo "Registre insertat correctament.";
        } else {
            echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
        } // end-if
    } else {
        echo "No s'han rebut dades.";
    } // end-if
?>
