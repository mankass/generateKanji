package application

import com.example.generatekanji.domain.dto.Level
import com.example.generatekanji.domain.dto.Word
import com.grapecity.documents.excel.*

import java.util.*

class Table {
    fun createTable(): String {

        val workbook = Workbook()

        val worksheet = workbook.worksheets.get(0)

        val columnHeight: Double = 36.25

        val columnsWeight: Double = 7.67

        val count = 10


        worksheet

        val vpr = mutableListOf<IRange>()
        worksheet.columns.rowHeight=columnHeight


        for (i: Int in 1..16 step 3) {
            vpr.add(worksheet.getRange("A$i:B$i"))
            vpr.add(worksheet.getRange("C$i:D$i"))
            vpr.add(worksheet.getRange("E$i:F$i"))
            vpr.add(worksheet.getRange("G$i:H$i"))
            vpr.add(worksheet.getRange("I$i:J$i"))
            vpr.add(worksheet.getRange("K$i:L$i"))
            vpr.add(worksheet.getRange("M$i:N$i"))
            vpr.add(worksheet.getRange("O$i:P$i"))
            vpr.add(worksheet.getRange("Q$i:R$i"))
            vpr.add(worksheet.getRange("S$i:T$i"))
        }
        for (i:IRange in vpr){
            i.merge()
            i.horizontalAlignment=HorizontalAlignment.CenterContinuous
        }
        val word = Word("Lol", "LOL", "333",Level.N1)
//        for (i: Int in 1..16 step 3) {
//            worksheet.getRange("A$i:B$i").merge()
//            worksheet.getRange("A$i:B$i").horizontalAlignment = HorizontalAlignment.CenterContinuous
//            worksheet.getRange("A$i:B$i").value = (word.translate)
//            worksheet.getRange("C$i:D$i").merge()
//            worksheet.getRange("E$i:F$i").merge()
//            worksheet.getRange("G$i:H$i").merge()
//            worksheet.getRange("I$i:J$i").merge()
//            worksheet.getRange("K$i:L$i").merge()
//            worksheet.getRange("M$i:N$i").merge()
//            worksheet.getRange("O$i:P$i").merge()
//            worksheet.getRange("Q$i:R$i").merge()
//            worksheet.getRange("S$i:T$i").merge()
//        }

        val name = UUID.randomUUID().toString() + ".xlsx"
        val cyan = "\u001B[36m"
        val reset = "\u001b[0m"
        println(cyan + name + reset)
        workbook.save(name)



        return name;
    }
}