pluginManagement {
    repositories {
        google()
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

rootProject.name = "Intent Practice App"
include(":intent-app")
include(":widgeta-pp")
include(":receiver-app")
include(":sender-app")
include(":nested-navigation-app")
