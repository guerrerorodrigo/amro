plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.amro.hilt)
}

android {
    namespace = "com.rodrigoguerrero.domain.common"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform {
                includeEngines("junit-jupiter")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
