package com.rayvision.rpc.api;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.rayvision.rpc.common.ApiResponse;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SerializationOptimizerImpl implements SerializationOptimizer {

    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        classes.add(ApiResponse.class);
        return classes;
    }
}