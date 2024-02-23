package moe.taswell.rebirthchat

import java.util.Optional

object Resources {
    private val titleOnJoinKeyName: String = "messages.title.on_player_join"
    private val titleOnJoinDelayPerMsg: String = "messages.title.delay_per_msg"
    private val chatTooFastMsgKeyName: String = "messages.on_chat_too_fast"
    private val chatSpeedLimitPerSecKeyName: String = "chat_speed_limit_per_second"
    private val muteOnChattingTooFastKeyName: String = "mute_on_chatting_too_fast"
    private val muteTimeMillionsecondsKeyname: String = "mute_time_million_seconds"
    private val mutedMessageCausedByChattingToFast: String = "messages.on_muted_caused_by_chatting_too_fast"

    private var chatSpeedLimitPerSecValue: Int = Integer.MIN_VALUE
    private var muteOnChattingTooFastValue: Optional<Boolean> = Optional.empty()
    private var muteTimeMillisecondsValue: Optional<Long> = Optional.empty()

    fun getTitleMessagesAndDelay(): Pair<List<String>,Long>{
        val pluginConfig = PluginBootstrap.pluginInstance.config

        return pluginConfig.getStringList(this.titleOnJoinKeyName) to pluginConfig.getLong(this.titleOnJoinDelayPerMsg)
    }

    fun getMutedMessageCausedByChattingTooFast(): String{
        return PluginBootstrap.pluginInstance.config.getString(mutedMessageCausedByChattingToFast)!!
    }

    fun getChatToMessageNotice(): String{
        return PluginBootstrap.pluginInstance.config.getString(chatTooFastMsgKeyName)!!
    }

    fun getChatSpeedLimitPerSec(): Int{
        if (this.chatSpeedLimitPerSecValue == Int.MIN_VALUE){
            this.chatSpeedLimitPerSecValue = PluginBootstrap.pluginInstance.config.getInt(chatSpeedLimitPerSecKeyName)
        }

        return this.chatSpeedLimitPerSecValue
    }

    fun getMuteTimeMilliseconds(): Long{
        if (this.muteTimeMillisecondsValue.isPresent){
            this.muteTimeMillisecondsValue = Optional.of(PluginBootstrap.pluginInstance.config.getLong(muteTimeMillionsecondsKeyname))
        }

        return this.muteTimeMillisecondsValue.get()
    }

    fun getMuteOnChattingTooFast(): Boolean{
        if (this.muteOnChattingTooFastValue.isPresent){
            this.muteOnChattingTooFastValue = Optional.of(PluginBootstrap.pluginInstance.config.getBoolean(muteOnChattingTooFastKeyName))
        }

        return this.muteOnChattingTooFastValue.get()
    }
}