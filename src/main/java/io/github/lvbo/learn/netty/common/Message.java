package io.github.lvbo.learn.netty.common;

import io.github.lvbo.learn.netty.util.JsonUtil;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:04
 */
@Data
public abstract class Message <T extends MessageBody> {
    protected MessageHeader messageHeader;
    protected T messageBody;

    public void decode(ByteBuf byteBuf) {
        int version = byteBuf.readInt();
        long streamId = byteBuf.readLong();
        int opCode = byteBuf.readInt();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;

        Class<T> messageBodyClass = getMessageBodyByOpCode(opCode);
        messageBody = JsonUtil.fromJson(byteBuf.toString(StandardCharsets.UTF_8), messageBodyClass);
    }

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes(StandardCharsets.UTF_8));
    }

    protected abstract Class<T> getMessageBodyByOpCode(int opCode);
}
