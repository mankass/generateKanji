package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.domain.enums.GeneratorType
import com.grapecity.documents.excel.HorizontalAlignment
import com.grapecity.documents.excel.IRange
import com.grapecity.documents.excel.Workbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.util.*

@Service
class FilesService(
    val wordAndStatService: WordAndStatService
) {
    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }

    fun createAll(wordList: List<WordData>) {
        val directoryName = UUID.randomUUID().toString().substring(1, 20)
        val path = "C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\src\\main\\resources\\files\\${directoryName}"
        val resultMkdir =
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

    fun create(words: List<WordAndStat>, generatorType: GeneratorType, name: String) {
        val nameDir = name + LocalDate.now().toString()
        createTableWordAndStat(words, generatorType, nameDir)
        createAnswersWordAndStat(words, generatorType, nameDir)
    }

    private fun createAnswersWordAndStat(wordList: List<WordAndStat>, generatorType: GeneratorType, nameDir: String) {
        val doc = XWPFDocument()
        val paragraph = doc.createParagraph()
        val run = paragraph.createRun()
        for ((i, word) in wordList.withIndex()) {
            when (generatorType) {
                GeneratorType.TRANSLATE_KANJI -> {
                    run.addBreak()
                    run.setText(
                        "${i}-Translate:${word.wordData.translate} -${word.wordData.word} -:" +
                                "[${word.wordData.transcription} ]"
                    )
                }

                GeneratorType.KANJI_TRANSLATE -> {
                    run.addBreak()
                    run.setText(
                        "${i}-Word:${word.wordData.word}:${word.wordData.translate} " +
                                "-[${word.wordData.transcription}] -)"
                    )
                }

                GeneratorType.TRANSCRIPTION_KANJI -> {
                    run.addBreak()
                    run.setText("${i}-Transcription:[${word.wordData.transcription}] -${word.wordData.word} -:${word.wordData.translate} ")
                }

                GeneratorType.KANJI_TRANSCRIPTION -> {
                    run.addBreak()
                    run.setText("${i}-Word:${word.wordData.word} -[${word.wordData.transcription}] -:${word.wordData.translate} ")
                }
            }

        }
        val uuid = UUID.randomUUID();
        run.fontSize = 16
        val fileOutputStream = FileOutputStream("${nameDir}${uuid.toString()}.docx")
        doc.write(fileOutputStream)
        fileOutputStream.close()
        doc.close()
    }

    private fun createTableWordAndStat(wordList: List<WordAndStat>, generatorType: GeneratorType, nameDir: String) {

        val directoryName = UUID.randomUUID().toString().substring(1, 20)
        val path = "C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\src\\main\\resources\\files\\${directoryName}"
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
            when (generatorType) {
                GeneratorType.TRANSLATE_KANJI -> iRange.value = "$count  " + wordList[count].wordData.translate
                GeneratorType.KANJI_TRANSLATE -> iRange.value = "$count  " + wordList[count].wordData.word
                GeneratorType.TRANSCRIPTION_KANJI -> iRange.value = "$count  " + wordList[count].wordData.transcription
                GeneratorType.KANJI_TRANSCRIPTION -> iRange.value = "$count  " + wordList[count].wordData.word
            }
        }
        workbook.save("//$path.xlsx")
    }

    fun createByDate(localDate: LocalDate, generatorType: GeneratorType, userData: UserData) {
        val words = wordAndStatService.findByDate(localDate, userData)
        randomGenerator(words)
        val wordsALl = wordAndStatService.getAll().toList()
        var list = mutableListOf<WordAndStat>()
        list.addAll(words)
        list.addAll(words)
        list.addAll(words)
        while (list.size < 60) {
            list.addAll(words)
        }

        create(list, generatorType, userData.login)

    }

}