#!/bin/bash

HADOOP_CLASSPATH=$(hadoop classpath)

find src/main/java -name "*.java" > sources.txt

javac -cp $HADOOP_CLASSPATH -d out @sources.txt

jar -cvf hadoop-examples.jar -C out/ .
