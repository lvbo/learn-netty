package io.github.lvbo.learn.netty.common.operation.order;

import io.github.lvbo.learn.netty.common.operation.Operation;
import io.github.lvbo.learn.netty.common.operation.OperationResult;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/18 08:19
 */
public class OrderOperation extends Operation {

    private int taskId;
    private String taskName;

    public OrderOperation(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    @Override
    public OperationResult execute() {
        System.out.printf("order operation taskId=%s,taskName=%s", taskId, taskName);
        return new OrderOperationResult(taskId, taskName, true);
    }
}
