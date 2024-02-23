package moe.taswell.rebirthutils.nms.api.scheduler.impl.task;

import moe.taswell.rebirthutils.nms.api.scheduler.ScheduledTask;
import moe.taswell.rebirthutils.nms.api.scheduler.SchedulerService;

public class NormalScheduledTaskImpl implements ScheduledTask {
    private final SchedulerService parentService;
    private Object taskId = null;

    public NormalScheduledTaskImpl(SchedulerService parentService) {
        this.parentService = parentService;
    }

    public void setTaskId(Object taskId){
        this.taskId = taskId;
    }

    @Override
    public void cancel() {
        this.parentService.cancelTask(this.taskId);
    }
}
