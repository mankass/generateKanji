package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.Word
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.FileOutputStream
@Service
class TranslatePageService {
    fun createTranslatePage(pair:Pair<String,List<Word>>):String{
        val doc=XWPFDocument()
        val paragraraph=doc.createParagraph()
        val run=paragraraph.createRun()
        for (word in pair.second){
            run.addBreak()
            run.setText("Word:${word.wordJapan};translate:${word.translateWord}:${word.levelN}")
        }

        run.fontSize=16
        doc.write(FileOutputStream("${pair.first}.docx"))
        doc.close()
        return "${pair.first}.docx"

    }
}