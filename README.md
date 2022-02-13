# Carb0n
POC monorepo for IOT data sensor collection, analysis and device monitoring

This is an example architecture for an IOT service that collects data from consumer devices. The devices filter carbon emissions from home heating systems in 
order to reduce household CO2 footprints.

The virtual device manager folder is a virtualized version of the devices that pushes randomized data onto a RabbitMQ exchange. The exchange is a fanout
so it distributes the messages to all attached queues.

The main 2 other apps are the Landscape manager which is for admins to monitor the IOT network and ensure the devices are healthy and performing as expected. The other
app is the Carb0n Analysis which will be a basic web server for showing the detailed carbon filtering that is being done by all devices on the network. Currently 
the Analysis app just stores valid messages into an in memory h2 database. The main goal of the analysis app will be to store historical data and to allow export
of data for analysis. Some ML models could be built from the historical data in the future.

