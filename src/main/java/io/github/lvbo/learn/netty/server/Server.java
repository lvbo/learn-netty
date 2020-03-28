package io.github.lvbo.learn.netty.server;

import io.github.lvbo.learn.netty.server.codec.decoder.FrameDecoder;
import io.github.lvbo.learn.netty.server.codec.decoder.ProcotolDecoder;
import io.github.lvbo.learn.netty.server.codec.encoder.FrameEncoder;
import io.github.lvbo.learn.netty.server.codec.encoder.ProcotolEncoder;
import io.github.lvbo.learn.netty.server.handler.OperationHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:27
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try {
            serverBootstrap.group(nioEventLoopGroup);
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new FrameEncoder())
                            .addLast(new FrameDecoder())
                            .addLast(new ProcotolEncoder())
                            .addLast(new ProcotolDecoder())
                            .addLast(new LoggingHandler(LogLevel.INFO))
                            .addLast(new OperationHandler());

                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }
}
