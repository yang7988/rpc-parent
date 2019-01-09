package com.rayvision.rpc.cache.serializer;

public interface Serializer {

    public  byte[] serializer(Object object);

    public <T> T deserializer(byte[] bytes, Class<T> clazz);
}
