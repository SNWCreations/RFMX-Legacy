package snw.rfmx.legacy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import snw.rfm.game.TeamHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RFMRandomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String type = args.length == 1 ? args[0] : "runner";
        List<String> region = new ArrayList<>();
        switch (type) {
            case "runner":
                region.addAll(TeamHolder.getInstance().getRunners());
                break;
            case "out":
                region.addAll(TeamHolder.getInstance().getGiveUpPlayers());
                break;
            case "both":
                region.addAll(TeamHolder.getInstance().getRunners());
                region.addAll(TeamHolder.getInstance().getGiveUpPlayers());
                break;
            default:
                sender.sendMessage(ChatColor.RED + "无效类型");
                return false;
        }
        String result = region.get(new Random().nextInt(region.size() - 1));
        sender.sendMessage(result);
        return true;
    }
}
