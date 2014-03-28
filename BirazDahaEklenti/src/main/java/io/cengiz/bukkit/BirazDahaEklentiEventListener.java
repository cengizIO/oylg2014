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

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BirazDahaEklentiEventListener implements Listener {

    private BirazDahaEklenti plugin;

    public BirazDahaEklentiEventListener(BirazDahaEklenti plugin) {
        this.plugin = plugin;
    }

    // http://jd.bukkit.org/apidocs/

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Bukkit
            .getServer()
            .broadcastMessage(
                event.getPlayer().getName() + " bir BLOK yerleştirdi"
            );
    }
}
