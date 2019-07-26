@ECHO off
javac -cp JUnit/junit-4.12.jar  src/staticcode/analysis/*.java -d bin
java -cp bin staticcode.analysis.Client
::java -cp JUnit/junit-4.12.jar;JUnit/hamcrest-core-1.3.jar;bin  org.junit.runner.JUnitCore staticcode.analysis.test.FindbugsStaticCodeAnalyzerTest
::java -cp JUnit/junit-4.12.jar;JUnit/hamcrest-core-1.3.jar;bin  org.junit.runner.JUnitCore staticcode.analysis.test.PMDStaticCodeAnalyzerTest
echo application terminated with exit code %errorlevel%
PAUSE