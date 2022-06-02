# binance-spot-service
## Intro
Binance spot service - is the consumer/producer application which is processing spot events from the Binance Websocket API and inserts them into the MySQL database.

## Workflow
### Version 1.0
The Consumer/Producer is encapsulated into the single App and runs in different threads. <br/>
1 Producer per 1 spot trade. The producer push events into the queue _(with the external storage on the disk)_. <br/>
The consumer reads events collect them into the batches and insert them into the MySQl.
<p align="center"><img src="diagram/spot_service_v1.png"/></p>

### Version 2.0 (TODO)
In the 2.0 we'll split consumer and producer into separate services and integrate them with the RabbitMQ. This will allow us to effectively scale the processing pipeline.
<p align="center"><img src="diagram/spot_service_v2.png"/></p>


## Application start

1. cd <project_folder>/docker <br/>
   docker-compose up

2. Set required spot trades <br/>
   ../BinanceSpotService/src/main/resources/application.properties <br/>
   _Example:_ "spot.trades=BTCUSDT,ETHUSDT"

3. Run service
