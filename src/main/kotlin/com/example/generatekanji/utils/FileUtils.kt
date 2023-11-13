package com.example.generatekanji.utils

import org.springframework.stereotype.Component
import java.io.File

@Component
class FileUtils {

    fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) {
            for (child in fileOrDirectory.listFiles()) deleteRecursive(child)
        }
        fileOrDirectory.delete()
    }
}