package io.github.lvbo.learn.netty.client.codec.decoder;

import io.github.lvbo.learn.netty.common.ResponseMessage;
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
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.decode(msg);
        out.add(responseMessage);
    }
}
