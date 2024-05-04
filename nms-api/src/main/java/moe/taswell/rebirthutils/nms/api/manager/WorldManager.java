package moe.taswell.rebirthutils.nms.api.manager;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;

public interface WorldManager {
    public World createWorld(@NotNull WorldCreator creator);

    public boolean unloadWorld(@NotNull String name, boolean save);

    public boolean unloadWorld(@NotNull World world, boolean save);
}
