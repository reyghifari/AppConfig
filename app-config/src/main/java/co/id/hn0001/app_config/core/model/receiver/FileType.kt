package co.id.hn0001.app_config.core.model.receiver

sealed class FileType() {
    data object HAR : FileType()
    data object Other : FileType()
}