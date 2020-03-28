package io.github.lvbo.learn.netty.common.order;

import io.github.lvbo.learn.netty.common.OperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:23
 */
public class OrderOperationResult extends OperationResult {

    private int taskId;
    private String taskName;
    private boolean complete;

    public OrderOperationResult(int taskId, String taskName, boolean complete) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.complete = complete;
    }
}
