pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")

val flutterProjectRoot = rootProject.projectDir.parentFile.toPath()

val plugins = java.util.Properties()
val pluginsFile = flutterProjectRoot.resolve(".flutter-plugins").toFile()
if (pluginsFile.exists()) {
    pluginsFile.inputStream().use { plugins.load(it) }
}

plugins.forEach { name, path ->
    val pluginDirectory = flutterProjectRoot.resolve(path.toString()).resolve("android").toFile()
    include(":$name")
    project(":$name").projectDir = pluginDirectory
}
