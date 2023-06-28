package com.example.generatekanji.application.controllers

import application.Table
import com.example.generatekanji.domain.dto.Level
import com.example.generatekanji.domain.dto.Word


class MainController {


    fun createWord(translate:String, word:String, level: Level, id:String):Word{
         return Word(translate,word,id,level)
    }

    fun createTable(){
        val table:Table=Table()
        table.createTable()
    }



}