package me.thienbao860.expansion.spectatorexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.text.DecimalFormat;

public class SpectatorExpansion extends PlaceholderExpansion implements Listener {

    private final Main plugin;

    public SpectatorExpansion(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        if (plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return true;
        }
        return false;
    }

    @Override
    public String getIdentifier() {
        return "spectator";
    }

    @Override
    public String getAuthor() {
        return "thienbao860";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        String[] args = identifier.split("_");
        Entity en = player.getSpectatorTarget();

        if (identifier.equalsIgnoreCase("isSpectating")) {
            return (en == null) ? "false" : "true";
        }

        if (identifier.equalsIgnoreCase("lookfrom")) {
            if (en == null) return "None";
            else {
                if (en instanceof Player) {
                    Player p = (Player) en;
                    return p.getName();
                } else return en.getType().name();
            }
        }

        if (args[0].equalsIgnoreCase("infoFrom")) {
            if (en == null) return "";
            if (args.length != 2) return null;
            LivingEntity entity = (LivingEntity) en;
            if (args[1].equalsIgnoreCase("health")) {
                DecimalFormat shorten = new DecimalFormat("#.0");
                return String.valueOf(shorten.format(entity.getHealth()));
            }
        }
        return null;
    }

}
