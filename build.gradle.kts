// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.homesoft.h264bitstream"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

    }
    buildFeatures {
        prefab = true
        prefabPublishing = true
    }

    prefab {
        create("h264bitstream") {
            headers = "include"
        }
    }
    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
            // Needs to be 3.23+
            // Set this in local.properties for compatibility with JitPack.io
            //cmake.dir=C\:\\Android\\Sdk\\cmake\\3.31.1
        }
    }
    publishing {
        singleVariant("release")
    }
}
//gradlew h264bitstream:publish
afterEvaluate {
    publishing {
        publications {
            register("h264bitstream", MavenPublication::class) {
                from(components["release"])
                groupId = "com.homesoft.android"
                artifactId = "h264bitstream"
                version = "0.2.0"
            }
        }
    }
}
