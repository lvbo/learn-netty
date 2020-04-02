package io.github.lvbo.learn.netty.client;

import io.github.lvbo.learn.netty.client.codec.decoder.FrameDecoder;
import io.github.lvbo.learn.netty.client.codec.decoder.ProcotolDecoder;
import io.github.lvbo.learn.netty.client.codec.encoder.FrameEncoder;
import io.github.lvbo.learn.netty.client.codec.encoder.ProcotolEncoder;
import io.github.lvbo.learn.netty.client.handler.ClientIdleStateHandler;
import io.github.lvbo.learn.netty.client.handler.KeepaliveHandler;
import io.github.lvbo.learn.netty.client.handler.OperationResponseHandler;
import io.github.lvbo.learn.netty.client.handler.dispacher.OperationResultCenter;
import io.github.lvbo.learn.netty.client.handler.dispacher.OperationResultPromise;
import io.github.lvbo.learn.netty.common.operation.OperationResult;
import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.common.operation.order.OrderOperation;
import io.github.lvbo.learn.netty.util.StreamIDGenerator;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/26 07:14
 */
public class ClientV2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        OperationResultCenter operationResultCenter = new OperationResultCenter();

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);
        KeepaliveHandler keepaliveHandler = new KeepaliveHandler();

        try {
            bootstrap.group(eventLoopGroup);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientIdleStateHandler())
                            .addLast(new FrameDecoder())
                            .addLast(new FrameEncoder())
                            .addLast(new ProcotolEncoder())
                            .addLast(new ProcotolDecoder())
                            .addLast(new OperationResponseHandler(operationResultCenter))
                            .addLast(loggingHandler)
                            .addLast(keepaliveHandler);
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090);
            channelFuture.sync();

            long streamId = StreamIDGenerator.getNextStreamId();
            RequestMessage requestMessage = new RequestMessage(streamId, new OrderOperation(999, "lvbo"));

            OperationResultPromise operationResultPromise = new OperationResultPromise();
            operationResultCenter.add(streamId, operationResultPromise);

            channelFuture.channel().writeAndFlush(requestMessage);

            OperationResult operationResult = operationResultPromise.get();

            System.out.println(operationResult);

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
