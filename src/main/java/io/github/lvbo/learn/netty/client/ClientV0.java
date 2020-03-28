package io.github.lvbo.learn.netty.client;

import io.github.lvbo.learn.netty.client.codec.decoder.FrameDecoder;
import io.github.lvbo.learn.netty.client.codec.decoder.ProcotolDecoder;
import io.github.lvbo.learn.netty.client.codec.encoder.FrameEncoder;
import io.github.lvbo.learn.netty.client.codec.encoder.ProcotolEncoder;
import io.github.lvbo.learn.netty.common.MessageHeader;
import io.github.lvbo.learn.netty.common.OperationType;
import io.github.lvbo.learn.netty.common.RequestMessage;
import io.github.lvbo.learn.netty.common.order.OrderOperation;
import io.github.lvbo.learn.netty.util.StreamIDGenerator;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/26 07:14
 */
public class ClientV0 {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(eventLoopGroup);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new FrameDecoder())
                            .addLast(new FrameEncoder())
                            .addLast(new ProcotolEncoder())
                            .addLast(new ProcotolDecoder())
                            .addLast(new LoggingHandler(LogLevel.INFO));
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090);
            channelFuture.sync();

            RequestMessage requestMessage = new RequestMessage(StreamIDGenerator.getNextStreamId(),
                    new OrderOperation(999, "lvbo"));

            channelFuture.channel().writeAndFlush(requestMessage);

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
