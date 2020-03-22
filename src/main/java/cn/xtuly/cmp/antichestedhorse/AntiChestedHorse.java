package cn.xtuly.cmp.antichestedhorse;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiChestedHorse extends JavaPlugin {
    public static FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ACHListener(),this);
        Config.rootPath = getDataFolder().getPath();
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        config = Config.load("AntiChestedHorse");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Config.save(config,"AntiChestedHorse");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!label.equalsIgnoreCase("ach") || !(sender instanceof Player)) {
            return false;
        }
        if(!sender.hasPermission("ach.switch")){
            return false;
        }
        if(args.length == 0){
            FileConfiguration config = Config.load("AntiChestedHorse");
            if(config.getBoolean("start")){
                config.set("start",false);
                sender.sendMessage("[§aAntiChestedHorse§f] 功能已禁用");
            }else{
                config.set("start",true);
                sender.sendMessage("[§aAntiChestedHorse§f] 功能已启用");
            }
            Config.save(config,"AntiChestedHorse");
            return true;
        }if(args.length == 1){
            if(args[0].equals("on")){
                config.set("start",true);
                sender.sendMessage("[§aAntiChestedHorse§f] 功能已启用");
            }else if(args[0].equals("off")){
                config.set("start",false);
                sender.sendMessage("[§aAntiChestedHorse§f] 功能已禁用");
            }else if(args[0].equals("reload")){
                Config.save(config,"AntiChestedHorse");
                config = Config.load("AntiChestedHorse");
            }else{
                return false;
            }
        }
        return false;
    }
}
