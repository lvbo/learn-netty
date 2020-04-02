package io.github.lvbo.learn.netty.client.codec.encoder;

import io.github.lvbo.learn.netty.common.operation.Operation;
import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.util.StreamIDGenerator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/27 07:11
 */
public class OperationEncoder extends MessageToMessageEncoder<Operation> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Operation msg, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage(StreamIDGenerator.getNextStreamId(), msg);
        out.add(requestMessage);
    }
}
