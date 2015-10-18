# Flink Install and Examples

These examples are based flink 0.9.1 sample set.

## Prerequisites

# Environment Setup

Confirm Java 1.7.*

Download Flink (0.9.1)

http://www.apache.org/dyn/closer.lua/flink/flink-0.9.1/flink-0.9.1-bin-hadoop1.tgz


## Installation

## Configuration

```shell
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home"

...

Create folder and untar flink gzip

```shell
mkdir -p ~/Documents/projects/intro
tar -xvf flink*.gz
```

## Flink Examples Setup

From Flink Documentation - https://ci.apache.org/projects/flink/flink-docs-release-0.9/apis/programming_guide.html

Steps involved in a flink application

1. Create an ExecutionEnvironment
2. Initialize input data
3. Apply operations - transformations/filtering/joins/etc
4. Specify how to output
5. Execute the application

Build flink-hello-world project:

```shell
$ cd ~/flink-hello-world/
$ mvn clean package
```

## Run the WordCountBatch Example

./flink run  -c com.mans2singh.intro.big.data.WordCountSocketStream ~/flink-hello-world/target/flink-hello-world-1.0-SNAPSHOT.jar localhost 9999


## Run the WordCountStream Example

./flink run  -c com.mans2singh.intro.big.data.WordCountSocketStream ~/flink-hello-world-1.0-SNAPSHOT.jar localhost 9999
