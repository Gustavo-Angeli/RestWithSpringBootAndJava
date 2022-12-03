package br.com.gusta.integrationtests.controller.withyaml.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import io.restassured.mapper.*;
import io.restassured.mapper.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.type.*;

public class YmlMapper implements ObjectMapper {

    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    protected TypeFactory typeFactory;

    public YmlMapper(TypeFactory typeFactory) {
        this.objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.typeFactory = TypeFactory.defaultInstance();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object deserialize(ObjectMapperDeserializationContext context) {
        try {
            String dataToDeserialize = context.getDataToDeserialize().asString();
            Class type = (Class) context.getType();

            return objectMapper.readValue(dataToDeserialize, type);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object serialize(ObjectMapperSerializationContext context) {
        try {
            return objectMapper.writeValueAsString(context.getObjectToSerialize());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
