package io.lostImagin4tion.financialHelper

object Dependencies {

    object JavaDesugaring {
        private const val VERSION = "2.0.0"

        const val DESUGARING = "com.android.tools:desugar_jdk_libs:2.0.0"
    }

    object AndroidCore {
        private const val APPCOMPAT_VERSION = "1.5.1"
        private const val CORE_VERSION = "1.9.0"
        private const val SPLASH_SCREEN_VERSION = "1.0.0"

        const val APPCOMPAT = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
        const val CORE = "androidx.core:core-ktx:$CORE_VERSION"
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:$SPLASH_SCREEN_VERSION"

        val ALL_DEPS = listOf(APPCOMPAT, CORE, SPLASH_SCREEN)
    }

    object Coroutines {
        private const val VERSION = "1.6.4"

        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object Compose {

        // ===========================================================================================
        // BE VERY CAREFUL WHEN UPDATING COMPOSE DEPENDENCIES, BECAUSE THEY CAN USE DIFFERENT VERSION!
        // ===========================================================================================

        const val COMPOSE_VERSION = "1.4.0"

        object Core {
            private const val ACTIVITY_COMPOSE_VERSION = "1.7.0"
            private const val CONSTRAINT_VERSION = "1.0.1"
            private const val MATERIAL3_VERSION = "1.1.0-beta01"

            const val UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
            const val UI_UTILS = "androidx.compose.ui:ui-util:$COMPOSE_VERSION"

            const val MATERIAL = "androidx.compose.material:material:$COMPOSE_VERSION"
            const val MATERIAL_YOU = "androidx.compose.material3:material3:$MATERIAL3_VERSION"

            const val MATERIAL_ICONS =
                "androidx.compose.material:material-icons-extended:$COMPOSE_VERSION"

            const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION"
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION" // debugImplementation

            const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:$ACTIVITY_COMPOSE_VERSION"

            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:$CONSTRAINT_VERSION"

            val ALL_CORE_DEPS = listOf(
                UI,
                UI_UTILS,
                MATERIAL,
                MATERIAL_YOU,
                MATERIAL_ICONS,
                UI_TOOLING_PREVIEW,
                ACTIVITY_COMPOSE,
                CONSTRAINT_LAYOUT
            )
            val ALL_CORE_DEBUG_DEPS = listOf(UI_TOOLING)
        }

        object Navigation {
            private const val VERSION = "2.5.3"

            const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:$VERSION"

            val ALL_NAVIGATION_DEPS = listOf(NAVIGATION_COMPOSE)
        }

        object Lifecycle {
            private const val LIFECYCLE_VERSION = "2.6.1"

            const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
            const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:$LIFECYCLE_VERSION"

            val ALL_LIFECYCLE_DEPS = listOf(RUNTIME, VIEW_MODEL)
        }

        object Foundation {

            const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:$COMPOSE_VERSION"
            const val COMPOSE_FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:$COMPOSE_VERSION"

            val ALL_FOUNDATION_DEPS = listOf(COMPOSE_FOUNDATION, COMPOSE_FOUNDATION_LAYOUT)
        }

        object Coil {
            private const val VERSION = "2.2.2"

            const val COIL_COMPOSE = "io.coil-kt:coil-compose:$VERSION"
            const val COIL_GIF = "io.coil-kt:coil-gif:$VERSION"

            val ALL_COIL_DEPS = listOf(COIL_COMPOSE, COIL_GIF)
        }

        object UiTest {

            // add to build.gradle with androidTestImplementation()
            const val UI_TEST = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"

            // add to build.gradle with debugImplementation
            const val UI_TEST_DEBUG = "androidx.compose.ui:ui-test-manifest:$COMPOSE_VERSION"
        }

        val ALL_DEPS =
            Core.ALL_CORE_DEPS + Lifecycle.ALL_LIFECYCLE_DEPS + Foundation.ALL_FOUNDATION_DEPS +
                Navigation.ALL_NAVIGATION_DEPS + Coil.ALL_COIL_DEPS
    }

    object UI {
        private const val CONSTRAINT_LAYOUT_VERSION = "2.1.4"
        private const val MATERIAL_VERSION = "1.7.0"

        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
        const val CORE = "com.google.android.material:material:$MATERIAL_VERSION"

        val ALL_DEPS = listOf(CONSTRAINT_LAYOUT, CORE)
    }

    object Lottie {
        private const val VERSION = "6.0.0"

        const val LOTTIE = "com.airbnb.android:lottie-compose:$VERSION"

        val ALL_DEPS = listOf(LOTTIE)
    }

    object Charts {
        private const val VICO_VERSION = "1.6.5"
        private const val PIE_CHART_VERSION = "0.2.4-alpha"

        const val VICO_CORE = "com.patrykandpatrick.vico:core:$VICO_VERSION"
        const val VICO_COMPOSE = "com.patrykandpatrick.vico:compose:$VICO_VERSION"
        const val VICO_MATERIAL = "com.patrykandpatrick.vico:compose-m3:$VICO_VERSION"

        const val PIE_CHART = "com.github.tehras:charts:$PIE_CHART_VERSION"

        val ALL_DEPS = listOf(VICO_CORE, VICO_COMPOSE, VICO_MATERIAL, PIE_CHART)
    }

    object Dagger {
        private const val VERSION = "2.44"

        const val ANDROID = "com.google.dagger:dagger-android:$VERSION"
        const val COMPILER = "com.google.dagger:dagger-compiler:$VERSION"
        const val PROCESSOR = "com.google.dagger:dagger-android-processor:$VERSION"

        val KAPT_DEPS = listOf(COMPILER, PROCESSOR)
    }

    object Room {
        private const val VERSION = "2.4.3"

        const val ROOM_KTX = "androidx.room:room-ktx:$VERSION"
        const val ROOM_RUNTIME = "androidx.room:room-runtime:$VERSION"
        const val COMPILER = "androidx.room:room-compiler:$VERSION"

        val ALL_DEPS = listOf(ROOM_KTX, ROOM_RUNTIME)
        val KAPT_DEPS = listOf(COMPILER)
    }

    object Logger {
        private const val TIMBER_VERSION = "5.0.1"

        const val TIMBER = "com.jakewharton.timber:timber:$TIMBER_VERSION"
    }

    object Network {
        private const val VERSION = "2.9.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:$VERSION"

        val ALL_DEPS = listOf(RETROFIT, MOSHI_CONVERTER)
    }

    object Test {
        private const val JUNIT_VERSION = "1.1.3"
        private const val MOCKK_VERSION = "1.13.2"

        const val JUNIT = "androidx.test.ext:junit:$JUNIT_VERSION"
        const val TEST_JUNIT = "test-junit"
        const val MOCKK = "io.mockk:mockk:$MOCKK_VERSION"
    }

    object Plugins {
        const val DETEKT_VERSION = "1.22.0"

        const val DETEKT_FORMATTING =
            "io.gitlab.arturbosch.detekt:detekt-formatting:$DETEKT_VERSION"
    }
}
