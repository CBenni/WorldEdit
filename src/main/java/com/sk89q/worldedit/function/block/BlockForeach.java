/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.function.block;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.function.RegionFunction;

/**
 * Executes the given command for each block.
 */
public class BlockForeach implements RegionFunction {
    private String command;
    private String playername;

    /**
     * Create a new instance.
     *
     * @param extent an extent
     * @param pattern a pattern
     */
    public BlockForeach(String playername, String command) {
        this.command = command;
        this.playername = playername;
    }

    @Override
    public boolean apply(Vector position) throws WorldEditException {
        String cc = command;
        if(cc.startsWith("/"))cc = cc.substring(1);
        cc = cc.replaceAll("@x", Integer.toString(position.getBlockX()));
        cc = cc.replaceAll("@y", Integer.toString(position.getBlockY()));
        cc = cc.replaceAll("@z", Integer.toString(position.getBlockZ()));
        cc = cc.replaceAll("@p", playername);
        org.bukkit.Bukkit.getLogger().info("Executing command "+cc);
        org.bukkit.Bukkit.dispatchCommand(org.bukkit.Bukkit.getServer().getConsoleSender(), cc);
        return true;
    }

}