package co.id.hn0001.app_config_core.core.extensions

import co.id.hn0001.app_config_core.ui.theme.metrixHarmoniousColor

fun Int.harmoniousColor() = metrixHarmoniousColor[this % metrixHarmoniousColor.size]