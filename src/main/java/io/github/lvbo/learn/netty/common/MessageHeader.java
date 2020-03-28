package io.github.lvbo.learn.netty.common;

import lombok.Data;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:02
 */
@Data
public class MessageHeader {
    private int version = 1;
    private int opCode;
    private long streamId;
}
