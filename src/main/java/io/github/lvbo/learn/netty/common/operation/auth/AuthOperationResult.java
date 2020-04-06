package io.github.lvbo.learn.netty.common.operation.auth;

import io.github.lvbo.learn.netty.common.operation.OperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/6 07:50
 */
public class AuthOperationResult extends OperationResult {
    private boolean isSuccess;

    public AuthOperationResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
