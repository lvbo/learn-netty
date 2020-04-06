package io.github.lvbo.learn.netty.server.handler;

import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.common.operation.Operation;
import io.github.lvbo.learn.netty.common.operation.auth.AuthOperation;
import io.github.lvbo.learn.netty.common.operation.auth.AuthOperationResult;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/4/6 07:47
 */
@Slf4j
@ChannelHandler.Sharable
public class AuthHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
        try {
            Operation operation = msg.getMessageBody();
            if (operation instanceof AuthOperation) {
                AuthOperation authOperation = (AuthOperation) operation;
                AuthOperationResult authOperationResult = authOperation.execute();
                if (authOperationResult.isSuccess()) {
                    log.info("auth is success");
                } else {
                    log.error("auth is error");
                    ctx.close();
                }
            } else {
                log.error("expect first operation is auth");
                ctx.close();
            }
        } catch (Exception e) {
            log.error("", e);
            ctx.close();
        } finally {
            ctx.pipeline().remove(this);
        }
    }
}
