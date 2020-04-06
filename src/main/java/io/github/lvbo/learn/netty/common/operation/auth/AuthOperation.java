package io.github.lvbo.learn.netty.common.operation.auth;

import io.github.lvbo.learn.netty.common.operation.Operation;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/6 07:48
 */
public class AuthOperation extends Operation {
    private static final String ADMIN = "admin";
    private String username;
    private String password;

    public AuthOperation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public AuthOperationResult execute() {
        if (username.equals(ADMIN) && password.equals(ADMIN)) {
            return new AuthOperationResult(true);
        } else {
            return new AuthOperationResult(false);
        }
    }
}
