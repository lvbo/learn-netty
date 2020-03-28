package io.github.lvbo.learn.netty.client.handler;

import io.github.lvbo.learn.netty.client.handler.dispacher.OperationResultCenter;
import io.github.lvbo.learn.netty.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/27 07:26
 */
public class OperationResponseHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private OperationResultCenter operationResultCenter;

    public OperationResponseHandler(OperationResultCenter operationResultCenter) {
        this.operationResultCenter = operationResultCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        operationResultCenter.set(msg.getMessageHeader().getStreamId(), msg.getMessageBody());
    }
}
