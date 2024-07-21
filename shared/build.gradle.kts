import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id(libs.plugins.kotlinx.serialization.get().pluginId)
    id("com.google.devtools.ksp") version libs.versions.ksp.get()
}

kotlin {
    androidTarget()
    iosArm64()
    iosSimulatorArm64()

    val xcf = XCFramework()
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib.common)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
                implementation(libs.koin.core)
                implementation(libs.firebase.auth)
                implementation(libs.firebase.firestore)
                implementation(libs.kmp.nativecoroutines.core)
                implementation(libs.kmp.nativecoroutines.annotations)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.firebase.auth.ktx)
                implementation(libs.firebase.firestore.ktx)
            }
        }
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
    tasks.register("testClasses") {
        dependsOn(
            sourceSets["commonTest"].kotlin.sourceDirectories,
            sourceSets["androidUnitTest"].kotlin.sourceDirectories,
            sourceSets["iosArm64Test"].kotlin.sourceDirectories,
            sourceSets["iosSimulatorArm64Test"].kotlin.sourceDirectories
        )
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

android {
    namespace = "com.meetagain.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    //add("kspCommonMain", "com.rickclephas.kmp:kmp-nativecoroutines-processor:1.0.0-ALPHA-32")
    //add("kspAndroid", "com.rickclephas.kmp:kmp-nativecoroutines-ksp:1.0.0-ALPHA-32")
    add("kspIosArm64", "com.rickclephas.kmp:kmp-nativecoroutines-ksp:1.0.0-ALPHA-32")
    add("kspIosSimulatorArm64", "com.rickclephas.kmp:kmp-nativecoroutines-ksp:1.0.0-ALPHA-32")
}

//kotlin.sourceSets["commonMain"].kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
//kotlin.sourceSets["androidMain"].kotlin.srcDir("build/generated/ksp/androidMain/kotlin")
kotlin.sourceSets["iosArm64Main"].kotlin.srcDir("build/generated/ksp/iosArm64Main/kotlin")
kotlin.sourceSets["iosSimulatorArm64Main"].kotlin.srcDir("build/generated/ksp/iosSimulatorArm64Main/kotlin")

ksp {
    arg("nativeCoroutines.suffix", "Native") // or any other value you prefer
    arg("nativeCoroutines.stateSuffix", "State") // or any other value you prefer
}