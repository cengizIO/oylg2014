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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BirazDahaEklentiCommandExecutor implements CommandExecutor {

    private BirazDahaEklenti plugin;

    public BirazDahaEklentiCommandExecutor(BirazDahaEklenti plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
        String label, String[] args) {
        plugin.getLogger().info("BirazDahaEklenti için onCommand tetiklendi");
        return false;
    }
}
