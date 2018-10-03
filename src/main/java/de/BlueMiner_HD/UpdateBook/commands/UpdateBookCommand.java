package de.BlueMiner_HD.UpdateBook.commands;

import de.BlueMiner_HD.UpdateBook.Methoden.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UpdateBookCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("updatebook.reload")) {
            sender.sendMessage("§cKeine Rechte");
            return false;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                sender.sendMessage("§7Lade Config neu...");
                config.loadconfig();
                sender.sendMessage("§aConfig wurde neu geladen");
            }

        } else {
            sendhelp(sender);
        }


        return false;
    }

    private void sendhelp(CommandSender sender) {
        sender.sendMessage("§7/updatebook reload");
    }
}
