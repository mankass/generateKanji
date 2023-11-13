package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.enums.GeneratorType
import com.example.generatekanji.utils.putFile
import com.grapecity.documents.excel.HorizontalAlignment
import com.grapecity.documents.excel.IRange
import com.grapecity.documents.excel.Workbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.zip.ZipOutputStream

@Service
class FilesService(
    val wordAndStatService: WordAndStatService
) {
    fun create(words: List<WordAndStat>, generatorType: GeneratorType, name: String): ByteArray {

        val buffGeneratedTable = createTableWordAndStat(words, generatorType)

        val answersDocBuff = createAnswersWordAndStat(words, generatorType)

        val zipByteArrayOutputStream = ByteArrayOutputStream()

        ZipOutputStream(zipByteArrayOutputStream).use {
            it.putFile("answers.docx", answersDocBuff)
            it.putFile("generatedTable.xlsx", buffGeneratedTable)
        }
        //TODO try with resources

        return zipByteArrayOutputStream.toByteArray()
    }

    private fun createAnswersWordAndStat(
        wordList: List<WordAndStat>,
        generatorType: GeneratorType,
    ): ByteArray {

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
        run.fontSize = 16

        val byteArrayOutputStream = ByteArrayOutputStream()
        doc.write(byteArrayOutputStream)
        doc.close()
        return byteArrayOutputStream.toByteArray()
    }

    private fun createTableWordAndStat(
        wordList: List<WordAndStat>,
        generatorType: GeneratorType,
    ): ByteArray {
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
        val outputStream = ByteArrayOutputStream()
        workbook.save(outputStream)
        return outputStream.toByteArray()

    }

    fun createByDate(localDate: LocalDate, generatorType: GeneratorType, userData: UserData): ByteArray {
        val words = wordAndStatService.findByDate(localDate, userData)
        val mutableList = mutableListOf<WordAndStat>()
        if (words.isEmpty()) {
            throw Exception("Нет слов")
        } else {
            while (mutableList.size < 60) {
                mutableList.addAll(words)
            }
            return create(mutableList.subList(0, 60), generatorType, userData.login)
        }
    }

    fun createByDate(
        localDateStart: LocalDate,
        localDateEnd: LocalDate,
        generatorType: GeneratorType,
        userData: UserData
    ): ByteArray {
        val words = wordAndStatService.findByDateBetween(localDateStart, localDateEnd, userData)
        val mutableList = mutableListOf<WordAndStat>()
        if (words.isEmpty()) {
            throw Exception("Нет слов")
        } else {
            while (mutableList.size < 60) {
                mutableList.addAll(words)
            }
            return create(mutableList.subList(0, 60), generatorType, userData.login)
        }
    }


    fun createALl(generatorType: GeneratorType, userData: UserData): ByteArray {
        val words = wordAndStatService.getAllByUser(userData)
        val mutableList = mutableListOf<WordAndStat>()
        if (words.isEmpty()) {
            throw Exception("Нет слов")
        } else {
            while (mutableList.size < 60) {
                mutableList.addAll(words)
            }
            return create(mutableList.subList(0, 60), generatorType, userData.login)
        }
    }

}