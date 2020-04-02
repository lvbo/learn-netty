package io.github.lvbo.learn.netty.common.operation.keepalive;

import io.github.lvbo.learn.netty.common.operation.Operation;
import io.github.lvbo.learn.netty.common.operation.OperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/2 07:53
 */
public class KeepaliveOperation extends Operation {
    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        return new KeepaliveOperationResult(time);
    }
}
