package snw.rfmx.legacy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import snw.rfm.RunForMoney;
import snw.rfm.api.GameController;

import java.io.File;
import java.util.Collection;
import java.util.Objects;

public class ForceOutCommand implements CommandExecutor {

    // accepts a player and reason
    // e.g. /forceout SNWCreations exit
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "参数不足!");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "操作失败。玩家不存在。");
            return true;
        }
        String type = "";
        if (args.length != 1) {
            type = args[1];
        }
        GameController controller = RunForMoney.getInstance().getGameController();
        if (controller != null) {
            String text;
            switch (type) {
                case "exit":
                    text = " 已弃权";
                    break;
                case "respawn":
                    text = " 复活成功";
                    break;
                default:
                    text = " 被强制淘汰";
                    break;
            }
            text = ((Objects.equals(type, "respawn") ? ChatColor.GREEN : ChatColor.RED) + "" + ChatColor.BOLD +
                    (YamlConfiguration.loadConfiguration(new File(RunForMoney.getInstance().getDataFolder(), "nickname.yml"))
                            .getString(target.getName(), target.getName())  // another way to access
                            // nickname support
                            + text));

            controller.forceOut(target);
            Bukkit.broadcastMessage(text);
            sender.sendMessage(ChatColor.GREEN + "操作成功。");
        } else {
            sender.sendMessage(ChatColor.RED + "操作失败。游戏并未运行。");
        }
        return true;
    }
}
