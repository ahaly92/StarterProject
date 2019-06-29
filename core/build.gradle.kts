plugins {
    id("com.android.library")
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
        testInstrumentationRunner = Versions.Android.testInstrumentationRunner
    }


    buildTypes {
        getByName("debug") {
            
        }

        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

baseDependencies()
