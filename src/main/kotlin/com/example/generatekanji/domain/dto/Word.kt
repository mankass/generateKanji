package com.example.generatekanji.domain.dto


data class Word(val  id:String?,val wordJapan:String, val translateWord:String, val levelN:Level,) {

}

enum class Level (){
    N1,N2,N3,N4,N5
}