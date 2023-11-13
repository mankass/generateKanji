package com.example.generatekanji.utils

import org.springframework.stereotype.Component

@Component
class ListUtills {

    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }
}