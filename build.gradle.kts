buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Libs.gradle)
        classpath(Libs.kotlinGradle)
        classpath(Libs.navSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
