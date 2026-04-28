plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.ui.common.mvi"
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}
