package com.example.generatekanji.utils

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun ZipOutputStream.putFile(name: String, file: ByteArray) {
    putNextEntry(ZipEntry(name))
    write(file)
    closeEntry()
}