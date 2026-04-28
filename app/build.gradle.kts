plugins {
    alias(libs.plugins.amro.android.app)
}

android {
    namespace = "com.rodrigoguerrero.amro"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
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
