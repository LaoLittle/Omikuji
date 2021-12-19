package org.laolittle.plugin.omikuji.utils

import net.mamoe.mirai.message.data.ForwardMessage
import net.mamoe.mirai.message.data.RawForwardMessage
import org.laolittle.plugin.omikuji.OmikujiInfo


class CustomForwardMsgDisplay(private val omikujiInfo: OmikujiInfo) : ForwardMessage.DisplayStrategy {
    override fun generateTitle(forward: RawForwardMessage): String = "群聊的聊天记录"

    override fun generateBrief(forward: RawForwardMessage): String = "[求签结果]"

    override fun generateSummary(forward: RawForwardMessage): String = omikujiInfo.order

    override fun generatePreview(forward: RawForwardMessage): List<String> = listOf("\n")
}