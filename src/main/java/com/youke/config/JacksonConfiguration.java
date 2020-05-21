package com.youke.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.youke.common.jackson.time.DateParser;
import com.youke.common.spring.jackson.deserializer.DateDeserializer;
import com.youke.common.spring.jackson.serializer.DateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
        builder.deserializers(new DateDeserializer(new DateParser()));
        builder.serializers(new DateSerializer());
        ObjectMapper objectMapper = builder.build();
        return objectMapper;
    }
}
