package achtml;
import java_cup.runtime.Symbol;
import proye1_compi2018.Nodo;

%%

%cupsym Simbolo
%class AnalizadorLexico_chtml
%cup
%public
%unicode
%public
%line
%column
%ignorecase
%state palabra

//\S
simbolo = ("@"|"?"|"#"|"."|"-"|"!"|"&"|":"|"*"|"+"|"="|","|"("|")")
todo = (\w|{simbolo})
//palabra = ((\w|{simbolo}|{digito})(\w|\s|{digito}|{simbolo})*)
cadena = "\"" ~"\""
path = c:"/""/"(\w|"/")+(".")(\w)+
comentario = "<""/""/""-"~"-""/""/"">"


%{
//VARIALES Y METODOS DEL SCANER
public String concatenar="";
public String Cerror="";
%}

%%	



//"" { return new Symbol(Simbolo., yycolumn, yyline);}


<YYINITIAL>"CHTML"           { return new Symbol(Simbolo.htmli, yycolumn, yyline);}
<YYINITIAL>"FIN-CHTML"        { return new Symbol(Simbolo.htmlf, yycolumn, yyline);}
<YYINITIAL>"ENCABEZADO"      { return new Symbol(Simbolo.encabezadoi, yycolumn, yyline);}
<YYINITIAL>"FIN-ENCABEZADO"  { return new Symbol(Simbolo.encabezadof, yycolumn, yyline);}
<YYINITIAL>"CUERPO"           { return new Symbol(Simbolo.cuerpoi, yycolumn, yyline);}
<YYINITIAL>"FIN-CUERPO"      { return new Symbol(Simbolo.cuerpof, yycolumn, yyline);}
<YYINITIAL>"<CJS"               { return new Symbol(Simbolo.cjsi, yycolumn, yyline); }
<YYINITIAL>"<CCSS"             { return new Symbol(Simbolo.ccssi, yycolumn, yyline);  }
<YYINITIAL>"RUTA"               { return new Symbol(Simbolo.ruta, yycolumn, yyline, yytext());  }
<YYINITIAL>"="                 { return new Symbol(Simbolo.igual, yycolumn, yyline);  }
<YYINITIAL>">"                 { return new Symbol(Simbolo.cierre, yycolumn, yyline);  }
<YYINITIAL>"<"                 { return new Symbol(Simbolo.abierto, yycolumn, yyline);  }
<YYINITIAL>"FIN-CJS"         { return new Symbol(Simbolo.cjsf, yycolumn, yyline);  }
<YYINITIAL>"FIN-CCSS"         { return new Symbol(Simbolo.ccssf, yycolumn, yyline);  }
<YYINITIAL>";"                 { return new Symbol(Simbolo.ptc, yycolumn, yyline);  }
<YYINITIAL>"fondo"         { return new Symbol(Simbolo.fondo, yycolumn, yyline);  }
<YYINITIAL>"<PANEL"         { return new Symbol(Simbolo.paneli, yycolumn, yyline);  }
<YYINITIAL>"FIN-PANEL"         { return new Symbol(Simbolo.panelf, yycolumn, yyline);  }
<YYINITIAL>"<IMAGEN"         { return new Symbol(Simbolo.imageni, yycolumn, yyline);  }
<YYINITIAL>"FIN-IMAGEN"         { return new Symbol(Simbolo.imagenf, yycolumn, yyline);  }
<YYINITIAL>"<OPCION"         { return new Symbol(Simbolo.opcioni, yycolumn, yyline);  }
<YYINITIAL>"FIN-OPCION"         { return new Symbol(Simbolo.opcionf, yycolumn, yyline);  }
<YYINITIAL>"<CAJA"         { return new Symbol(Simbolo.cajai, yycolumn, yyline);  }
<YYINITIAL>"FIN-CAJA"         { return new Symbol(Simbolo.cajaf, yycolumn, yyline);  }
<YYINITIAL>"<TEXTO"         { return new Symbol(Simbolo.textoi, yycolumn, yyline);  }
<YYINITIAL>"FIN-TEXTO"         { return new Symbol(Simbolo.textof, yycolumn, yyline);  }
<YYINITIAL>"<SPINNER"         { return new Symbol(Simbolo.spinneri, yycolumn, yyline);  }
<YYINITIAL>"FIN-SPINNER"         { return new Symbol(Simbolo.spinnerf, yycolumn, yyline);  }
<YYINITIAL>"<SALTO-FIN"         { return new Symbol(Simbolo.salto, yycolumn, yyline);  }
<YYINITIAL>"<TITUlO"         { return new Symbol(Simbolo.tituloi, yycolumn, yyline);  }
<YYINITIAL>"FIN-TITUlO"         { return new Symbol(Simbolo.titulof, yycolumn, yyline);  }
<YYINITIAL>"<BOTON"         { return new Symbol(Simbolo.botoni, yycolumn, yyline);  }
<YYINITIAL>"FIN-BOTON"         { return new Symbol(Simbolo.botonf, yycolumn, yyline);  }
<YYINITIAL>"<ENLACE"         { return new Symbol(Simbolo.enlacei, yycolumn, yyline);  }
<YYINITIAL>"FIN-ENLACE"         { return new Symbol(Simbolo.enlacef, yycolumn, yyline);  }
<YYINITIAL>"<TEXTO_A"         { return new Symbol(Simbolo.textoai, yycolumn, yyline);  }
<YYINITIAL>"FIN-TEXTO_A"         { return new Symbol(Simbolo.textoaf, yycolumn, yyline);  }
<YYINITIAL>"<CAJA_TEXTO"         { return new Symbol(Simbolo.cajati, yycolumn, yyline);  }
<YYINITIAL>"FIN-CAJA_TEXTO"         { return new Symbol(Simbolo.cajatf, yycolumn, yyline);  }

//tabla
<YYINITIAL>"<TABLA"         { return new Symbol(Simbolo.tablai, yycolumn, yyline);  }
<YYINITIAL>"FIN-TABLA"         { return new Symbol(Simbolo.tablaf, yycolumn, yyline);  }
<YYINITIAL>"<FIL_T"         { return new Symbol(Simbolo.fili, yycolumn, yyline);  }
<YYINITIAL>"FIN-FIL_T"         { return new Symbol(Simbolo.filf, yycolumn, yyline);  }
<YYINITIAL>"<CB"         { return new Symbol(Simbolo.cbi, yycolumn, yyline);  }
<YYINITIAL>"FIN-CB"         { return new Symbol(Simbolo.cbf, yycolumn, yyline);  }
<YYINITIAL>"<CT"         { return new Symbol(Simbolo.cti, yycolumn, yyline);  }
<YYINITIAL>"FIN-CT"         { return new Symbol(Simbolo.ctf, yycolumn, yyline);  }

//elementos
<YYINITIAL>"ID"         { return new Symbol(Simbolo.ids, yycolumn, yyline);  }
<YYINITIAL>"GRUPO"         { return new Symbol(Simbolo.grupo, yycolumn, yyline);  }
<YYINITIAL>"ALTO"         { return new Symbol(Simbolo.alto, yycolumn, yyline);  }
<YYINITIAL>"ANCHO"         { return new Symbol(Simbolo.ancho, yycolumn, yyline);  }
<YYINITIAL>"ALINEADO"         { return new Symbol(Simbolo.alineado, yycolumn, yyline);  }
<YYINITIAL>"CCSS"         { return new Symbol(Simbolo.ccss, yycolumn, yyline);  }
<YYINITIAL>"CLICK"         { return new Symbol(Simbolo.click, yycolumn, yyline);  }
<YYINITIAL>"VALOR"         { return new Symbol(Simbolo.valor, yycolumn, yyline);  }

<YYINITIAL>{cadena}       { return new Symbol(Simbolo.cadena,  yycolumn, yyline, yytext());}
//{titulo}       { return new Symbol(Simbolo.titulo,  yycolumn, yyline, yytext());}
<YYINITIAL>{path}       { return new Symbol(Simbolo.path,  yycolumn, yyline, yytext());}
//<YYINITIAL>{palabra}       { return new Symbol(Simbolo.palabra,  yycolumn, yyline, yytext());}
<YYINITIAL>{comentario} {}

<YYINITIAL>{todo}       { concatenar = yytext();
						 yybegin(palabra);}

<palabra> [\n] { concatenar = concatenar + "\n"; }
<palabra> [^"<"] { concatenar = concatenar + yytext(); }
<palabra> ["<"] {   yybegin(YYINITIAL);
					return new Symbol(Simbolo.palabra,  yycolumn, yyline, concatenar); 

				 }

/* BLANCOS */
<YYINITIAL>[ \t\r\f\n]+        { /* Se ignoran */}
 

/* ERRORES LEXICOS */
.      { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);
		  Cerror = Cerror + "Error lexico: "+yytext() + " Linea: "+yyline +
		   " Columna: "+yycolumn + "T-archivo: CHTML"+"/n"; }


