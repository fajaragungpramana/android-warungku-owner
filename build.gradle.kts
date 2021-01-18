buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpath.GRADLE)
        classpath(Classpath.KOTLIN)
        classpath(Classpath.HILT)
    }
}

allprojects {
    repositories {
        google()
        jcenter()

        maven("https://jitpack.io")
    }
}

tasks {
    registering(Delete::class) {
        delete(buildDir)
    }
}