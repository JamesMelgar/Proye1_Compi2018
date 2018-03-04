package ccss;
import java_cup.runtime.Symbol;
import proye1_compi2018.Nodo;

%%

%cupsym Simbolo
%class AnalizadorLexico_ccss
%cup
%public
%unicode
%public
%line
%column
%ignorecase


digito = [0-9]
letra = [a-zA-ZñÑ]
bool = (TRUE|FALSE)
id = {letra}+({letra}|{digito}|"_")*
cadena = "\"" ~"\""
numero = {digito}+("."{digito}+)?
comentario = "/""/" ~\n
comentario2 = "/""*" ~"*""/"


%{
//VARIALES Y METODOS DEL SCANER
public String Cerror="";
%}

%%	

<YYINITIAL>{
"+"        { return new Symbol(Simbolo.suma, yycolumn, yyline);}
"-"           { return new Symbol(Simbolo.resta, yycolumn, yyline);}
"*"           { return new Symbol(Simbolo.multiplicacion, yycolumn, yyline);}
"/"           { return new Symbol(Simbolo.division, yycolumn, yyline);}
"TEXTO"           { return new Symbol(Simbolo.texto, yycolumn, yyline);}
"LETRA"           { return new Symbol(Simbolo.letra, yycolumn, yyline);}
"TAMTEX"           { return new Symbol(Simbolo.tamtex, yycolumn, yyline);}
"FONDOELEMENTO"           { return new Symbol(Simbolo.fondoelemento, yycolumn, yyline);}
"AUTOREDIMENSION"           { return new Symbol(Simbolo.autoredimension, yycolumn, yyline);}
"["           { return new Symbol(Simbolo.cori, yycolumn, yyline);}
"]"           { return new Symbol(Simbolo.corf, yycolumn, yyline);}
"VISIBLE"           { return new Symbol(Simbolo.visible, yycolumn, yyline);}
"BORDE"           { return new Symbol(Simbolo.borde, yycolumn, yyline);}
"OPAQUE"           { return new Symbol(Simbolo.opaque, yycolumn, yyline);}
"COLORTEXT"           { return new Symbol(Simbolo.colortext, yycolumn, yyline);}
"GRUPO"           { return new Symbol(Simbolo.grupo, yycolumn, yyline);}
"ID"           { return new Symbol(Simbolo.ids, yycolumn, yyline);}
"("           { return new Symbol(Simbolo.pari, yycolumn, yyline);}
")"           { return new Symbol(Simbolo.parf, yycolumn, yyline);}
//":"           { return new Symbol(Simbolo.dpt, yycolumn, yyline);}
";"           { return new Symbol(Simbolo.pyc, yycolumn, yyline);}
":="           { return new Symbol(Simbolo.asig, yycolumn, yyline);}
","           { return new Symbol(Simbolo.coma, yycolumn, yyline);}
"HORIZONTAL"           { return new Symbol(Simbolo.horizontal, yycolumn, yyline);}
"VERTICAL"           { return new Symbol(Simbolo.vertical, yycolumn, yyline);}

//Alineado
"IZQUIERDA"           { return new Symbol(Simbolo.izquierda, yycolumn, yyline);}
"DERECHA"           { return new Symbol(Simbolo.derecha, yycolumn, yyline);}
"CENTRADO"           { return new Symbol(Simbolo.centrado, yycolumn, yyline);}
"JUSTIFICADO"           { return new Symbol(Simbolo.justificado, yycolumn, yyline);}
"ALINEADO"           { return new Symbol(Simbolo.alineado, yycolumn, yyline);}

//fomato
"NEGRILLA"           { return new Symbol(Simbolo.negrilla, yycolumn, yyline);}
"CURSIVA"           { return new Symbol(Simbolo.cursiva, yycolumn, yyline);}
"MAYUSCULA"           { return new Symbol(Simbolo.mayuscula, yycolumn, yyline);}
"MINUSCULA"           { return new Symbol(Simbolo.minuscula, yycolumn, yyline);}
"CAPITAL-T"           { return new Symbol(Simbolo.capital, yycolumn, yyline);}
"FORMATO"           { return new Symbol(Simbolo.formato, yycolumn, yyline);}

{bool}       { return new Symbol(Simbolo.bool,  yycolumn, yyline, yytext());}
{id}       { return new Symbol(Simbolo.id,  yycolumn, yyline, yytext());}
{cadena}       { return new Symbol(Simbolo.cadena,  yycolumn, yyline, yytext());}
{numero}		 { return new Symbol(Simbolo.numero,  yycolumn, yyline, yytext());}
{comentario} { }
{comentario2} { }
/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);
 Cerror = Cerror + "Error lexico: "+yytext() + " Linea: "+yyline +
		   " Columna: "+yycolumn + "T-archivo: CCSS"+"/n"; }

}