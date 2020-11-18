@ECHO OFF
echo start
SET BINDIR=%~dp0
CD /D "%BINDIR%"
"%ProgramFiles%\Java\jdk-11\bin\java.exe" -Xmx4096m -Xms4096m -jar Cave.jar
PAUSE