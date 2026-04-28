import com.android.build.api.dsl.LibraryExtension
import com.guerrerorodrigo.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class UnitTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests.all { test ->
                        test.useJUnitPlatform {
                            includeEngines("junit-jupiter")
                        }
                    }
                }
            }

            tasks.withType<Test> {
                useJUnitPlatform()
            }

            dependencies {
                add("testImplementation", libs.findBundle("junit").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("turbine").get())
            }
        }
    }
}
