plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
}

android {
    namespace = "com.rodrigoguerrero.domain.home.impl"
    testOptions {
        unitTests.all {
            it.useJUnitPlatform {
                includeEngines("junit-jupiter")
            }
        }
    }
}

dependencies {
    implementation(project(":domain:common"))
    implementation(project(":domain:home:home-api"))
    implementation(project(":data:repository:movies:movies-api"))

    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
