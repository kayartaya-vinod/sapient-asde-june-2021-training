# Elasticsearch Fluentd Kibana (EFK)

What is EFK Stack?

“EFK” stands for three major open source projects, namely : Elasticsearch, Fluentd and Kibana.

- Elasticsearch is a search and analytics engine.
- Fluentd to receive, clean and parse the log data.
- Kibana lets users visualize data with charts and graphs in Elasticsearch.

Why is EFK important?

EFK is a log management service. Fluentd will collect the logs and send it to Elasticsearch, which will receive the logs and save it on its database. Kibana will fetch the logs from Elasticsearch and display it on default or custom graphical modes.

- The importance of EFK is that it can help you easily pinpoint the root cause of any application or software error.
- EFK also provides us with a visual overview of how the software is being used globally by the user base — getting all this crucial data in one single dashboard is going to make the productivity rise substantially.

Elasticsearch requires your vm.max_map_count set to 262144 and the default will be 65530. To rectify this, use the command given below in cmd.

```
wsl -d docker-desktop
sysctl -w vm.max_map_count=262144
```

## How to add microservice in docker-compose.yml file

- Create a jar file of the microservice using `mvn clean package` command.
- Add the address of the microservice in the build key and container_name in the docker-compose.yml file.

Below is the configuration for adding a springboot project named springboot-efk which is already docker containerized.

```
springboot-efk:
   build: ./springboot-efk
   container_name: springboot-efk
   ports:
     - "8888:8888"
   logging:
     driver: fluentd
     options:
       fluentd-address: fluentd
```

EFK can be then started by goining to it's directory in cmd and hitting up `docker-compose up` command in it.

Kibana can be accessed by goining to http://localhost:5601.

## Create index pattern

Steps :

- Go to http://localhost:5601 on web browser.
- Choose management tab on the left sidebar.
- Click on index pattern under kibana heading.
- Enter index name as logstash-2021\* and click on next.
- Chose @timestamp in Time Filter field name and click on create index pattern.

Now you will be able to see the incoming logs in the discover tab on the left sidebar.

## How to create and visualize data on dashboard

Steps :

- Go to dashboard tab on the left sidebar and select the available dashboard.
- Click on edit button on top right corner.
- Click on add and then click on add new visualization.
- Select the appropriate visualization type and configure it then save it to dashboard.
