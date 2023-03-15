package com.unongmilk.event

import com.unongmilk.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.awt.Color

class JoinQuitEvent : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        Main.sendMessage(Color.GREEN, "${event.player.name}님이 접속했습니다")
    }
    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        Main.sendMessage(Color.RED, "${event.player.name}님이 퇴장했습니다")
    }
}