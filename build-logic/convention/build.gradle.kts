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
    compileOnly(libs.screenshot.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("amroAndroidLibrary") {
            id = "com.guerrerorodrigo.amro.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("amroAndroidUiTest") {
            id = "com.guerrerorodrigo.amro.android.ui.test"
            implementationClass = "AndroidUiTestPlugin"
        }
        register("amroApiLibrary") {
            id = "com.guerrerorodrigo.amro.api.library"
            implementationClass = "ApiLibraryPlugin"
        }
        register("amroHilt") {
            id = "com.guerrerorodrigo.amro.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("amroScreenshotTest") {
            id = "com.guerrerorodrigo.amro.screenshot.test"
            implementationClass = "ScreenshotTestPlugin"
        }
        register("amroUnitTest") {
            id = "com.guerrerorodrigo.amro.unit.test"
            implementationClass = "UnitTestPlugin"
        }
    }
}
