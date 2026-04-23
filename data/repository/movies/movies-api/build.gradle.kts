plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization.plugin)
}

android {
    namespace = "com.rodrigoguerrero.repository.movies.api"
    compileSdk {
        version = release(36)
    }
}

dependencies {

}
