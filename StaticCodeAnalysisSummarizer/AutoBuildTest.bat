@ECHO OFF
javac -cp JUnit/junit-4.12.jar  src/staticcode/analysis/*.java -d bin
java -cp JUnit/junit-4.12.jar;JUnit/hamcrest-core-1.3.jar;bin  org.junit.runner.JUnitCore staticcode.analysis.test.FindbugsStaticCodeAnalyzerTest
PAUSE