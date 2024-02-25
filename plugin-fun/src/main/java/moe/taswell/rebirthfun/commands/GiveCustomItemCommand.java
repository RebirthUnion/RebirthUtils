package moe.taswell.rebirthfun.commands;

import moe.taswell.rebirthfun.items.CustomItemRegistryManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveCustomItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            sender.sendMessage(Component.text("Only players could use this command!").color(TextColor.color(255,0,0)));
            return true;
        }

        if (!sender.hasPermission("rebirthfun.commands.gcitem")){
            sender.sendMessage(Component.text("No permission!").color(TextColor.color(255,0,0)));
            return true;
        }

        if (args.length != 1){
            sender.sendMessage(Component.text("Wrong use!Please use it like this:").color(TextColor.color(255,0,0)));
            return false;
        }

        final ItemStack target = CustomItemRegistryManager.getItem(args[0]);

        if (target == null){
            sender.sendMessage(Component.text("Item not found!").color(TextColor.color(255,0,0)));
            return true;
        }

        player.getInventory().addItem(target);
        return true;
    }
}
