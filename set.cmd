::@ECHO OFF
REM This is a simple batch file help to compile java code, run inside OUT folder.
::(for /f "delims=" %%A in ('dir /s /B *.java') do @echo "%%A") > src.txt
cd ..
(for /f "delims=" %%A in ('dir /s /B *.java') do @echo "%%A") > src.txt
java set.java
javac -d .\OUT @src.txt
cd .\OUT