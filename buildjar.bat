@echo off

echo 正在处理上一次构建的内容...
del build\* /s /f /q /a
rd build
mkdir build

echo 正在编译...
javac -encoding utf-8 -d build "DuplicateLines.java"

echo 正在打包...
copy .\README.md build\
copy .\LICENSE build\
cd build
jar -cvfm "DuplicateLines.jar" "../manifest" -C ./ .

cd ..
pause