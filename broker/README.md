### start zookeeper and kafka
On Linux:

``` sh
cd zookeeper-3.4.8
```

``` sh
bin/zookeeper-server-start.sh config/zookeeper.properties
```

``` sh
cd kafka_2.11-0.9.0.1
```

``` sh
bin/kafka-server-start.sh config/server.properties
```

On Windows:

```
cd zookeeper-3.4.8\bin
```

```
zkServer.cmd
``` 

``` 
cd kafka_2.11-0.9.0.1\bin\windows\
```

```
kafka-server-start.bat .\config\server.properties
```

### create a topic
On Linux:

``` sh
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 10 --topic test_topic
```

On Windows:

```
cd kafka_2.11-0.9.0.1\bin\windows
```
``` 
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 10 --topic test
```

### package this example
``` sh
mvn clean package
```


### Run the Consumer
Run the consumer. This will need to be run as we are using kafka to handle the message broker layer. (TBD)

``` sh
java -cp broker-1.0-SNAPSHOT.jar io.scala.elixir.CONSUMER localhost:2181 group1 test_topic 10 0
```
