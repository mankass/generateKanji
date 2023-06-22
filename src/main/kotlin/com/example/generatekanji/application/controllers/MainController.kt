package application.controllers

import application.Table
import model.Level
import model.Word

class MainController {


    fun createWord(translate:String,word:String,level: Level,id:String):Word{
         return Word(translate,word,level,id)
    }

    fun createTable(){
        val table:Table=Table()
        table.createTable()
    }



}