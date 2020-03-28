package io.github.lvbo.learn.netty.server.handler;

import io.github.lvbo.learn.netty.common.Operation;
import io.github.lvbo.learn.netty.common.OperationResult;
import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/25 07:49
 */
public class OperationHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
        Operation operation = msg.getMessageBody();
        OperationResult operationResult = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(msg.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        ctx.channel().writeAndFlush(responseMessage);
    }
}
