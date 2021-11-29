package com.namnx.spring_dynamic_json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    public JsonConfig(ObjectMapper objectMapper) {
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
        objectMapper.setFilterProvider(simpleFilterProvider);
    }
}
