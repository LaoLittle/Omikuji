package org.laolittle.plugin.omikuji

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class OmikujiInfo(
    @SerialName("order") val order: String,
    @SerialName("first") val first: String,
    @SerialName("second") val second: String,
    @SerialName("third") val third: String,
    @SerialName("fourth") val fourth: String,
    @SerialName("attr") val attr: String
)

internal val Json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    isLenient = true
    allowStructuredMapKeys = true
}