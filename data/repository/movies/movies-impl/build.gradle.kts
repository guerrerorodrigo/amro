plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.repository.movies.impl"
}

dependencies {
    implementation(project(":data:repository:movies:movies-api"))
    implementation(project(":data:source:tmdb-api"))
}
