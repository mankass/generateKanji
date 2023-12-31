package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.FileOutputStream
@Service
class TranslatePageService {
    fun createTranslatePage(pair:Pair<String,List<WordData>>):String{
        val doc=XWPFDocument()
        val paragraph=doc.createParagraph()
        val run=paragraph.createRun()
        for (word in pair.second){
            run.addBreak()
            run.setText("Word:${word.word};         translate:${word.translate}")
        }

        run.fontSize=16
        val fileOutputStream=FileOutputStream("${pair.first}.docx")
        doc.write(fileOutputStream)
        fileOutputStream.close()
        doc.close()

        return "${pair.first}.docx"

    }
}