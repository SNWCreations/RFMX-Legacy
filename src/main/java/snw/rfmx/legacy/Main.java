package snw.rfmx.legacy;

import org.bukkit.plugin.java.JavaPlugin;
import snw.rfmx.legacy.commands.*;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("forceout")).setExecutor(new ForceOutCommand());
        Objects.requireNonNull(getCommand("reverse")).setExecutor(new ReverseCommand());
        Objects.requireNonNull(getCommand("hunternomove")).setExecutor(new HunterNoMoveCommand());
        Objects.requireNonNull(getCommand("rfmrandom")).setExecutor(new RFMRandomCommand());
        Objects.requireNonNull(getCommand("addmoney")).setExecutor(new AddMoneyCommand());
        Objects.requireNonNull(getCommand("clearcoin")).setExecutor(new ClearCoinCommand());
        Objects.requireNonNull(getCommand("rrt")).setExecutor(new RemoveRemainingTimeCommand());
        Objects.requireNonNull(getCommand("setcoinpersecond")).setExecutor(new SetCoinPerSecondCommand());
        Objects.requireNonNull(getCommand("forceoutbatch")).setExecutor(new ForceOutBatchCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
