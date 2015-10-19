# Flink Install and Examples

These examples are based flink 0.9.1 documentation and sample set.

## Flink Documentation
https://ci.apache.org/projects/flink/flink-docs-release-0.9/apis/programming_guide.html

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

# Environment Setup

## Set up Java
```shell
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home"

...
```

## Set up examples

```shell
mkdir -p ~/Documents/projects/intro

cd ~/Documents/projects/intro
```

## Check out flink-hello-world

```shell

$ git clone https://github.com/mans2singh/flink-hello-world.git flink-hello-world
```

## Build flink project

```shell
cd ~/flink-hello-world/
mvn clean install
```

## Prepare class path

```shell
mvn dependency:copy-dependencies
export CP=$(find ./target/dependency/ | xargs | sed  's/ /:/g')
```

## Run the WordCountSocketStream Example

### Run nc in one terminal

```shell
nc -lk 9999
```

### Run the flink code in another terminal

```shell
java -cp $CP:./target/flink-hello-world-1.0-SNAPSHOT.jar com.mans2singh.intro.big.data.stream.WordCountSocketStream localhost 9999
```
(good,1)
(this,2)

## Run the WordFilterCountCommandLine Example

```shell
java -cp $CP:./target/flink-hello-world-1.0-SNAPSHOT.jar com.mans2singh.intro.big.data.batch.WordFilterCountCommandLine "this is a test of the new system"
```


## Run the WordFilterCountCommandLine Example

```shell
java -cp $CP:./target/flink-hello-world-1.0-SNAPSHOT.jar com.mans2singh.intro.big.data.batch.WordFilterCountCommandLine "this is a test of the new system"
```

(of,1)
(test,1)
(is,1)
(for,1)
(new,1)
(company,1)
(system,1)
(this,1)

## Run the WordFilterCountCommandLine Example

```shell
java -cp $CP:./target/flink-hello-world-1.0-SNAPSHOT.jar com.mans2singh.intro.big.data.batch.WordCountCommandLine "this is a test of the new system"
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
