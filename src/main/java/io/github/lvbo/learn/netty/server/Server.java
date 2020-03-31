package io.github.lvbo.learn.netty.server;

import io.github.lvbo.learn.netty.server.codec.decoder.FrameDecoder;
import io.github.lvbo.learn.netty.server.codec.decoder.ProcotolDecoder;
import io.github.lvbo.learn.netty.server.codec.encoder.FrameEncoder;
import io.github.lvbo.learn.netty.server.codec.encoder.ProcotolEncoder;
import io.github.lvbo.learn.netty.server.handler.MetricHandler;
import io.github.lvbo.learn.netty.server.handler.OperationHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:27
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childOption(NioChannelOption.TCP_NODELAY, true);

        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
        NioEventLoopGroup workGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

        LoggingHandler debugLoggingHandler = new LoggingHandler(LogLevel.DEBUG);
        LoggingHandler infoLoggingHandler = new LoggingHandler(LogLevel.INFO);

        MetricHandler metricHandler = new MetricHandler();

        try {
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast("debugLoggingHanlder", debugLoggingHandler)
                            .addLast("metricHandler", metricHandler)
                            .addLast("frameeEncoder", new FrameEncoder())
                            .addLast("frameDecoder", new FrameDecoder())
                            .addLast("procotolEncoder", new ProcotolEncoder())
                            .addLast("procotolDecoder", new ProcotolDecoder())
                            .addLast("infoLoggingHandler", infoLoggingHandler)
                            .addLast("operationHandler", new OperationHandler());

                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
