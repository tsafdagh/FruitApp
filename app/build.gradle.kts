plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.code.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.code.demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isDebuggable = true
            applicationIdSuffix = ".dev"

            resValue(
                "string",
                "app_name",
                "Dev Fruit App"
            )
        }

        create("staging") {
            isDebuggable = true
            applicationIdSuffix = ".stag"

            resValue(
                "string",
                "app_name",
                "Stag Fruit App"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        create("recette") {
            isDebuggable = true
            applicationIdSuffix = ".rect"

            resValue(
                "string",
                "app_name",
                "Recet Fruit App"
            )
            signingConfig = signingConfigs.getByName("debug")
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.runtime.livedata)
    implementation(libs.tabsync.compose)
    implementation(libs.coil.kt.coil.compose)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("org.mockito:mockito-inline:3.+")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}