package io.github.lvbo.learn.netty.common;

import io.github.lvbo.learn.netty.common.operation.OperationResult;
import io.github.lvbo.learn.netty.common.operation.OperationType;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:17
 */
public class ResponseMessage extends Message<OperationResult> {
    @Override
    protected Class<OperationResult> getMessageBodyByOpCode(int opCode) {
        return OperationType.getResponseOperationfromOpCode(opCode);
    }
}
