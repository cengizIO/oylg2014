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

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BirazDahaEklenti extends JavaPlugin {

    private final BirazDahaEklentiCommandExecutor commandExecutor
            = new BirazDahaEklentiCommandExecutor(this);
    private final BirazDahaEklentiEventListener eventListener
            = new BirazDahaEklentiEventListener(this);

    private Competition competition;

    public Competition getCompetition() {
        return competition;
    }

    public void onDisable() {
        // Şimdilik boş
    }

    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        getCommand("sureniz").setExecutor(commandExecutor);
        getCommand("hedefiniz").setExecutor(commandExecutor);
        getCommand("ben_de").setExecutor(commandExecutor);
        getCommand("kimler").setExecutor(commandExecutor);
        getCommand("basla").setExecutor(commandExecutor);

        pm.registerEvents(eventListener, this);
        competition = new Competition(this);

        competition.players.add("CraveSalad");
        competition.players.add("rexnebular");
    }
}
