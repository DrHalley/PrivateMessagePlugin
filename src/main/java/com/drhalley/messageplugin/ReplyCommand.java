package com.drhalley.messageplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ReplyCommand implements CommandExecutor{

    private MessagePlugin messagePlugin;
    public ReplyCommand(MessagePlugin messagePlugin){
        this.messagePlugin = messagePlugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length >=1){
                UUID puuid = p.getUniqueId();

                if(messagePlugin.getRecentMessages().containsKey(puuid)){
                UUID uuid = messagePlugin.getRecentMessages().get(p.getUniqueId());
                if (Bukkit.getPlayer(uuid) != null){

                    Player receiver = Bukkit.getPlayer(uuid);
                    UUID ruuid = receiver.getUniqueId();
                    StringBuilder builder = new StringBuilder();
                    for(int i =0; i< args.length; i++){
                        builder.append(args[i]).append(" ");
                    }
                    p.sendMessage("You -> " + receiver.getDisplayName() + ": " + builder);
                    receiver.sendMessage(p.getDisplayName() + "-> You: " + builder);
                }else{
                    p.sendMessage(ChatColor.RED + "That player gone offline");
                }
                }else{
                    p.sendMessage(ChatColor.RED + "You haven't messaged someone yet");
                }
            }else{
                p.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message> or /m <player> <message>");
            }
        }
        return false;
    }
}
