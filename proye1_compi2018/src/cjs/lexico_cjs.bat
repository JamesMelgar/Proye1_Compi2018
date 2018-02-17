SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_111\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET JFLEX_HOME=C:\Fuente\jflex-1.6.1
cd C:\Users\James_PC\Documents\NetBeansProjects\pr1compi2\Proye1_Compi2018\proye1_compi2018\src\cjs
java -jar %JFLEX_HOME%\lib\jflex-1.6.1.jar lexico_cjs.jflex
pause
