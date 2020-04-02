package io.github.lvbo.learn.netty.common.operation.keepalive;

import io.github.lvbo.learn.netty.common.operation.OperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/2 07:54
 */
public class KeepaliveOperationResult extends OperationResult {
    private long time;

    public KeepaliveOperationResult(long time) {
        this.time = time;
    }
}
