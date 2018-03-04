package cjs;
import java_cup.runtime.Symbol;
import proye1_compi2018.Nodo;

%%

%cupsym Simbolo
%class AnalizadorLexico_cjs
%cup
%public
%unicode
%public
%line
%column
%ignorecase


digito = [0-9]
letra = [a-zA-ZñÑ]
id = {letra}+({letra}|{digito}|"_")*
cadena = "\"" ~"\""
truei = "'"TRUE"'"
falsei = "'"FALSE"'"
bool = ({truei}|{falsei})
fecha = [0-9][0-9]?"/"[0-9][0-9]"/"[0-9][0-9][0-9][0-9]
date = "'"{fecha}"'"
hora = {digito}{digito}":"{digito}{digito}":"{digito}{digito}
datetime ="'"{fecha}" "{hora}"'"
numero = {digito}+("."{digito}+)?

%{
//VARIALES Y METODOS DEL SCANER

%}

%%	

<YYINITIAL>{
"DIMV"        { return new Symbol(Simbolo.dimv, yycolumn, yyline);}
":"           { return new Symbol(Simbolo.dpt, yycolumn, yyline);}
"{"           { return new Symbol(Simbolo.lli, yycolumn, yyline);}
"}"           { return new Symbol(Simbolo.llf, yycolumn, yyline);}
","           { return new Symbol(Simbolo.coma, yycolumn, yyline);}
";"           { return new Symbol(Simbolo.pyc, yycolumn, yyline);}
"."           { return new Symbol(Simbolo.pt, yycolumn, yyline);}
"("           { return new Symbol(Simbolo.pari, yycolumn, yyline);}
")"           { return new Symbol(Simbolo.parf, yycolumn, yyline);}
"CONTEO"           { return new Symbol(Simbolo.conteo, yycolumn, yyline);}
"aTEXTO()"           { return new Symbol(Simbolo.atexto, yycolumn, yyline);}
"SI"           { return new Symbol(Simbolo.si, yycolumn, yyline);}
"SINO"           { return new Symbol(Simbolo.sino, yycolumn, yyline);}
"SELECCIONA"           { return new Symbol(Simbolo.selecciona, yycolumn, yyline);}
"CASO"           { return new Symbol(Simbolo.caso, yycolumn, yyline);}
"DEFECTO"           { return new Symbol(Simbolo.defecto, yycolumn, yyline);}
"PARA"           { return new Symbol(Simbolo.para, yycolumn, yyline);}
"MIENTRAS"           { return new Symbol(Simbolo.mientras, yycolumn, yyline);}
"DETENER"           { return new Symbol(Simbolo.detener, yycolumn, yyline);}
"IMPRIMIR"           { return new Symbol(Simbolo.imprimir, yycolumn, yyline);}
"FUNCION"           { return new Symbol(Simbolo.funcion, yycolumn, yyline);}
"FUNCION()"           { return new Symbol(Simbolo.newfuncion, yycolumn, yyline);}
"RETORNAR"           { return new Symbol(Simbolo.retornar, yycolumn, yyline);}
"MENSAJE"           { return new Symbol(Simbolo.mensaje, yycolumn, yyline);}
"DOCUMENTO"           { return new Symbol(Simbolo.documento, yycolumn, yyline);}
"OBTENER"           { return new Symbol(Simbolo.obtener, yycolumn, yyline);}
"SETELEMENTO"           { return new Symbol(Simbolo.setelemento, yycolumn, yyline);}
"OBSERVADOR"           { return new Symbol(Simbolo.observador, yycolumn, yyline);}

//operadores
"=="           { return new Symbol(Simbolo.igual, yycolumn, yyline);}
"!="           { return new Symbol(Simbolo.diferente, yycolumn, yyline);}
"<"           { return new Symbol(Simbolo.menorq, yycolumn, yyline);}
">"           { return new Symbol(Simbolo.mayorq, yycolumn, yyline);}
"<="           { return new Symbol(Simbolo.menory, yycolumn, yyline);}
">="           { return new Symbol(Simbolo.mayory, yycolumn, yyline);}
"&&"           { return new Symbol(Simbolo.and, yycolumn, yyline);}
"||"           { return new Symbol(Simbolo.or, yycolumn, yyline);}
"!"           { return new Symbol(Simbolo.not, yycolumn, yyline);}
"+"           { return new Symbol(Simbolo.suma, yycolumn, yyline);}
"-"           { return new Symbol(Simbolo.resta, yycolumn, yyline);}
"*"           { return new Symbol(Simbolo.multiplicacion, yycolumn, yyline);}
"/"           { return new Symbol(Simbolo.division, yycolumn, yyline);}
"^"           { return new Symbol(Simbolo.potencia, yycolumn, yyline);}
"%"           { return new Symbol(Simbolo.modulo, yycolumn, yyline);}
"++"           { return new Symbol(Simbolo.adicion, yycolumn, yyline);}
"--"           { return new Symbol(Simbolo.sustraccion, yycolumn, yyline);}

{id}       { return new Symbol(Simbolo.id,  yycolumn, yyline, yytext());}
{cadena}       { return new Symbol(Simbolo.cadena,  yycolumn, yyline, yytext());}
{bool}       { return new Symbol(Simbolo.bool,  yycolumn, yyline, yytext());}
{date}       { return new Symbol(Simbolo.date,  yycolumn, yyline, yytext());}
{datetime}       { return new Symbol(Simbolo.datetime,  yycolumn, yyline, yytext());}
{numero}		 { return new Symbol(Simbolo.numero,  yycolumn, yyline, yytext());}

/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);}

}