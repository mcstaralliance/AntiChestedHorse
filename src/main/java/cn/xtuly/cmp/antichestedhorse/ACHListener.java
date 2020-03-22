package cn.xtuly.cmp.antichestedhorse;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ACHListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
        if(AntiChestedHorse.config.getBoolean("start")){
            if(!e.getPlayer().hasPermission("ach.bypass")){
                if(e.getRightClicked() instanceof ChestedHorse){
                    ChestedHorse ch = (ChestedHorse)e.getRightClicked();
                    if(ch.isCarryingChest()){
                        ch.setCarryingChest(false);
                    }
                    if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.CHEST){
                        e.setCancelled(true);
                    }
                    if(AntiChestedHorse.config.getString("message") != null){
                        e.getPlayer().sendMessage(AntiChestedHorse.config.getString("message"));
                    }else{
                        e.getPlayer().sendMessage("[§aAntiChestedHorse§f] 该操作已被禁用");
                        AntiChestedHorse.config.set("message","[§aAntiChestedHorse§f] 该操作已被禁用");
                        Config.save(AntiChestedHorse.config,"AntiChestedHorse");
                    }

                }
            }
        }
    }
}
