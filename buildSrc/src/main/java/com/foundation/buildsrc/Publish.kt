package com.foundation.buildsrc

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

private const val VERSION = "1.0.1"
private const val SNAPSHOT = false
private const val ARTIFACT_ID = "view-binding-helper"

object Publish {
    object Version {
        val versionName = if (SNAPSHOT) "$VERSION-SNAPSHOT" else VERSION
        const val versionCode = 1
        const val artifactId = ARTIFACT_ID

        private fun getTimestamp(): String {
            return SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.CHINA).format(Date())
        }

        fun getVersionTimestamp(): String {
            return "$versionName-${getTimestamp()}"
        }
    }

    object Maven {
        const val codingArtifactsRepoUrl =
            "https://mijukeji-maven.pkg.coding.net/repository/jileiku/base_maven/"
        val repositoryUserName: String
        val repositoryPassword: String

        init {
            //读取local的腾讯云用户名和密码
            val localProperties = Properties()
            var lp = File("local.properties")
            if (!lp.exists()) lp = File("../local.properties")//“/”win和mac都支持
            if (!lp.exists()) throw RuntimeException("没有找到local.properties")
            localProperties.load(lp.inputStream())
            val name = localProperties.getProperty("repositoryUserName")
            val password = localProperties.getProperty("repositoryPassword")
            if (name == null || password == null) {
                throw RuntimeException("请在local.properties添加私有仓库的用户名（repositoryUserName）和密码（repositoryPassword）")
            }
            repositoryUserName = name
            repositoryPassword = password

            //自动修改md里的版本号，匹配像：xxx:manager:1.0.1-SNAPSHOT"
            var md = File("README.md")
            if (!md.exists()) md = File("../README.md")
            if (md.exists()) {
                val text = md.readText()
                ":$ARTIFACT_ID:.+\"".toRegex().find(text)?.let {
                    val currentVersion = ":$ARTIFACT_ID:${Version.versionName}\""
                    if (it.value == currentVersion) {
                        return@let
                    }
                    md.writeText(
                        text.substring(0, it.range.first)
                                + currentVersion
                                + text.substring(it.range.last + 1, text.length)
                    )
                }
            }
        }

        /**
         * 获取模块3级包名，如：com.foundation.widget
         */
        fun getThreePackage(projectDir: File): String {
            val st = getFourPackage(projectDir)
            return st.substring(0, st.lastIndexOf("."))
        }

        /**
         * 获取模块4级包名，如：com.foundation.widget.shape
         */
        fun getFourPackage(projectDir: File): String {
            try {
                val javaFile = File(projectDir, "src\\main\\java")
                if (javaFile.exists()) {
                    val child = javaFile.listFiles()[0].listFiles()[0].listFiles()[0].listFiles()[0]
                    //先删掉前段路径，然后转为.
                    return child.absolutePath.substring(javaFile.absolutePath.length + 1)
                        .replace("/", ".")
                        .replace("\\", ".")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            throw  RuntimeException("没有找到第四级包名")
        }
    }
}