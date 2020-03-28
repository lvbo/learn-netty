package io.github.lvbo.learn.netty.client.codec.decoder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/19 08:08
 */
public class FrameDecoder extends LengthFieldBasedFrameDecoder {
    public FrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }
}
