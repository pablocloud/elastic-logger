package com.github.pablocloud.elasticlogger.utils

import java.util.Properties as JavaProperties

class ElasticConfig {

    public static JavaProperties CONFIG

    static {
        CONFIG = new JavaProperties()
        CONFIG.load(this.getResourceAsStream('config.properties'))
    }

}
