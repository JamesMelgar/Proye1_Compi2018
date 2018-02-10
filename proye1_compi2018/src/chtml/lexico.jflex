package chtml;
import java_cup.runtime.Symbol;
import pr1compilarodores2.Nodo;
%%

%cupsym Simbolo
%class AnalizadorLexico
%cup
%public
%unicode
%public
%line
%column
%ignorecase

digito = [0-9]
numero = ("-")?{digito}+("." {digito}+)?
tchar = "'"~"'"
tstring = "\"" ~"\""
letra = [a-zA-Z��]
id = {letra}+({letra}|{digito}|"_")*

%{
//VARIALES Y METODOS DEL SCANER

%}

%%	

<YYINITIAL>{
//Operaciones Aritm�ticas
"+"                 {return new Symbol(Simbolo.mas,yycolumn,yyline);}
"-"                 {return new Symbol(Simbolo.menos,yycolumn,yyline);}
"*"                 {return new Symbol(Simbolo.por,yycolumn,yyline);}  
"/"                 {return new Symbol(Simbolo.div,yycolumn,yyline);}
";"                 {return new Symbol(Simbolo.puntoycoma,yycolumn,yyline);}
","                 {return new Symbol(Simbolo.coma,yycolumn,yyline);}
"="                 {return new Symbol(Simbolo.igual,yycolumn,yyline);}

//Tipo dato
"int"           { return new Symbol(Simbolo.int_, yycolumn, yyline);}
"double"        { return new Symbol(Simbolo.double_, yycolumn, yyline);}
"string"        { return new Symbol(Simbolo.string_, yycolumn, yyline);}
"char"          { return new Symbol(Simbolo.char_, yycolumn, yyline);}

{numero}       	{ return new Symbol(Simbolo.numero,  yycolumn, yyline, yytext());}
{tstring}       { return new Symbol(Simbolo.tstring,  yycolumn, yyline, yytext());}
{tchar}         { return new Symbol(Simbolo.tchar,  yycolumn, yyline, yytext());}
{id}            { return new Symbol(Simbolo.id,  yycolumn, yyline, yytext());}

/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);}

}
























