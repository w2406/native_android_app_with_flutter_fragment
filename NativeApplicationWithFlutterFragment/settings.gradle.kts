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
        // 追加
        maven("https://storage.googleapis.com/download.flutter.io")
        maven(url = "../flutter_module/build/host/outputs/repo")
    }
}

rootProject.name = "NativeApplicationWithFlutterFragment"
include(":app")
 