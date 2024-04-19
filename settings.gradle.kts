// https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3
includeBuild("build_src")

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "recyclerview-adapter"
include(
    ":feature_view_recyclerview_adapter",
    ":feature_view_recyclerview_adapter_sample"
)
