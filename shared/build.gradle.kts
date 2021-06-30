import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.dmitry.molchanov.kmmjsonxmlparsesample.db"
        sourceFolders = listOf("sqldelight")
    }
    linkSqlite = true
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val sqlDelightVersion = "1.5.0"
        val ktorVersion = "1.6.0"
        val serializationVersion = "1.2.1"
        val coroutinesVersion = "1.4.3-native-mt" // native-mt поддерживает многопоточность корутин в нативе
        val xmlSerializerVersion = "0.82.0"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                // ktor
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                //sqldelight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")


                // project ':shared': Could not find core-0.82.0-samplessources.jar (io.github.pdvrieze.xmlutil:core:0.82.0).
                //implementation("io.github.pdvrieze.xmlutil:core:$xmlSerializerVersion")
                //implementation("io.github.pdvrieze.xmlutil:serialization-android:$xmlSerializerVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

                // xmlutil
                implementation("io.github.pdvrieze.xmlutil:core:$xmlSerializerVersion")
                implementation("io.github.pdvrieze.xmlutil:serialization-android:$xmlSerializerVersion")

                // ktor
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                // sqldelight
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                // ktor
                implementation("io.ktor:ktor-client-ios:$ktorVersion")

                // sqldelight
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosTest by getting
    }
}


android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
}