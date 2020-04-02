package io.github.lvbo.learn.netty.client.handler;

import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/2 07:41
 */
public class ClientIdleStateHandler extends IdleStateHandler {

    public ClientIdleStateHandler() {
        super(0, 4, 0, TimeUnit.SECONDS);
    }
}
