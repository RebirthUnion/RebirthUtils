package moe.taswell.rebirthutils.nms.api.scheduler.impl.task;

import moe.taswell.rebirthutils.nms.api.scheduler.ScheduledTask;
import moe.taswell.rebirthutils.nms.api.scheduler.SchedulerService;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class CallbackScheduledTaskImpl implements ScheduledTask {
    private final SchedulerService parentService;
    private Object taskId = null;
    private final List<Runnable> callbacks = new ArrayList<>();
    private volatile boolean taskFinished = false;

    public CallbackScheduledTaskImpl(SchedulerService parentService) {
        this.parentService = parentService;
    }

    public void setTaskId(Object newId){
        this.taskId = newId;
    }

    public void onTaskFinished(){
        this.taskFinished = true;
        for (Runnable task : this.callbacks){
            try {
                task.run();
            }catch (Exception e){
                Bukkit.getLogger().severe("Failed to run callback! " + e.getLocalizedMessage());
            }
        }
    }

    public void appendCallbackTask(Runnable task){
        if (this.taskFinished){
            throw new IllegalStateException("Task already finished!");
        }

        this.callbacks.add(task);
    }

    @Override
    public void cancel() {
        this.parentService.cancelTask(this.taskId);
    }
}
