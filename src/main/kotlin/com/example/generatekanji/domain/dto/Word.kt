package com.example.generatekanji.domain.dto


data class Word(val translate:String, val word:String, val level:Level,val  id:String?) {

}

enum class Level (){
    N1,N2,N3,N4,N5
}