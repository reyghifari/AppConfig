package co.id.hn0001.app_config.core.extensions

import co.id.hn0001.app_config.ui.theme.metrixHarmoniousColor

fun Int.harmoniousColor() = metrixHarmoniousColor[this % metrixHarmoniousColor.size]