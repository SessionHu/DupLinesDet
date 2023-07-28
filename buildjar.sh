echo -e "正在处理上一次构建的内容..."
rm -r build/
mkdir build/

echo -e "正在编译..."
javac -d build/ DuplicateLines.java

echo -e "正在打包..."
cp ./README.md ./LICENSE build/
cd build/
jar -cvfm 'DuplicateLines.jar' ../manifest -C ./ .

cd ..
