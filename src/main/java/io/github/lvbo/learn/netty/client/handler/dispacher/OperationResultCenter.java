package io.github.lvbo.learn.netty.client.handler.dispacher;

import io.github.lvbo.learn.netty.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/27 07:28
 */
public class OperationResultCenter {

    private Map<Long, OperationResultPromise> map = new ConcurrentHashMap<>();

    public void add(long streamId, OperationResultPromise operationResultPromise) {
        map.put(streamId, operationResultPromise);
    }

    public void set(long streamId, OperationResult operationResult) {
        OperationResultPromise operationResultPromise = map.get(streamId);
        if (operationResultPromise != null) {
            operationResultPromise.setSuccess(operationResult);
            map.remove(streamId);
        }
    }
}
