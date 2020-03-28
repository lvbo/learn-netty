package io.github.lvbo.learn.netty.server.codec.decoder;

import io.github.lvbo.learn.netty.common.RequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/19 08:10
 */
public class ProcotolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.decode(msg);
        out.add(requestMessage);
    }
}
