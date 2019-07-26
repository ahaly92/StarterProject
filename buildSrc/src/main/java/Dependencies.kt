import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object Versions {
    object Android {
        const val compileSdkVersion = 28
        const val minSdkVersion = 23
        const val targetSdkVersion = 28
        const val appVersionCode = 1
        const val appVersionName = "1.0"
        const val applicationId = "com.yeetsies.starterproject"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    const val gradle = "3.4.1"

    const val kotlin = "1.3.11"
    const val appCompat = "1.0.2"
    const val constraintLayout = "1.1.1"
    const val coroutines = "1.3.0-M1"
    const val mockitoKotlinLib = "2.0.0"

    const val lifecycle_version = "2.1.0-beta01"
    const val jetPackNavigation = "1.0.0"
    const val navSafeArgs = "1.0.0"

    const val timber = "4.7.1"

    const val retrofit = "2.6.0"
    const val okhttp = "3.11.0"
    const val gson = "2.8.5"

    const val rxJava = "2.1.8"
    const val rxAndroid = "2.0.1"

    const val junit = "4.12"
    const val testRunner = "1.1.0"
    const val espresso = "3.1.0"
    const val coreTesting = "2.0.1"
    const val mockito = "2.23.4"
}

object Libs {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val navSafeArgs = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navSafeArgs}"
    const val jetPackNavigationFragment = "android.arch.navigation:navigation-fragment:${Versions.jetPackNavigation}"
    const val jetPackNavigationUI = "android.arch.navigation:navigation-ui-ktx:${Versions.jetPackNavigation}"

    const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle_version}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlinLib = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinLib}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

fun Project.baseDependencies() {
    dependencies {
        "implementation"(fileTree("libs") { include("*.jar") })

        //android
        "implementation"(Libs.kotlin)
        "implementation"(Libs.appCompat)
        "implementation"(Libs.constraintLayout)

        // navigation
        "implementation"(Libs.jetPackNavigationFragment)
        "implementation"(Libs.jetPackNavigationUI)
        "implementation"(Libs.coroutines)

        // mvvm
        "implementation"(Libs.lifeCycle)
        "implementation"(Libs.viewModel)
        "implementation"(Libs.liveData)

        // logging
        "implementation"(Libs.timber)

        // networking
        "implementation"(Libs.retrofit)
        "implementation"(Libs.retrofitGson)
        "implementation"(Libs.retrofitRxAdapter)
        "implementation"(Libs.gson)
        "implementation"(Libs.okhttp)
        "implementation"(Libs.okhttpLogging)

        // concurrency
        "implementation"(Libs.rxJava)
        "implementation"(Libs.rxAndroid)


        // testing
        "testImplementation"(Libs.junit)
        "testImplementation"(Libs.mockito)
        "testImplementation"(Libs.mockitoKotlinLib)
        "testImplementation"(Libs.coreTesting)
        "androidTestImplementation"(Libs.testRunner)
        "androidTestImplementation"(Libs.espresso)
    }
}
