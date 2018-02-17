SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_111\bin"
SET PATH=%JAVA_HOME%;%PATH%
cd C:\Users\James_PC\Documents\NetBeansProjects\pr1compi2\Proye1_Compi2018\proye1_compi2018\src\cjs
java -jar C:\Fuente\java-cup-11b.jar -parser AnalizadorSintactico_cjs -symbols Simbolo sintactico_cjs.cup
pause
