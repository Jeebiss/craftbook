package com.sk89q.craftbook.bukkit;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.sk89q.craftbook.bukkit.util.BukkitUtil;
import com.sk89q.craftbook.util.config.YAMLConfiguration;
import com.sk89q.util.yaml.YAMLProcessor;

/**
 * A CraftBook implementation of {@link com.sk89q.worldedit.bukkit.BukkitConfiguration}.
 */
public class BukkitConfiguration extends YAMLConfiguration {

    public boolean enableCircuits;
    public boolean enableMechanisms;
    public boolean enableVehicles;

    public boolean noOpPermissions;
    public boolean indirectRedstone;
    public boolean useBlockDistance;
    public boolean safeDestruction;
    public int stThinkRate;
    public boolean obeyWorldguard;
    public boolean advancedBlockChecks;

    public boolean updateNotifier;

    public String language;
    public List<String> languages;

    private final CraftBookPlugin plugin;

    public BukkitConfiguration(YAMLProcessor config, CraftBookPlugin plugin) {

        super(config, plugin.getLogger());
        this.plugin = plugin;
    }

    @Override
    public void load() {

        try {
            config.load();
        } catch (IOException e) {
            logger.severe("Error loading CraftBook configuration: " + e);
            BukkitUtil.printStacktrace(e);
        }

        config.setHeader(
                "# CraftBook Configuration for Bukkit. Generated for version: " + CraftBookPlugin.inst().getDescription().getVersion(),
                "# This configuration will automatically add new configuration options for you,",
                "# So there is no need to regenerate this configuration unless you need to.",
                "# More information about these configuration nodes are available at...",
                "# http://wiki.sk89q.com/wiki/CraftBook/Configuration",
                "",
                "");

        config.setComment("enable-circuits", "If this is set to false, all circuit mechanics will be disabled, and circuit configuration will not do anything.");
        enableCircuits = config.getBoolean("enable-circuits", true);

        config.setComment("enable-mechanisms", "If this is set to false, all mechanics will be disabled, and mechanism configuration will not do anything.");
        enableMechanisms = config.getBoolean("enable-mechanisms", true);

        config.setComment("enable-vehicles", "If this is set to false, all vehicles mechanics will be disabled, and vehicle configuration will not do anything.");
        enableVehicles = config.getBoolean("enable-vehicles", true);


        config.setComment("st-think-ticks", "WARNING! Changing this can result in all ST mechanics acting very weirdly, only change this if you know what you are doing!");
        stThinkRate = config.getInt("st-think-ticks", 2);

        config.setComment("notify-updates", "Enables the update notifier. This checks for updates on start, and notifies anyone with the permission when they join. They can then use /cb update to download the update.");
        updateNotifier = config.getBoolean("notify-updates", true);

        config.setComment("safe-destruction", "Causes many mechanics to require sufficient blocks to function, for example gates, bridges and doors.");
        safeDestruction = config.getBoolean("safe-destruction", true);

        config.setComment("no-op-permissions", "If on, OP's will not default to have access to everything.");
        noOpPermissions = config.getBoolean("no-op-permissions", false);

        config.setComment("indirect-redstone", "Allows redstone not directly facing a mechanism to trigger said mechanism.");
        indirectRedstone = config.getBoolean("indirect-redstone", false);

        config.setComment("use-block-distance", "Rounds all distance equations to the block grid.");
        useBlockDistance = config.getBoolean("use-block-distance", false);

        config.setComment("check-worldguard-flags", "Checks to see if WorldGuard allows building/using in the area when activating mechanics.");
        obeyWorldguard = config.getBoolean("check-worldguard-flags", true);

        config.setComment("advanced-block-checks", "Use advanced methods to detect if a player can build or not. Use this if you use region protections other than WorldGuard, or experience issues with WorldGuard protection.");
        advancedBlockChecks = config.getBoolean("advanced-block-checks", false);

        config.setComment("language", "The default language for CraftBook.");
        language = config.getString("language", "en_US");

        config.setComment("languages", "A list of languages supported by craftbook, if a user requests a language not listed... They will see default.");
        languages = config.getStringList("languages", Arrays.asList("en_US"));

        super.load();
    }

    @Override
    public File getWorkingDirectory() {

        return plugin.getDataFolder();
    }
}