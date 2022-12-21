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
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ForceOutBatchCommand implements CommandExecutor {

    // e.g. /forceoutbatch <type> <players>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "参数不足!");
            return false;
        }
        GameController controller = RunForMoney.getInstance().getGameController();
        if (controller != null) {
            String text;
            switch (args[0]) {
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
            for (Player target : Arrays.stream(args).map(Bukkit::getPlayer).collect(Collectors.toSet())) {
                if (target == null) {
                    continue;
                }
                text = ((Objects.equals(args[0], "respawn") ? ChatColor.GREEN : ChatColor.RED) + "" + ChatColor.BOLD +
                        (YamlConfiguration.loadConfiguration(new File(RunForMoney.getInstance().getDataFolder(), "nickname.yml"))
                                .getString(target.getName(), target.getName())  // another way to access
                                // nickname support
                                + text));

                controller.forceOut(target);
                Bukkit.broadcastMessage(text);
            }
            sender.sendMessage(ChatColor.GREEN + "操作成功。");
        } else {
            sender.sendMessage(ChatColor.RED + "操作失败。游戏并未运行。");
        }
        return true;
    }
}
