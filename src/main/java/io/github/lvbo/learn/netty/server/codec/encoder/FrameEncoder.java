package io.github.lvbo.learn.netty.server.codec.encoder;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/25 07:37
 */
public class FrameEncoder extends LengthFieldPrepender {

    public FrameEncoder() {
        super(2);
    }
}
