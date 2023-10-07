package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
import com.grapecity.documents.excel.HorizontalAlignment
import com.grapecity.documents.excel.IRange
import com.grapecity.documents.excel.Workbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Service
class FilesService {


    fun createAll(wordList: List<WordData>) {
        val directoryName = UUID.randomUUID().toString().substring(1,20)
        val path="C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\src\\main\\resources\\files\\${directoryName}"
        val  resultMkdir=
            File("C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\src\\main\\resources\\files\\${directoryName}").mkdir()
        println(resultMkdir)
        createTable(wordList, path)
        createAnswers(wordList, path)
    }

    fun createTable(wordList: List<WordData>, nameFile: String) {

        val workbook = Workbook()
        val worksheet = workbook.worksheets.get(0)

        val listOfRange = mutableListOf<IRange>()
        worksheet.columns.rowHeight = 36.25

        for (i: Int in 1..16 step 3) {
            listOfRange.add(worksheet.getRange("A$i:B$i"))
            listOfRange.add(worksheet.getRange("C$i:D$i"))
            listOfRange.add(worksheet.getRange("E$i:F$i"))
            listOfRange.add(worksheet.getRange("G$i:H$i"))
            listOfRange.add(worksheet.getRange("I$i:J$i"))
            listOfRange.add(worksheet.getRange("K$i:L$i"))
            listOfRange.add(worksheet.getRange("M$i:N$i"))
            listOfRange.add(worksheet.getRange("O$i:P$i"))
            listOfRange.add(worksheet.getRange("Q$i:R$i"))
            listOfRange.add(worksheet.getRange("S$i:T$i"))
        }
        for ((count, iRange: IRange) in listOfRange.withIndex()) {
            iRange.merge()
            iRange.horizontalAlignment = HorizontalAlignment.CenterContinuous
            iRange.rowHeight = 17.00
            iRange.font.size = 14.00
            iRange.font.bold = true
            iRange.value = wordList[count].word + "   $count"

        }
        workbook.save("//$nameFile.xlsx")
    }

    fun createAnswers(wordList: List<WordData>, nameFile: String) {
        val doc = XWPFDocument()
        val paragraph = doc.createParagraph()
        val run = paragraph.createRun()
        for ((i, word) in wordList.withIndex()) {
            run.addBreak()
            run.setText("${i}-Word:${word.word} -[${word.transcription}] -:${word.translate} ")
        }

        run.fontSize = 16
        val fileOutputStream = FileOutputStream("${nameFile}.docx")
        doc.write(fileOutputStream)
        fileOutputStream.close()
        doc.close()
    }

}