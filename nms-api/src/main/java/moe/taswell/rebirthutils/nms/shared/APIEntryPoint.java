package moe.taswell.rebirthutils.nms.shared;

import moe.taswell.rebirthutils.nms.api.hooks.PlayerEventHooks;
import moe.taswell.rebirthutils.nms.api.manager.NbtManager;
import moe.taswell.rebirthutils.nms.api.manager.PlayerManager;
import moe.taswell.rebirthutils.nms.api.scheduler.SchedulerService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class APIEntryPoint {
    private static final String BASE_PACKAGE_NAME = "moe.taswell.rebirthutils.nms.";
    private static final String API_PACKAGE_NAME = "moe.taswell.rebirthutils.nms.api";

    private static final String FOLIA_SCHEDULER_SERVICE_CLASS_NAME = getBaseAPIModuleName() + ".scheduler.impl.FoliaSchedulerServiceImpl";
    private static final String BUKKIT_SCHEDULER_SERVICE_CLASS_NAME = getBaseAPIModuleName() + ".scheduler.impl.BukkitSchedulerServiceImpl";

    private static String NMS_VERSION;

    private static PlayerEventHooks PLAYER_EVENT_HOOKS;
    private static PlayerManager PLAYER_MANAGER;
    private static SchedulerService SCHEDULER_SERVICE;
    private static NbtManager NBT_MANAGER;

    private static boolean isFolia = false;

    static {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
             isFolia = true;
        }catch (Exception ignored){

        }
    }

    public static boolean isFolia(){
        return isFolia;
    }

    public static void init(){
        NMS_VERSION = Utils.getServerNMSVersion();

        try {
            initPlayerEventHooks();
            initPlayerManager();
            initSchedulerService();
            initNbtManager();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static SchedulerService getSchedulerService(){
        return SCHEDULER_SERVICE;
    }

    @NotNull
    public static NbtManager getNbtManager(){
        return NBT_MANAGER;
    }

    @NotNull
    public static PlayerEventHooks getPlayerEventHooks() {
        return PLAYER_EVENT_HOOKS;
    }

    @NotNull
    public static PlayerManager getPlayerManager() {
        return PLAYER_MANAGER;
    }

    @Contract(pure = true)
    public static @NotNull String getBaseNMSModuleName(){
        return BASE_PACKAGE_NAME + NMS_VERSION;
    }

    @NotNull
    public static String getBaseAPIModuleName(){
        return API_PACKAGE_NAME;
    }

    private static void initPlayerEventHooks() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<?> managerClassCurrentVersion = Class.forName(getBaseNMSModuleName() + ".hooks.PlayerEventHooksImpl");
        final Constructor constructor = managerClassCurrentVersion.getConstructor();

        PLAYER_EVENT_HOOKS = (PlayerEventHooks) constructor.newInstance();
    }

    private static void initPlayerManager() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<?> managerClassCurrentVersion = Class.forName(getBaseNMSModuleName() + ".manager.PlayerManagerImpl");
        final Constructor constructor = managerClassCurrentVersion.getConstructor();
        
        PLAYER_MANAGER = (PlayerManager) constructor.newInstance();
    }

    private static void initSchedulerService() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        final Class<?> schedulerServiceClass = isFolia() ? Class.forName(FOLIA_SCHEDULER_SERVICE_CLASS_NAME) : Class.forName(BUKKIT_SCHEDULER_SERVICE_CLASS_NAME);
        final Constructor constructor = schedulerServiceClass.getConstructor();

        SCHEDULER_SERVICE = (SchedulerService) constructor.newInstance();
    }

    private static void initNbtManager() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        final Class<?> nbtManagerClass = Class.forName(getBaseNMSModuleName() + ".manager.NbtManagerImpl");
        final Constructor constructor = nbtManagerClass.getConstructor();

        NBT_MANAGER = (NbtManager) constructor.newInstance();
    }
}