package moe.taswell.rebirthutils.nms.shared;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class Utils {

    @NotNull
    public static String getServerNMSVersion(){
        return "v" + Bukkit.getMinecraftVersion().replace(".","_");
    }

}