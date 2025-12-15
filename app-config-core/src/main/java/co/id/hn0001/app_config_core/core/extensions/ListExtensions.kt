package co.id.hn0001.app_config_core.core.extensions


fun <T> List<T>.remove(data: T) = filter { it != data }

fun <T> List<T>.add(data: T) = this + data