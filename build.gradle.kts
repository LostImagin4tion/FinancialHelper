plugins {
    id("com.android.application") version "7.1.3" apply false
    id("com.android.library") version "7.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("io.gitlab.arturbosch.detekt") version io.lostImagin4tion.financialHelper.Dependencies.Plugins.DETEKT_VERSION
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${io.lostImagin4tion.financialHelper.CompileVersions.KOTLIN_VERSION}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

tasks {
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        exclude("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        exclude("**/**est**")
        config.setFrom(files("${rootDir}/.detekt/detekt-config.yml"))
        buildUponDefaultConfig = false
        ignoreFailures = false
        autoCorrect = true
        dependencies {
            detektPlugins(io.lostImagin4tion.financialHelper.Dependencies.Plugins.DETEKT_FORMATTING)
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
