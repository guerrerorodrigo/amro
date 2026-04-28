plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization.plugin)
    alias(libs.plugins.screenshot)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.ui.feature.details"
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    implementation(project(":domain:details:details-api"))
    implementation(project(":ui:common:mvi"))
    implementation(project(":ui:common:theme"))

    implementation(libs.hilt.navigation.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.coil.compose)

    screenshotTestImplementation(libs.screenshot.validation.api)
    screenshotTestImplementation(libs.androidx.compose.ui.tooling)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
