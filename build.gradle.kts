// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.foundation.buildsrc.Publish

buildscript {//这里不支持import
    repositories {
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunNexusPublic) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunPublic) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunGoogle) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunJcenter) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunCentral) }
        maven { setUrl(com.foundation.buildsrc.Repositories.jitpackIo) }
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.foundation.buildsrc.Dependencies.kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunNexusPublic) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunPublic) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunGoogle) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunJcenter) }
        maven { setUrl(com.foundation.buildsrc.Repositories.aliyunCentral) }
        maven { setUrl(com.foundation.buildsrc.Repositories.jitpackIo) }
        maven {
            setUrl(Publish.Maven.codingArtifactsRepoUrl)
            credentials {
                username = Publish.Maven.repositoryUserName
                password = Publish.Maven.repositoryPassword
            }
        }
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}