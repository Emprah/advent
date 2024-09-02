package com.emprah.advent.util

import org.springframework.stereotype.Service
import java.io.File

@Service
object FileService {

    fun getFile(name: String, resourcesPath: String = "") =
        this::class.java.classLoader.getResource("$resourcesPath$name")?.file?.let { File(it)}
}