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
numero = ("-")?{digito}+("." {digito}+)?
cadena = "\"" ~"\""
titulo = "<TITULO>" ~"<FIN-TITULO>"
texto = "<TEXTO>" ~"<FIN-TEXTO>"

%{
//VARIALES Y METODOS DEL SCANER

%}

%%	

<YYINITIAL>{

//"" { return new Symbol(Simbolo., yycolumn, yyline);}



"<CHTML>"           { return new Symbol(Simbolo.htmli, yycolumn, yyline);}
"<FIN-CHTML>"        { return new Symbol(Simbolo.htmlf, yycolumn, yyline);}
"<ENCABEZADO>"      { return new Symbol(Simbolo.encabezadoi, yycolumn, yyline);}
"<FIN-ENCABEZADO>"  { return new Symbol(Simbolo.encabezadof, yycolumn, yyline);}
"<CUERPO"           { return new Symbol(Simbolo.cuerpoi, yycolumn, yyline);}
"<FIN-CUERPO>"      { return new Symbol(Simbolo.cuerpof, yycolumn, yyline);}
"<CJS"               { return new Symbol(Simbolo.cjsi, yycolumn, yyline); }
"<CCSS"             { return new Symbol(Simbolo.ccssf, yycolumn, yyline);  }
"RUTA"               { return new Symbol(Simbolo.ruta, yycolumn, yyline);  }
"="                 { return new Symbol(Simbolo.igual, yycolumn, yyline);  }
">"                 { return new Symbol(Simbolo.cierre, yycolumn, yyline);  }
"<FIN-CJS>"         { return new Symbol(Simbolo.cjsf, yycolumn, yyline);  }
"<FIN-CCSS>"         { return new Symbol(Simbolo.ccssf, yycolumn, yyline);  }
";"                 { return new Symbol(Simbolo.ptc, yycolumn, yyline);  }
"fondo"         { return new Symbol(Simbolo.fondo, yycolumn, yyline);  }


{numero}       	{ return new Symbol(Simbolo.numero,  yycolumn, yyline, yytext());}
{cadena}       { return new Symbol(Simbolo.cadena,  yycolumn, yyline, yytext());}
{titulo}       { return new Symbol(Simbolo.titulo,  yycolumn, yyline, yytext());}
{texto}       { return new Symbol(Simbolo.texto,  yycolumn, yyline, yytext());}


/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);}

}

