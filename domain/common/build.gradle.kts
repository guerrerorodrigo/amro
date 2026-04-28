plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.domain.common"
}

dependencies {
    implementation(libs.kotlinx.datetime)
}
