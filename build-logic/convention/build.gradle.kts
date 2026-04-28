plugins {
    `kotlin-dsl`
}

group = "com.guerrerorodrigo.build-logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("amroApiLibrary") {
            id = "com.guerrerorodrigo.amro.api.library"
            implementationClass = "ApiLibraryPlugin"
        }
        register("amroHilt") {
            id = "com.guerrerorodrigo.amro.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}
