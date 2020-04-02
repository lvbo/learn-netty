package io.github.lvbo.learn.netty.client.handler;

import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.common.operation.keepalive.KeepaliveOperation;
import io.github.lvbo.learn.netty.util.StreamIDGenerator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/2 07:49
 */
@Slf4j
@ChannelHandler.Sharable
public class KeepaliveHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT) {
            log.info("open read idle ");
            RequestMessage requestMessage = new RequestMessage(StreamIDGenerator.getNextStreamId(), new KeepaliveOperation());
            ctx.channel().writeAndFlush(requestMessage);
        }

        super.userEventTriggered(ctx, evt);
    }
}
