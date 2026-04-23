plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization.plugin)
}

android {
    namespace = "com.rodrigoguerrero.domain.home.api"
    compileSdk {
        version = release(36)
    }
}
