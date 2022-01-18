<?php
    var_dump($_POST);
    if (isset($_POST["dataPronostic"])) {
        $dataPronostic = $_POST["dataPronostic"];
        $poblacio = $_POST["poblacio"];
        $pais = $_POST["pais"];
        $descripcio = $_POST["descripcio"];
        $temperatura = $_POST["temperatura"];
        $temperaturaMax = $_POST["temperaturaMax"];
        $temperaturaMin = $_POST["temperaturaMin"];
        $humitat = $_POST["humitat"];
        $sensacioTermica = $_POST["sensacioTermica"];

        $servidor = "localhost";
        $usuario = "root";
        $password = "";
        $dbname = "openweather";
        $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

        if (!$conexion) {
            echo "Error en la conexion a MySQL: " . mysqli_connect_error();
            exit();
        } // end-if

        $sql = "INSERT INTO pronosticsdiaris (dataPronostic, descripcio, poblacio, pais, temp, tempMax, tempMin, humitat, sensTerm) VALUES ('$dataPronostic', '$descripcio', '$poblacio', '$pais', '$temperatura', '$temperaturaMax', '$temperaturaMin', '$humitat', '$sensacioTermica')";

        if (mysqli_query($conexion, $sql)) {
            echo "Registro insertado correctamente.";
        } else {
            echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
        } // end-if
    } else {
        echo "No s'han rebut dades.";
    } // end-if
?>
