package snw.rfmx.legacy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import snw.rfm.RunForMoney;
import snw.rfm.api.GameController;

public class ClearCoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameController controller = RunForMoney.getInstance().getGameController();
        if (controller != null) {
            controller.clearCoin();
            sender.sendMessage(ChatColor.GREEN + "操作成功。");
        } else {
            sender.sendMessage(ChatColor.RED + "操作失败。游戏并未运行。");
        }
        return true;
    }
}
