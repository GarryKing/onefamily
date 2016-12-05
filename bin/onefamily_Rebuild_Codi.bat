@echo off
::-----------------------------
::repackage onefamily-server
::-----------------------------
E:
cd \
cd "E:\my\onefamily\onefamily-server\"
rd /s /q target
md target
cd ..
call mvn clean package -DskipTests=true

::-----------------------------
::clean onefamily-server in tomcat
::-----------------------------
D:
cd \
cd "D:\TechProgram\apache-tomcat-8.5.5\webapps\"
rd /s /q  ROOT
del /s /q  ROOT.war

::-----------------------------
::copy war to tomcat
::-----------------------------
E:
cd \
copy "E:\my\onefamily\onefamily-server\target\onefamily-server.war" "D:\TechProgram\apache-tomcat-8.5.5\webapps\"

::-----------------------------
::rename war
::-----------------------------
D:
cd \
cd "D:\TechProgram\apache-tomcat-8.5.5\webapps\"
ren onefamily-server.war ROOT.war

::-----------------------------
::start tomcat
::-----------------------------
cd \
cd "D:\TechProgram\apache-tomcat-8.5.5\bin"
catalina.bat jpda start