package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
import com.grapecity.documents.excel.HorizontalAlignment
import com.grapecity.documents.excel.IRange
import com.grapecity.documents.excel.Workbook
import org.springframework.stereotype.Service
import java.util.*

@Service
class TableService {

    val columnHeight: Double = 36.25

    fun createTable(word: List<WordData>,string: String): Pair<String, List<WordData>> {


        val workbook = Workbook()
        val worksheet = workbook.worksheets.get(0)

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
            iRange.rowHeight=17.00
            iRange.value = word[count].word+"   $count"

        }
        val name = UUID.randomUUID().toString()

        val cyan = "\u001B[36m"
        val reset = "\u001b[0m"
        println("$cyan$name.xlsx$reset")
        workbook.save("$string$name.xlsx")

        return Pair(name, word)


    }

}