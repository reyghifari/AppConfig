package co.id.hn0001.app_config_core.core.model.receiver

sealed class FileType() {
    data object HAR : FileType()
    data object Other : FileType()
}