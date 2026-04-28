import com.android.build.api.dsl.LibraryExtension
import com.guerrerorodrigo.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ScreenshotTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.compose.screenshot")
            }

            extensions.configure<LibraryExtension> {
                experimentalProperties["android.experimental.enableScreenshotTest"] = true
            }

            dependencies {
                add("screenshotTestImplementation", libs.findLibrary("screenshot-validation-api").get())
                add("screenshotTestImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            }
        }
    }
}
