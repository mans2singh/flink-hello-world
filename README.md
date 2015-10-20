# Flink Install and Examples


These examples are based Flink version 0.9.1.

## Flink Documentation
https://ci.apache.org/projects/flink/flink-docs-release-0.9/index.html

## Steps involved in a creating a Flink application

1. Create an ExecutionEnvironment
2. Initialize input data
3. Apply operations - transformations/filtering/joins/etc
4. Specify how to output
5. Execute the application

## Prerequisites

Java 1.7.*

Maven

Git

## Set up project

```shell
mkdir -p ~/Documents/projects/intro

cd ~/Documents/projects/intro
```

## Check out flink-hello-world from github.com

```shell
git clone https://github.com/mans2singh/flink-hello-world.git flink-hello-world
```

## Build flink project

```shell
cd ~/flink-hello-world/
mvn clean install
```
## Setup eclipse IDE (optional)

```shell
mvn eclipse:eclipse
```

## Prepare class path for the examples

```shell
mvn dependency:copy-dependencies
export CP=$(find ./target/dependency/ | xargs | sed  's/ /:/g'):./target/flink-hello-world-1.0-SNAPSHOT.jar
```

## Run the WordCountSocketStream example that counts words from a stream

### Run nc (netcat utility) in one terminal

```shell
nc -lk 9999
```

### Run the flink code in another terminal

```shell
java -cp $CP com.mans2singh.intro.big.data.stream.WordCountSocketStream localhost 9999
```
#### Enter - this is a good day

(is,1)
(a,1)
(good,1)
(day,1)
(this,1)
