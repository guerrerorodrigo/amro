plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

android {
    namespace = "com.rodrigoguerrero.amro"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.rodrigoguerrero.amro"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.compose.navigation)

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(project(":data:repository:movies:movies-api"))
    implementation(project(":data:repository:movies:movies-impl"))
    implementation(project(":data:source:tmdb-api"))
    implementation(project(":data:source:tmdb-impl"))
    implementation(project(":domain:common"))
    implementation(project(":domain:home:home-api"))
    implementation(project(":domain:home:home-impl"))
    implementation(project(":domain:details:details-api"))
    implementation(project(":domain:details:details-impl"))
    implementation(project(":ui:common:mvi"))
    implementation(project(":ui:common:theme"))
    implementation(project(":ui:feature:details"))
    implementation(project(":ui:feature:home"))
}
