package co.id.hn0001.app_config.core.model

sealed class MimeType(val value: String) {
    object PDF : MimeType("application/pdf")
    object JSON : MimeType("application/json")
    object Text : MimeType("text/plain")
}
