@ECHO off

javac *.java
jar cfe APLSIP.jar Driver *.class *.java
