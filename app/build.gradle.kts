import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("maven-publish")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

android {
    namespace = "com.example.testapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testapp"
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

mavenPublishing{
    configure(
        AndroidSingleVariantLibrary(
        // the published variant
        variant = "release",
        // whether to publish a sources jar
        sourcesJar = true,
        // whether to publish a javadoc jar
        publishJavadocJar = true,
    )
    )
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    coordinates("com.github.PratibhaCD", "TestMaven", "1.0.2")

    pom {
        name.set("TestMavenLib")
        description.set("A description of what my library does.")
        inceptionYear.set("2024")
        url.set("https://github.com/PratibhaCD/TestMaven")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("PratibhaCD")
                name.set("PratibhaCD")
                url.set("https://github.com/username/")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/PratibhaCD/TestMaven.git")
            developerConnection.set("scm:git:ssh://github.com:PratibhaCD/TestMaven.git")
            url.set("https://github.com/PratibhaCD/TestMaven")
        }
    }

    signAllPublications()
}

//publishing {
//    publications {
//        create<MavenPublication>("mavenCentral") {
////            from components.java
//
//            groupId = "com.github.PratibhaCD"
//            artifactId = "TestMaven"
//            version = "1.0.0"
//
//            pom {
//                name = "TestMaven"
//                description = "Test maven central project"
//                url = "https://github.com/PratibhaCD/TestMaven"
//
//                licenses {
//                    license {
//                        name = "The Apache License, Version 2.0"
//                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
//                    }
//                }
//
//                developers {
//                    developer {
//                        id = "PratibhaCD"
//                        name = "PratibhaCD"
//                        email = "pratibhasharma@countrydelight.in"
//                    }
//                }
//
//                scm {
//                    connection.set("scm:git:git://github.com/PratibhaCD/TestMaven.git")
//                    developerConnection.set("scm:git:ssh://github.com:PratibhaCD/TestMaven.git")
//                    url.set("https://github.com/PratibhaCD/TestMaven")
//                }
//            }
//        }
//    }
//}


dependencies {
    implementation(project(":TestAppLib"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}