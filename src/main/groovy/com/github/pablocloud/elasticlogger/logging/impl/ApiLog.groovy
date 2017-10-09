package com.github.pablocloud.elasticlogger.logging.impl

import com.github.pablocloud.elasticlogger.logging.LogAbstract
import com.github.pablocloud.elasticlogger.types.ElasticClient


class ApiLog<RQ, RS> extends LogAbstract<ApiLog> {

    private RQ request
    private RS response
    private Date date

    @Override
    def logToElastic() {
        new ElasticClient<ApiLog>().sendToElastic(this)
    }

}
