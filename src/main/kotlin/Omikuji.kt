package org.laolittle.plugin.omikuji

import kotlinx.serialization.ExperimentalSerializationApi
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildForwardMessage
import net.mamoe.mirai.utils.info
import org.laolittle.plugin.omikuji.utils.*
import java.time.LocalDate
import kotlin.random.Random

@ExperimentalSerializationApi
object Omikuji : KotlinPlugin(
    JvmPluginDescription(
        id = "org.laolittle.plugin.omikuji",
        version = "1.0",
            name = "Omikuji"
    )
) {
    override fun onEnable() {
        decodeJson()
        logger.info { "加载完成" }
        GlobalEventChannel.subscribeMessages {
            finding(Regex("求签")) {
                val date = LocalDate.now()
                val accdate = "${date.year}${date.monthValue}${date.dayOfMonth}".toLong()
                val random = Random(sender.id + accdate).nextInt(0, 40)
                omkj = omikujiInfo[random]
                userrandom = userrandom.plus(Pair(sender.id, accdate))
                val forwardMessage = buildForwardMessage(
                    displayStrategy = CustomForwardMsgDisplay
                ) {
                    add(sender, PlainText(omkj?.first.toString()))
                    add(sender, PlainText(omkj?.second.toString()))
                    add(sender, PlainText(omkj?.third.toString()))
                    add(sender, PlainText(omkj?.fourth.toString()))
                    add(sender, PlainText(omkj?.attr.toString()))
                }
                subject.sendMessage(forwardMessage)
            }
        }
    }

    override fun onDisable() {
        logger.info { "卸载完成" }
    }
}