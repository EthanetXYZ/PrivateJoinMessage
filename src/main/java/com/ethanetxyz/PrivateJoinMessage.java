package com.ethanetxyz;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class PrivateJoinMessage extends JavaPlugin implements Listener, CommandExecutor {
    private static final String CONFIG_FILE = "messages.json";
    private static final Gson gson = new Gson();
    private List<String> messages;
    private Random random = new Random();

    @Override
    public void onEnable()  {
        loadMessages();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("pjm").setExecutor(this);
    }

    private void loadMessages()  {
        try  (JsonReader reader = new JsonReader(Files.newBufferedReader(new File(getDataFolder(), CONFIG_FILE).toPath(), StandardCharsets.UTF_8))) {
            messages = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
        } catch  (Exception e)  {
            getLogger().severe("Failed to load messages: " + e.getMessage());
            messages = defaultMessages();
        }

        getLogger().info("PrivateJoinMessage: Messages loaded successfully.");
    }

    private List<String> defaultMessages() {
        return List.of(
                "Welcome back, %player%",
                "Hey %player%, great to see you",
                "Hope you brought snacks, %player%",
                "The adventure begins again, %player%",
                "Ready to roll, %player%"
        );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)  {
        Player player = event.getPlayer();

        if (messages == null || messages.isEmpty()) return;

        String randomMessage = messages.get(random.nextInt(messages.size())).replace("%player%", player.getName());
        event.setJoinMessage(null);
        getServer().getScheduler().runTask(this, () -> player.sendMessage(randomMessage));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)  {
        if (args.length == 0)  {
            sender.sendMessage("§cUsage: /pjm <reload|list>");
            return false;
        }

        switch(args[0].toLowerCase()) {
            case "reload":
                if (!sender.hasPermission("privatejoinmessage.reload"))  {
                    sender.sendMessage("§cYou do not have permission to use this command.");
                    return true;
                }
                loadMessages();
                sender.sendMessage("§aPrivateJoinMessage reloaded.");
                break;

            case "list":
                if (!sender.hasPermission("privatejoinmessage.list"))  {
                    sender.sendMessage("§cYou do not have permission to view the join messages.");
                    return true;
                }

                if (messages == null || messages.isEmpty())  {
                    sender.sendMessage("§cNo join messages are currently set.");
                    return true;
                }

                sender.sendMessage("§aCurrent Join Messages:");
                for(String message : messages) {
                    sender.sendMessage("§7- " + message);
                }
                break;

            default:
                sender.sendMessage("§cUnknown subcommand. Usage: /pjm <reload|list>");
                break;
        }
        return true;
    }
}
