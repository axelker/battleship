#!/bin/sh
find -name "*.java" > sources.txt
javac -d dist -cp :* @sources.txt
java -cp dist main.Main