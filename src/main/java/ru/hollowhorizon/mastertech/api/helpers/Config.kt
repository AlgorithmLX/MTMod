package ru.hollowhorizon.mastertech.api.helpers

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Config(val fileName: String, val dist: ConfigDist = ConfigDist.SERVER) {
    enum class ConfigDist {
        CLIENT, SERVER
    }
}
