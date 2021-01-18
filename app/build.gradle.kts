plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId("com.implizstudio.android.app.androidwarungkuowner")
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        versionCode(AndroidConfig.VERSION_CODE)
        versionName(AndroidConfig.VERSION_NAME)

        buildConfigField("String", "BASE_URL", rootProject.property("app.base_url") as String)
        buildConfigField("String", "ACCESS_KEY", rootProject.property("app.access_key") as String)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures.viewBinding = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dynamicFeatures = mutableSetOf(":auth", ":dashboard", ":intro", ":product")
}

dependencies {

    implementation(project(":resources"))

    api(project(":components"))

    implementation(Dependencies.AndroidX.ACTIVITY)
    api(Dependencies.AndroidX.NAVIGATION_UI)
    api(Dependencies.AndroidX.NAVIGATION_FRAGMENT)

    api(Dependencies.Coroutines.MAIN)

    implementation(Dependencies.Dagger.HILT)
    implementation(Dependencies.Dagger.HILT_VIEW_MODEL)
    kapt(Dependencies.Dagger.HILT_COMPILER)
    kapt(Dependencies.Dagger.HILT_VIEW_MODEL_COMPILER)

    api(Dependencies.Layout.MATERIAL_DESIGN)
    api(Dependencies.Layout.CONSTRAINT_LAYOUT)
    api(Dependencies.Layout.SWIPE_REFRESH)

    implementation(Dependencies.SquareUp.RETROFIT)
    implementation(Dependencies.SquareUp.GSON_CONVERTER)
    implementation(Dependencies.SquareUp.HTTP_LOGGING)

}