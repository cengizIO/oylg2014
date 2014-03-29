/*
    This file is part of BirazDahaEklenti

    BirazDahaEklenti is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BirazDahaEklenti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with BirazDahaEklenti. If not, see <http://www.gnu.org/licenses/>.
 */

package io.cengiz.bukkit;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BirazDahaEklentiCommandExecutor implements CommandExecutor {

    private final BirazDahaEklenti plugin;
    private final Server server;

    public BirazDahaEklentiCommandExecutor(BirazDahaEklenti plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
        String label, String[] args) {
        Competition competition = plugin.getCompetition();

        switch (command.getName()) {

        case "sureniz":
            competition.setMaxAllowedSeconds(Integer.valueOf(args[0]));
            server.broadcastMessage(
                    "İzin verilen süre: "
                    + competition.getMaxAllowedSeconds()
                    + " saniye");
            break;

        case "hedefiniz":
            competition.setTargetBlock(Material.matchMaterial(args[0]));
            server
                .broadcastMessage(
                    "Hedef blok: "
                    + competition.getTargetBlock().toString());
            break;

        case "ben_de":
            String participantName = sender.getName();
            if (participantName.equals("")
                    || participantName.equals("CONSOLE")) {
                sender.sendMessage("Sana yok!");
                return true;
            }
            if (! competition.players.add(participantName)) {
                sender.sendMessage("Oyuna zaten katışmışsınız");
            } else {
                server.broadcastMessage(participantName + " oyuna katıldı!");
            }
            break;

        case "kimler":
            if (competition.players.size() < 1) {
                sender.sendMessage("Henüz kimseler katılmamış");
            } else {
                sender.sendMessage("Katılımcıların adları şöyle: "
                        + competition.players.toString());
            }
            break;

        case "basla":
            competition.go();
            break;
        }


        return true;
    }
}
