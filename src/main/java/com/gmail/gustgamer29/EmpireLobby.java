package com.gmail.gustgamer29;

import com.gmail.gustgamer29.configuration.ConfigHandler;
import com.gmail.gustgamer29.listeners.*;
import com.gmail.gustgamer29.listeners.listenerutil.HandlePlayerConnection;
import com.gmail.gustgamer29.listeners.loginlistener.EmpireLoginListener;
import com.gmail.gustgamer29.itemization.itens.ProcessManager;
import com.gmail.gustgamer29.listeners.loginlistener.PlayerJoinInLobby;
import com.gmail.gustgamer29.reflection.ReflectionUtils;
import com.gmail.gustgamer29.reflection.UnSupportedVersion;
import com.gmail.gustgamer29.tablist.ScoreManager;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class EmpireLobby extends JavaPlugin {

    private static final String PLUGIN_NAME = "EmpireLobby";

    private static String pluginVersion = "Unknown";
    private static String pluginBuild = "N/D";

    private static String pluginPrefix = ChatColor.RED + "" + ChatColor.BOLD + "Lobby " + ChatColor.GRAY + ChatColor.BOLD + ">>>";
    private static final String PREFIX_FOLDER = "Settings.Prefix";
    private static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName();

    private static ConfigHandler language;
    private static ConfigHandler configuration;

    private static ProcessManager processManager;

    private static EmpireLobby instance;

    private static ReflectionUtils reflect;

    @Override
    public void onEnable(){
        instance = this;
        loadPluginVersion(getDescription().getVersion());
        initialize();
    }

    @Override
    public void onDisable() {

    }

    private void initialize(){
        initializeJarFiles();
        processManager = new ProcessManager(EmpireLobby.getInstance());
        initializeListeners();
        initializeComma();
        hookToMinecraftVersion();
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    private void loadPluginVersion(String version){
        int index = version.lastIndexOf("-");
        if (index != -1) {
            pluginVersion = version.substring(0, index);
            pluginBuild = version.substring(index + 1);
            if (pluginBuild.startsWith("b")) {
                pluginBuild = pluginBuild.substring(1);
            }
        }
    }

    private void initializeListeners(){
        PluginManager mg = this.getServer().getPluginManager();

        //Login listeners
        mg.registerEvents(new EmpireLoginListener(getProcessManager()), this);
        mg.registerEvents(new PlayerJoinInLobby(getProcessManager(), new HandlePlayerConnection()), this);

        //Bow teleportation listener
        mg.registerEvents(new ShootWithBowListener(), this);

        //Player launch listener
        mg.registerEvents(new PlayerInteractWithEntity(this), this);

        //Hide players listener
        mg.registerEvents(new HidePlayersEvent(new ScoreManager(Bukkit.getScoreboardManager().getMainScoreboard())), this);

        //Block Inventory click && drag Listener
        mg.registerEvents(new InventoryClickBlocker(), this);

        //Weather Event Listener Cancel
        mg.registerEvents(new CancelWeather(), this);

        //Command block Listener
        mg.registerEvents(new CommandBlockerListener(this), this);

    }

    private void initializeComma(){
        getCommand("help").setExecutor(new Command());
        getCommand("help").setTabCompleter(new Command());
        //getCommand("help").getAliases().forEach(System.out::println);
    }

    private void hookToMinecraftVersion() {
        String version = SERVER_VERSION.substring(SERVER_VERSION.lastIndexOf(".") + 1);
        try {
            reflect = new ReflectionUtils(version);
            //info("&dHooked to '&c" + version.replaceAll("_", ".") + "'&d spigot/bukkit version.");
        } catch (UnSupportedVersion err) {
            //ConsoleLogguerManager.getInstance().logSevere("Sua versao atual do bukkit/spigot " + version + " ainda nao e suportada pelo plugin!\ndesabilitando...");
            err.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private static void initializeJarFiles(){
        try {
            language = new ConfigHandler(EmpireLobby.getInstance(), "language.yml");

            if(language.contains(PREFIX_FOLDER) && language.getString(PREFIX_FOLDER) != null){
                pluginPrefix = language.getStringReplaced(PREFIX_FOLDER);
            }

            configuration = new ConfigHandler(EmpireLobby.getInstance(), "configuration.yml");

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static ConfigHandler getConfiguration() {
        return configuration;
    }

    public String getPluginVersion(){
        return pluginVersion;
    }

    public String getPluginBuild(){
        return pluginBuild;
    }

    public String getPluginName(){
        return PLUGIN_NAME;
    }

    public static EmpireLobby getInstance() {
        return instance;
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public ConfigHandler getLanguage() {
        return language;
    }

    public static ReflectionUtils getReflect() {
        return reflect;
    }

}
