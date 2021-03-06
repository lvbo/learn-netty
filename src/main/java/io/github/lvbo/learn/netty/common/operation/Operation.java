package io.github.lvbo.learn.netty.common.operation;

import io.github.lvbo.learn.netty.common.MessageBody;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:06
 */
public abstract class Operation extends MessageBody {

    public abstract OperationResult execute();

}
