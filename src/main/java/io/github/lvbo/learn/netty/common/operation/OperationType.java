package io.github.lvbo.learn.netty.common.operation;

import io.github.lvbo.learn.netty.common.operation.auth.AuthOperation;
import io.github.lvbo.learn.netty.common.operation.auth.AuthOperationResult;
import io.github.lvbo.learn.netty.common.operation.keepalive.KeepaliveOperation;
import io.github.lvbo.learn.netty.common.operation.keepalive.KeepaliveOperationResult;
import io.github.lvbo.learn.netty.common.operation.order.OrderOperation;
import io.github.lvbo.learn.netty.common.operation.order.OrderOperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:09
 */
public enum OperationType {
    ORDER(3, OrderOperation.class, OrderOperationResult.class),
    AUTH(2, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(1,KeepaliveOperation.class, KeepaliveOperationResult.class);

    OperationType(int opCode, Class requestOperationClazz, Class responseOperationClazz) {
        this.opCode = opCode;
        this.requestOperationClazz = requestOperationClazz;
        this.responseOperationClazz = responseOperationClazz;
    }

    private int opCode;

    private Class<Operation> requestOperationClazz;

    private Class<OperationResult> responseOperationClazz;

    public int getOpCode() {
        return opCode;
    }

    public Class getRequestOperationClazz() {
        return requestOperationClazz;
    }

    public Class getResponseOperationClazz() {
        return responseOperationClazz;
    }


    public static Class<OperationResult> getResponseOperationfromOpCode(int opCode) {
        OperationType operationType = getOperationType(opCode);
        if (operationType != null) {
            return operationType.getResponseOperationClazz();
        }
        return null;
    }

    private static OperationType getOperationType(int opCode) {
        OperationType[] operationTypes = values();
        for (OperationType operationType : operationTypes) {
            if (operationType.getOpCode() == opCode) {
                return operationType;
            }
        }
        return null;
    }

    public static Class<Operation> getRequestOperationfromOpCode(int opCode) {
        OperationType operationType = getOperationType(opCode);
        if (operationType != null) {
            return operationType.getRequestOperationClazz();
        }
        return null;
    }

    public static int getOpCodeFromOperation(Operation operation) {
        OperationType[] operationTypes = values();
        for (OperationType operationType : operationTypes) {
            if (operationType.requestOperationClazz == operation.getClass()) {
                return operationType.getOpCode();
            }
        }
        throw new AssertionError("not found!");
    }
}

