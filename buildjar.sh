rm -r build/
mkdir build/

javac -d build/ DuplicateLines.java

cp ./README.md ./LICENSE build/
cd build/
jar -cvfm 'DuplicateLines.jar' ../manifest -C ./ .
