package moe.taswell.rebirthutils.nms.api.scheduler.impl;

import moe.taswell.rebirthutils.nms.api.scheduler.ScheduledTask;
import moe.taswell.rebirthutils.nms.api.scheduler.SchedulerService;
import moe.taswell.rebirthutils.nms.api.scheduler.impl.task.NormalScheduledTaskImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class BukkitSchedulerServiceImpl implements SchedulerService {
    @Override
    public ScheduledTask runTaskNow(Runnable task, @Nullable Location location, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTask(plugin,task).getTaskId());
        return wrapped;
    }

    @Override
    public ScheduledTask runAsyncTaskNow(Runnable task, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTaskAsynchronously(plugin,task).getTaskId());
        return wrapped;
    }

    @Override
    public ScheduledTask runTaskLater(Runnable task, long delay, @Nullable Location location, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTaskLater(plugin,task,delay).getTaskId());
        return wrapped;
    }

    @Override
    public ScheduledTask runAsyncTaskLater(Runnable task, long delay, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay).getTaskId());
        return wrapped;
    }

    @Override
    public ScheduledTask runTaskTimer(Runnable task, long delay, long period, @Nullable Location location, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period).getTaskId());
        return wrapped;
    }

    @Override
    public ScheduledTask runAsyncTaskTimer(Runnable task, long delay, long period, Plugin plugin) {
        final NormalScheduledTaskImpl wrapped = new NormalScheduledTaskImpl(this);
        wrapped.setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId());
        return wrapped;
    }

    @Override
    public void cancelTask(Object taskId) {
        Bukkit.getScheduler().cancelTask((Integer) taskId);
    }
}
