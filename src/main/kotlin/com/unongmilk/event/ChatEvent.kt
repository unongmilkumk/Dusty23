package com.unongmilk.event

import com.unongmilk.Main
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import java.awt.Color

class ChatEvent : Listener {
    @EventHandler
    fun onChat(event : PlayerChatEvent) {
        event.isCancelled = true
        Bukkit.broadcastMessage("${event.player.name} : ${event.message}")
        Main.sendMessage(Color.WHITE, "${event.player.name} : ${event.message}")
    }
}