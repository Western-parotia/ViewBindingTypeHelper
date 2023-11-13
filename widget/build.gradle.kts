import com.foundation.kts.Dependencies
import com.foundation.kts.Publish


plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

val versionTimestamp = Publish.Version.getVersionTimestamp()

dependencies {
    implementation(Dependencies.Kotlin.kotlin_stdlib)
}

android {
    compileSdk = 30
    defaultConfig {
        minSdk = 21
        // lib 项目 无需指定 targetSdk,仅用于构建测试apk
//        targetSdk = 31
        buildConfigField("String", "versionName", "\"${Publish.Version.versionName}\"")
        buildConfigField("String", "versionTimeStamp", "\"$versionTimestamp\"")
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs =
            freeCompilerArgs + arrayOf("-module-name", Publish.Maven.getFourPackage(projectDir))

    }
    compileOptions {
        kotlinOptions.freeCompilerArgs += arrayOf(
            "-module-name", Publish.Maven.getFourPackage(projectDir)
        )
    }
    buildFeatures {
        viewBinding = true
    }
}


//val sourceCodeTask: Jar = tasks.register("sourceCode", Jar::class.java) {
//    from(android.sourceSets.getByName("main").java.srcDirs)
//    classifier = "sources"
//}.get()
//
//
//tasks.register("createGitTagAndPush", Exec::class.java) {
//    commandLine("git", "push", "origin", versionTimestamp)
//}
//    .get()
//    .dependsOn(tasks.register("createGitTag", Exec::class.java) {
//        commandLine("git", "tag", versionTimestamp, "-m", "autoCreateWithMavenPublish")
//    })

//publishing {
//    publications {
//        create<MavenPublication>("tools") {
//            groupId = Publish.Maven.getThreePackage(projectDir)
//            artifactId = Publish.Version.artifactId
//            version = Publish.Version.versionName
//            artifact(sourceCodeTask)
//            afterEvaluate {//在脚本读取完成后绑定
//                val bundleReleaseAarTask: Task = tasks.getByName("bundleReleaseAar")
//                bundleReleaseAarTask.finalizedBy("createGitTagAndPush")
//                artifact(bundleReleaseAarTask)
//            }
////            artifact("$buildDir/outputs/aar/loading-release.aar")//直接制定文件
//            pom.withXml {
//                val dependenciesNode = asNode().appendNode("dependencies")
//                configurations.implementation.get().allDependencies.forEach {
//                    if (it.version != "unspecified" && it.name != "unspecified") {
//                        val depNode = dependenciesNode.appendNode("dependency")
//                        depNode.appendNode("groupId", it.group)
//                        depNode.appendNode("artifactId", it.name)
//                        depNode.appendNode("version", it.version)
//                    }
//                }
//            }
//
//        }
//        repositories {
//            if (Publish.SNAPSHOT) {
//                Publish.Maven.aliyunSnapshotRepositories(this)
//            } else {
//                Publish.Maven.aliyunReleaseRepositories(this)
//            }
//        }
//    }
//}