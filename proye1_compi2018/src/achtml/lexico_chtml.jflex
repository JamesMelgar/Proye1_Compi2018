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

digito = [0-9]
palabra = ((\w|-|@|{digito})(\w|-|\s|{digito}|".")*)
cadena = "\"" ~"\""
STRING = ({digito}|[0-9])
//titulo = "<TITULO" ~"<FIN-TITULO"
path = c:"/""/"(\w|"/")+(".")(\w)+

%{
//VARIALES Y METODOS DEL SCANER

%}

%%	

<YYINITIAL>{

//"" { return new Symbol(Simbolo., yycolumn, yyline);}



"<CHTML"           { return new Symbol(Simbolo.htmli, yycolumn, yyline);}
"FIN-CHTML"        { return new Symbol(Simbolo.htmlf, yycolumn, yyline);}
"<ENCABEZADO"      { return new Symbol(Simbolo.encabezadoi, yycolumn, yyline);}
"FIN-ENCABEZADO"  { return new Symbol(Simbolo.encabezadof, yycolumn, yyline);}
"<CUERPO"           { return new Symbol(Simbolo.cuerpoi, yycolumn, yyline);}
"FIN-CUERPO"      { return new Symbol(Simbolo.cuerpof, yycolumn, yyline);}
"<CJS"               { return new Symbol(Simbolo.cjsi, yycolumn, yyline); }
"<CCSS"             { return new Symbol(Simbolo.ccssf, yycolumn, yyline);  }
<YYINITIAL> "RUTA"               { return new Symbol(Simbolo.ruta, yycolumn, yyline, yytext());  }
"="                 { return new Symbol(Simbolo.igual, yycolumn, yyline);  }
">"                 { return new Symbol(Simbolo.cierre, yycolumn, yyline);  }
"<"                 { return new Symbol(Simbolo.abierto, yycolumn, yyline);  }
"FIN-CJS"         { return new Symbol(Simbolo.cjsf, yycolumn, yyline);  }
"FIN-CCSS"         { return new Symbol(Simbolo.ccssf, yycolumn, yyline);  }
";"                 { return new Symbol(Simbolo.ptc, yycolumn, yyline);  }
"fondo"         { return new Symbol(Simbolo.fondo, yycolumn, yyline);  }
"<PANEL"         { return new Symbol(Simbolo.paneli, yycolumn, yyline);  }
"FIN-PANEL"         { return new Symbol(Simbolo.panelf, yycolumn, yyline);  }
"<IMAGEN"         { return new Symbol(Simbolo.imageni, yycolumn, yyline);  }
"FIN-IMAGEN"         { return new Symbol(Simbolo.imagenf, yycolumn, yyline);  }
"<OPCION"         { return new Symbol(Simbolo.opcioni, yycolumn, yyline);  }
"FIN-OPCION"         { return new Symbol(Simbolo.opcionf, yycolumn, yyline);  }
"<CAJA"         { return new Symbol(Simbolo.cajai, yycolumn, yyline);  }
"FIN-CAJA"         { return new Symbol(Simbolo.cajaf, yycolumn, yyline);  }
"<TEXTO"         { return new Symbol(Simbolo.textoi, yycolumn, yyline);  }
"FIN-TEXTO"         { return new Symbol(Simbolo.textof, yycolumn, yyline);  }
"<SPINNER"         { return new Symbol(Simbolo.spinneri, yycolumn, yyline);  }
"FIN-SPINNER"         { return new Symbol(Simbolo.spinnerf, yycolumn, yyline);  }
"<SALTO-FIN"         { return new Symbol(Simbolo.salto, yycolumn, yyline);  }
"<TITUlO"         { return new Symbol(Simbolo.tituloi, yycolumn, yyline);  }
"FIN-TITUlO"         { return new Symbol(Simbolo.titulof, yycolumn, yyline);  }
"<BOTON"         { return new Symbol(Simbolo.botoni, yycolumn, yyline);  }
"FIN-BOTON"         { return new Symbol(Simbolo.botonf, yycolumn, yyline);  }
"<ENLACE"         { return new Symbol(Simbolo.enlacei, yycolumn, yyline);  }
"FIN-ENLACE"         { return new Symbol(Simbolo.enlacef, yycolumn, yyline);  }
"<TEXTO_A"         { return new Symbol(Simbolo.textoai, yycolumn, yyline);  }
"FIN-TEXTO_A"         { return new Symbol(Simbolo.textoaf, yycolumn, yyline);  }
"<CAJA_TEXTO"         { return new Symbol(Simbolo.cajati, yycolumn, yyline);  }
"FIN-CAJA_TEXTO"         { return new Symbol(Simbolo.cajatf, yycolumn, yyline);  }

//tabla
"<TABLA"         { return new Symbol(Simbolo.tablai, yycolumn, yyline);  }
"FIN-TABLA"         { return new Symbol(Simbolo.tablaf, yycolumn, yyline);  }
"<FIL_T"         { return new Symbol(Simbolo.fili, yycolumn, yyline);  }
"FIN-FIL_T"         { return new Symbol(Simbolo.filf, yycolumn, yyline);  }
"<CB"         { return new Symbol(Simbolo.cbi, yycolumn, yyline);  }
"FIN-CB"         { return new Symbol(Simbolo.cbf, yycolumn, yyline);  }
"<CT"         { return new Symbol(Simbolo.cti, yycolumn, yyline);  }
"FIN-CT"         { return new Symbol(Simbolo.ctf, yycolumn, yyline);  }

//elementos
"ID"         { return new Symbol(Simbolo.ids, yycolumn, yyline);  }
"GRUPO"         { return new Symbol(Simbolo.grupo, yycolumn, yyline);  }
"ALTO"         { return new Symbol(Simbolo.alto, yycolumn, yyline);  }
"ANCHO"         { return new Symbol(Simbolo.ancho, yycolumn, yyline);  }
"ALINEADO"         { return new Symbol(Simbolo.alineado, yycolumn, yyline);  }
"CCSS"         { return new Symbol(Simbolo.ccss, yycolumn, yyline);  }
"CLICK"         { return new Symbol(Simbolo.click, yycolumn, yyline);  }
"VALOR"         { return new Symbol(Simbolo.valor, yycolumn, yyline);  }


{cadena}       { return new Symbol(Simbolo.cadena,  yycolumn, yyline, yytext());}
//{titulo}       { return new Symbol(Simbolo.titulo,  yycolumn, yyline, yytext());}
{path}       { return new Symbol(Simbolo.path,  yycolumn, yyline, yytext());}
{palabra}       { return new Symbol(Simbolo.palabra,  yycolumn, yyline, yytext());}

/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);}

}

