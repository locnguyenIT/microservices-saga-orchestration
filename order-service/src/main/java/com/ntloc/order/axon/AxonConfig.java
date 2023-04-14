package com.ntloc.order.axon;

import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public Serializer messageSerializer() {
        return JacksonSerializer.defaultSerializer();
    }
}
