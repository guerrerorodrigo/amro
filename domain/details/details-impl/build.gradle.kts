plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.amro.hilt)
}

android {
    namespace = "com.rodrigoguerrero.domain.details.impl"
    compileSdk {
        version = release(36)
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
    implementation(project(":domain:common"))
    implementation(project(":domain:details:details-api"))
    implementation(project(":data:repository:movies:movies-api"))

    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
