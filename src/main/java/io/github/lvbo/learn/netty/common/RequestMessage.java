package io.github.lvbo.learn.netty.common;

import io.github.lvbo.learn.netty.common.operation.Operation;
import io.github.lvbo.learn.netty.common.operation.OperationType;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:12
 */
public class RequestMessage extends Message<Operation> {
    @Override
    protected Class<Operation> getMessageBodyByOpCode(int opCode) {
        return OperationType.getRequestOperationfromOpCode(opCode);
    }

    public RequestMessage() {
    }

    public RequestMessage(long streamId, Operation operation) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setOpCode(OperationType.getOpCodeFromOperation(operation));
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;
        this.messageBody = operation;
    }

}
