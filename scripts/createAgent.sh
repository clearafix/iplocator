#!/usr/bin/env bash

cd ../src/main/java

# add full path in quotes to agent.jar in run-configuration:
# -javaagent:"fullPath/agent.jar"

javac ObjectSizer.java
jar -cmf MANIFEST.MF agent.jar ObjectSizer.class
rm ObjectSizer.class