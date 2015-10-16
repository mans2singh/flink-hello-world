# Flink Install and Examples

## Prerequisites

# Environment Setup

Confirm Java 1.7.*

Download Flink (0.9.1)

http://www.apache.org/dyn/closer.lua/flink/flink-0.9.1/flink-0.9.1-bin-hadoop1.tgz

Install Git, Maven if needed:

```shell
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

% brew install git
% brew install git-flow-avh

% brew install maven
```

## Installation

Install Hadoop and Hive:

```shell
$ brew install hadoop
$ brew install hive
```

## Configuration

```shell
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home"

...

Create folder and untar flink gzip

```shell
mkdir -p ~/Documents/projects/intro
tar -xvf flink*.gz
```

Start flink job manater
```shell
$ ./start-local.sh
```

## Flink Examples Setup

Build flink-hello-world project:

```shell
$ cd ~/flink-hello-world/
$ mvn clean package
```

## Run the WordCountBatch Example

./flink run  -c com.mans2singh.intro.big.data.WordCountSocketStream /Users/msingh2/Documents/projects/intuit/intro/flink-hello-world/target/flink-hello-world-1.0-SNAPSHOT.jar localhost 9999


## Run the WordCountStream Example

./flink run  -c com.mans2singh.intro.big.data.WordCountSocketStream ~Documents/projects/intuit/intro/flink-hello-world/target/flink-hello-world-1.0-SNAPSHOT.jar localhost 9999
