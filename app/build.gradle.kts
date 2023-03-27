import io.lostImagin4tion.financialHelper.CompileVersions
import io.lostImagin4tion.financialHelper.Dependencies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = io.lostImagin4tion.financialHelper.CompileVersions.CURRENT_COMPILE_VERSION
    namespace = "io.lostImagin4tion.financialHelper"

    defaultConfig {
        applicationId = namespace!!
        minSdk = io.lostImagin4tion.financialHelper.CompileVersions.MINIMUM_COMPILE_VERSION
        targetSdk = io.lostImagin4tion.financialHelper.CompileVersions.CURRENT_COMPILE_VERSION
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = io.lostImagin4tion.financialHelper.CompileVersions.JAVA_COMPILE_VERSION
        targetCompatibility = io.lostImagin4tion.financialHelper.CompileVersions.JAVA_COMPILE_VERSION
    }

    kotlinOptions {
        jvmTarget = io.lostImagin4tion.financialHelper.CompileVersions.JVM_VERSION
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    coreLibraryDesugaring(io.lostImagin4tion.financialHelper.Dependencies.JavaDesugaring.DESUGARING)

    // Android Core
    io.lostImagin4tion.financialHelper.Dependencies.AndroidCore.ALL_DEPS.forEach { implementation(it) }

    // Coroutines
    implementation(io.lostImagin4tion.financialHelper.Dependencies.Coroutines.ANDROID)

    // Compose
    io.lostImagin4tion.financialHelper.Dependencies.Compose.ALL_DEPS.forEach { implementation(it) }
    io.lostImagin4tion.financialHelper.Dependencies.Compose.Core.ALL_CORE_DEBUG_DEPS.forEach { debugImplementation(it) }

    // UI components
    io.lostImagin4tion.financialHelper.Dependencies.UI.ALL_DEPS.forEach { implementation(it) }

    // Dagger
    implementation(io.lostImagin4tion.financialHelper.Dependencies.Dagger.ANDROID)
    io.lostImagin4tion.financialHelper.Dependencies.Dagger.KAPT_DEPS.forEach { kapt(it) }

    // Network
    io.lostImagin4tion.financialHelper.Dependencies.Network.ALL_DEPS.forEach { implementation(it) }

    // Logger
    implementation(io.lostImagin4tion.financialHelper.Dependencies.Logger.TIMBER)
}