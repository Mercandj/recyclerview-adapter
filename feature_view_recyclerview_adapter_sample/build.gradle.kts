import com.mercandalli.build_src.main.Const

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mercandalli.android.apps.view_recyclerview_adapter_sample"
    compileSdk = Const.compileSdkVersion

    defaultConfig {
        applicationId = "com.mercandalli.android.apps.view_recyclerview_adapter_sample"
        minSdk = Const.minSdkVersion
        targetSdk = Const.targetSdkVersion
        versionCode = Const.featureVersionCode
        versionName = Const.featureVersionName
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        named("main") {
            // Split resources.
            // https://medium.com/google-developer-experts/android-project-structure-alternative-way-29ce766682f0#.sjnhetuhb
            res.setSrcDirs(
                setOf(
                    "src/main/res/animals_activity",
                    "src/main/res/application_icon",
                    "src/main/res/cat_row_view",
                    "src/main/res/dog_row_view",
                    "src/main/res/theme"
                )
            )
        }
    }

    // https://developer.android.com/studio/write/java8-support
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":feature_view_recyclerview_adapter"))

    // AndroidX
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
