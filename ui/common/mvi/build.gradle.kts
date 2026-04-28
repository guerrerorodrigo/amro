plugins {
    alias(libs.plugins.amro.android.library)
}

android {
    namespace = "com.rodrigoguerrero.ui.common.mvi"
    testOptions {
        unitTests.all {
            it.useJUnitPlatform {
                includeEngines("junit-jupiter")
            }
        }
    }
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
