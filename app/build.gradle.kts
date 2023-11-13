import com.foundation.kts.Dependencies
import com.foundation.kts.Statics

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.foundation.example"
        minSdk = 21

        resValue("string", "app_name", Statics.APP_NAME)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlin_stdlib)
    implementation(Dependencies.AndroidX.core_ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.activity)

    implementation(project(":widget"))
}
configurations.all {
    resolutionStrategy {
        // don't cache changing modules at all
        cacheChangingModulesFor(10, "seconds")
    }
}