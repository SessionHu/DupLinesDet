@echo off

echo ���ڴ�����һ�ι���������...
del build\* /s /f /q /a
rd build
mkdir build

echo ���ڱ���...
javac -encoding utf-8 -d build "DuplicateLines.java"

echo ���ڴ��...
copy .\README.md build\
copy .\LICENSE build\
cd build
jar -cvfm "DuplicateLines.jar" "../manifest" -C ./ .

cd ..
pause