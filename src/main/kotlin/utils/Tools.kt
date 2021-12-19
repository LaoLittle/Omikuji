package org.laolittle.plugin.omikuji.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import org.laolittle.plugin.omikuji.Json
import org.laolittle.plugin.omikuji.Omikuji
import org.laolittle.plugin.omikuji.OmikujiInfo
import java.io.*

var omikujiInfo: Map<Int, OmikujiInfo> = linkedMapOf()

@ExperimentalSerializationApi
fun decodeJson() {
    omikujiInfo = Json.decodeFromString(selfReadText())
    /*val file = dataFolder.resolve("userrandom.json")
    if (!file.exists()) {
        file.createNewFile()
        return
    }
    userrandom = Json.decodeFromString(file.readText())*/
}

/*
@ExperimentalSerializationApi
fun encodeJson() {
    val writer = FileWriter(dataFolder.resolve("userrandom.json"))
    writer.write(Json.encodeToString(userrandom))
    writer.close()
}
*/
@ExperimentalSerializationApi
@Throws(IOException::class)
private fun selfReadText(): String {
    val input = Omikuji.javaClass.getResourceAsStream("/data/omikuji.json")
    return if (input != null) {
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        input.use {
            val reader: Reader = BufferedReader(
                InputStreamReader(input, "UTF-8")
            )
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }
        writer.toString()
    } else {
        ""
    }
}