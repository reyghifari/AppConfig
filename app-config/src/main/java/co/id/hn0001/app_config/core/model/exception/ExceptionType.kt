package co.id.hn0001.app_config.core.model.exception

sealed class ExceptionType(val type: String) {
    data object All : ExceptionType("all")
    data object MainApp : ExceptionType("main_app")
}
