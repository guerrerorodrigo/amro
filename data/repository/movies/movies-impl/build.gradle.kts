plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rodrigoguerrero.repository.movies.impl"
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
    implementation(project(":data:repository:movies:movies-api"))
    implementation(project(":data:source:tmdb-api"))

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
