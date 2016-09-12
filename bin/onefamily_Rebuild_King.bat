@echo off
::-----------------------------
::repackage onefamily-server
::-----------------------------
F:
cd \
cd "F:\Project\Idea\onefamily\onefamily-server\"
rd /s /q target
md target
cd ..
call mvn clean package -DskipTests=true

::-----------------------------
::clean onefamily-server in tomcat
::-----------------------------
cd \
cd "F:\TechProgram\apache-tomcat-7.0.32\webapps\"
rd /s /q  ROOT
del /s /q  ROOT.war

::-----------------------------
::copy war to tomcat
::-----------------------------
copy "F:\Project\Idea\onefamily\onefamily-server\target\onefamily-server.war" "F:\TechProgram\apache-tomcat-7.0.32\webapps\"

::-----------------------------
::rename war
::-----------------------------
cd \
cd "F:\TechProgram\apache-tomcat-7.0.32\webapps\"
ren onefamily-server.war ROOT.war

::-----------------------------
::start tomcat
::-----------------------------
cd \
cd "F:\TechProgram\apache-tomcat-7.0.32\bin"
catalina.bat jpda start