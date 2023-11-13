//import com.foundation.kts.*//根目录文件不允许使用 包导入，只能写全路径
// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.foundation.kts.GlobalConfig

//buildSrc的初始化init
GlobalConfig.init(project)

buildscript {//这里不支持import
    repositories {
        com.foundation.kts.Repositories.defRepositories(this)
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.foundation.kts.Dependencies.kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        com.foundation.kts.Repositories.defRepositories(this)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}