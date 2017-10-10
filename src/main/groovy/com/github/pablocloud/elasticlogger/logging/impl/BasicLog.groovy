package com.github.pablocloud.elasticlogger.logging.impl

import com.github.pablocloud.elasticlogger.logging.LogAbstract
import com.github.pablocloud.elasticlogger.types.ElasticClient

class BasicLog<T> extends LogAbstract<BasicLog> {

    private T log
    private Date date

    @Override
    def logToElastic() {
        new ElasticClient<BasicLog>().sendToElastic(this)
    }

}
