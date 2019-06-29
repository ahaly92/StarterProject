plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        versionCode = Versions.Android.appVersionCode
        versionName = Versions.Android.appVersionName
        applicationId = Versions.Android.applicationId
        testInstrumentationRunner = Versions.Android.testInstrumentationRunner
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../debug.keystore")
        }

        create("release") {
            val fileName = getLocalProperty("signing_file")
            if (fileName.isNotEmpty()) {
                storeFile = file(fileName)
                storePassword = getLocalProperty("signing_password")
                keyAlias = getLocalProperty("signing_alias")
                keyPassword = getLocalProperty("signing_key_password")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }

        create("qa") {
            initWith(buildTypes.getByName("debug"))
            applicationIdSuffix = ".qa"
            matchingFallbacks = listOf("debug")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

baseDependencies()
