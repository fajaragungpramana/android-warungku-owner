private object Version {
    const val GRADLE = "4.1.1"
    const val KOTLIN = "1.4.21"

    const val HILT = "2.28-alpha"
    const val HILT_VIEW_MODEL = "1.0.0-alpha02"

    const val NAVIGATION = "2.3.2"

    const val RETROFIT = "2.9.0"
}

object AndroidConfig {
    const val COMPILE_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.3"

    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30
    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.0.1"
}

object Classpath {
    const val GRADLE = "com.android.tools.build:gradle:${Version.GRADLE}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"

    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
}

object Dependencies {

    const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.2"
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.2.0"
        const val ACTIVITY = "androidx.activity:activity-ktx:1.1.0"
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
    }

    object Coroutines {
        const val MAIN = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    }

    object Dagger {
        const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
        const val HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.HILT_VIEW_MODEL}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
        const val HILT_VIEW_MODEL_COMPILER = "androidx.hilt:hilt-compiler:${Version.HILT_VIEW_MODEL}"
    }

    object Layout {
        const val MATERIAL_DESIGN = "com.google.android.material:material:1.2.1"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val OTP_VIEW = "com.github.aabhasr1:OtpView:v1.1.2"
        const val DOT_INDICATOR = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
        const val SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"
    }

    object SquareUp {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT}"
        const val HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:4.9.0"
        const val PICASSO = "com.squareup.picasso:picasso:2.71828"
    }

}