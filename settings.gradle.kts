pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "amro"
include(":app")
include(":data:repository:movies:movies-api")
include(":data:repository:movies:movies-impl")
include(":data:source:tmdb-api")
include(":data:source:tmdb-impl")
include(":domain:common")
include(":domain:home:home-api")
include(":domain:home:home-impl")
include(":ui:common:mvi")
include(":ui:common:theme")
include(":ui:feature:home")
