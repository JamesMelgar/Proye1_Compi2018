Titular_ccss [
	ID (panel1);
		Formato := mayuscula, NEGRILLA, capital-t;
		Letra := "Arial";
		Tamtex := 12;
		ALINEADO := CENTRADO;
		Fondoelemento := "green";
		BORDE := [15, "red", TRUE];
		Opaque := false;
		AutoRedimension := [false , HORIZONTAL];

	GRUPO (texto1);
		Alineado := Derecha;
		letra := "Monospaced";
		Tamtex := 11;
		Formato :=  NEGRILLA, capital-t;
		Fondoelemento := "BLUE";
		visible := TRUE;
		BORDE := [3, "red", TRUE];
		Opaque := true;
		colorTExt := "green";

	ID(button1);
		ALINEADO := CENTRADO;
		Texto := "Eliminar";
		Formato :=  mayuscula, NEGRILLA, capital-t;
		letra := "Monospaced";
		Tamtex := 12;
		colorTExt := "yellow";
		Fondoelemento := "red";
		visible := true;
		BORDE := [1, "BLACK",FALSE];
		Opaque := false;

]
//Fin identificador_1
caja_de_texto [
	ID (caja);
		Texto := "COMPI2";
		ALINEADO := CENTRADO;
		Formato := NEGRILLA;
		Letra := "Arial";
		Tamtex := 18;
		colorTExt := "yellow";
		Fondoelemento := "BLACK";
		BORDE := [1, "GReen",FALSE];
]