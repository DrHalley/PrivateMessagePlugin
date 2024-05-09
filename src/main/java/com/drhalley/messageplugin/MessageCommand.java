package com.drhalley.messageplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MessageCommand implements CommandExecutor {

    private MessagePlugin messagePlugin;
    public MessageCommand(MessagePlugin messagePlugin){
        this.messagePlugin = messagePlugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            System.out.println(p.getUniqueId());
            if(args.length >= 2){
                if(Bukkit.getPlayerExact(args[0]) != null){
                    Player receiver = Bukkit.getPlayerExact(args[0]);
                    StringBuilder builder = new StringBuilder();
                    for(int i =1; i< args.length; i++){
                        builder.append(args[i]).append(" ");
                    }
                    p.sendMessage("You -> " + receiver.getDisplayName() + ": " + builder);
                    receiver.sendMessage(p.getDisplayName() + "-> You: " + builder);
                    UUID puuid = p.getUniqueId();
                    UUID ruuid = receiver.getUniqueId();
                    messagePlugin.getRecentMessages().put(puuid, ruuid);
                }else{
                    p.sendMessage(ChatColor.RED + "There is no player with that name!");
                }
            }else{
                p.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message> or /m <player> <message>");
            }
        }
        return false;
    }
}
