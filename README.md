# elastic-logger

## Configuration (config.properties)

 - config.elastic.host=128.0.0.1
 - config.elastic.port=9200
 - config.elastic.protocol=http

## Available Logs

 - ApiLog<RQ, RS>
 - BasicLog<T>

## How-To

You just need to create a new Log object from the above list and
call logToElastic() method.