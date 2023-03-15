package com.unongmilk.discord

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit

class EventController : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when (event.name) {
            "say" -> {
                event.reply(event.getOption("message")!!.asString).queue()
            }
        }
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.channel.idLong == 1084029944964849714L) {
            if (!event.message.author.isBot) {
                Bukkit.getOnlinePlayers().forEach{
                    it.sendMessage("[디스코드] ${event.message.author.name} : ${event.message.contentDisplay}")
                }
            }
        }
    }
}