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
(good,1)

(this,2)

## Run the WordDistinctCommandLine example that produces distinct words

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordDistinctCommandLine "this is the test of the new system"
```
## Run the WordJoinBatch example which joins two word counts from two files

```shell
java -cp $CP com.mans2singh.intro.big.data.join.WordJoinBatch file://$PWD/src/main/resources/joinFile1.txt file://$PWD/src/main/resources/joinFile2.txt
```
((john,2),(john,1))

((is,2),(is,2))

((happy,1),(happy,1))

((mary,1),(mary,1))

((very,1),(very,1))

## Run the WordFilterCountCommandLine example that filters articles and counts rest of the words

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordFilterCountCommandLine "this is a test of the new system"
```

(of,1)

(test,1)

(is,1)

(for,1)

(new,1)

(company,1)

(system,1)

(this,1)

## Run the WordCountCommandLine example which counts words

```shell
java -cp $CP com.mans2singh.intro.big.data.batch.WordCountCommandLine "this is a test of the new system"
```

(of,1)

(for,1)

(new,1)

(is,1)

(the,2)

(test,1)

(a,1)

(company,1)

(system,1)

(this,1)
