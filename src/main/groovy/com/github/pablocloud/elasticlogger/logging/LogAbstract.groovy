package com.github.pablocloud.elasticlogger.logging

import com.fasterxml.jackson.databind.ObjectMapper

abstract class LogAbstract<T> implements Log<T> {

    def logToLocal(){
        println(new ObjectMapper().writeValueAsString(this))
    }

    abstract logToElastic()

}
