import com.guerrerorodrigo.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.guerrerorodrigo.amro.android.library")
                apply("com.guerrerorodrigo.amro.hilt")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.guerrerorodrigo.amro.android.ui.test")
                apply("com.guerrerorodrigo.amro.screenshot.test")
                apply("com.guerrerorodrigo.amro.unit.test")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", project(":ui:common:mvi"))
                add("implementation", project(":ui:common:theme"))
                add("implementation", libs.findLibrary("hilt-navigation-compose").get())
                add("implementation", platform(bom))
                add("implementation", libs.findBundle("compose").get())
                add("implementation", libs.findLibrary("kotlinx-collections-immutable").get())
                add("implementation", libs.findLibrary("coil-compose").get())
            }
        }
    }
}
