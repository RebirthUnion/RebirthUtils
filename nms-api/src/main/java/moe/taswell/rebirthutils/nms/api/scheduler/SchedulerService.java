package moe.taswell.rebirthutils.nms.api.scheduler;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;

public interface SchedulerService {
    ScheduledTask runTaskNow(Runnable task, @Nullable Location location, Plugin plugin);

    ScheduledTask runAsyncTaskNow(Runnable task,Plugin plugin);

    ScheduledTask runTaskLater(Runnable task,long delay,@Nullable Location location,Plugin plugin);

    ScheduledTask runAsyncTaskLater(Runnable task,long delay,Plugin plugin);

    ScheduledTask runTaskTimer(Runnable task,long delay,long period,@Nullable Location location,Plugin plugin);

    ScheduledTask runAsyncTaskTimer(Runnable task,long delay,long period,Plugin plugin);

    void cancelTask(Object taskId);
}
