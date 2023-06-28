package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.Word
import com.grapecity.documents.excel.HorizontalAlignment
import com.grapecity.documents.excel.IRange
import com.grapecity.documents.excel.Workbook
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class TableService(val db: JdbcTemplate) {

    val columnHeight: Double = 36.25

    fun createTable(word: List<Word>): Pair<String,List<Word>> {


        val listUsingWords = ArrayList<Word>()

        for (i: Int in 1..60) {
            listUsingWords.add(word[Random.nextInt(word.size)])
        }

        val workbook = Workbook()
        val worksheet = workbook.worksheets.get(0)
//                  val wordsList: List<Word> = db.query("select * from words") { response, _ ->
//            Word(
//                response.getString(1), response.getString(2),
//                response.getString(3), (Level.N1)
//            )
//        }


        val listOfRange = mutableListOf<IRange>()
        worksheet.columns.rowHeight = columnHeight

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

            iRange.value = listUsingWords[count].wordJapan
        }
        val name = UUID.randomUUID().toString()

        val cyan = "\u001B[36m"
        val reset = "\u001b[0m"
        println("$cyan$name.xlsx$reset")
        workbook.save("$name.xlsx")
        return Pair(name,listUsingWords)


    }

}