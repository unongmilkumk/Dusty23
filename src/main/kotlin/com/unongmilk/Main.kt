package com.unongmilk

import com.unongmilk.discord.EventController
import com.unongmilk.event.ChatEvent
import com.unongmilk.event.JoinQuitEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.plugin.java.JavaPlugin
import java.awt.Color

class Main : JavaPlugin() {
    companion object {
        lateinit var instance : Main
        var bot : JDA? = null

        fun sendMessage(color: Color, message : String) {
            bot?.getTextChannelById(1084029944964849714L)!!.sendMessageEmbeds(EmbedBuilder().setColor(color).setTitle(message).build()).queue()
        }
    }
    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        if (config.get("token") == null) {
            logger.info("Token이 없음!")
            server.pluginManager.disablePlugin(this)
            return
        }

        bot = JDABuilder.createDefault(config.get("token")!! as String)
            .setEnabledIntents(GatewayIntent.DIRECT_MESSAGE_REACTIONS,
            GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
            GatewayIntent.GUILD_INVITES, GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING,
            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MODERATION,
            GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.GUILD_WEBHOOKS, GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.SCHEDULED_EVENTS).build()
        bot!!.presence.setStatus(OnlineStatus.ONLINE)
        bot!!.presence.activity = Activity.playing("Dusty 개발")
        bot!!.addEventListener(EventController())
        bot!!.awaitReady()

        sendMessage(Color.GREEN, config.get("enable-msg") as String)

        bot!!.upsertCommand("say", "말을 합니다").addOption(OptionType.STRING, "message", "할 말 적어주세요").queue()

        server.pluginManager.registerEvents(ChatEvent(), this)
        server.pluginManager.registerEvents(JoinQuitEvent(), this)
        logger.info(config.get("enable-msg") as String)
    }

    override fun onDisable() {
        sendMessage(Color.RED, config.get("disable-msg") as String)
        logger.info(config.get("disable-msg") as String)
    }
}