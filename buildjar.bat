@echo off

echo Processing files from last build...
del build\* /s /f /q /a
rd build
mkdir build

echo Building...
javac -encoding utf-8 -d build "DuplicateLines.java"

echo Packing...
copy .\README.md build\
copy .\LICENSE build\
cd build
jar -cvfm "DuplicateLines.jar" "../manifest" -C ./ .

cd ..
pause
