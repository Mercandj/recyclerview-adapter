import com.mercandalli.build_src.main.Const

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    namespace = "com.mercandalli.android.sdk.view_recyclerview_adapter"
    compileSdk = Const.compileSdkVersion

    defaultConfig {
        minSdk = Const.minSdkVersion
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
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
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.mercandalli.android.sdk"
            artifactId = "recyclerview_adapter"
            version = Const.featureVersionName

            afterEvaluate { from(components["release"]) }
        }
    }
}

