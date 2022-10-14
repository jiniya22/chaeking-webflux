package me.jiniworld.book.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.jiniworld.book.util.DateTimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(value: LocalDate?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.format(DateTimeUtils.FORMATTER_DATE))
    }
}
class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate {
        return LocalDate.parse(p?.text, DateTimeUtils.FORMATTER_DATE)
    }
}
class LocalDateTimeSerializer : StdSerializer<LocalDateTime>(LocalDateTime::class.java) {
    override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.format(DateTimeUtils.FORMATTER_DATETIME))
    }
}
class LocalDateTimeDeserializer : StdDeserializer<LocalDateTime>(LocalDateTime::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        return LocalDateTime.parse(p?.text, DateTimeUtils.FORMATTER_DATETIME)
    }
}
class LocalTimeSerializer : StdSerializer<LocalTime>(LocalTime::class.java) {
    override fun serialize(value: LocalTime?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.format(DateTimeUtils.FORMATTER_TIME))
    }
}
class LocalTimeDeserializer : StdDeserializer<LocalTime>(LocalTime::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalTime {
        return LocalTime.parse(p?.text, DateTimeUtils.FORMATTER_TIME)
    }
}