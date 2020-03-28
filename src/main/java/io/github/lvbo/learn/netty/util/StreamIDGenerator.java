package io.github.lvbo.learn.netty.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/26 07:26
 */
public class StreamIDGenerator {

    private static final AtomicLong idSeed = new AtomicLong(0);

    public static long getNextStreamId() {
        return idSeed.incrementAndGet();
    }
}
