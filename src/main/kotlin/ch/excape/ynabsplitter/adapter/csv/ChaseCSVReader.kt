package ch.excape.ynabsplitter.adapter.csv

import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.adapter.ynab.toTransaction
import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvDate
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import java.io.File
import java.io.FileReader
import java.util.*

class ChaseCSVReader(filename: File) {

    private val reader: CsvToBean<ChaseTransaction>? = CsvToBeanBuilder<ChaseTransaction>(FileReader(filename))
            .withType(ChaseTransaction::class.java).build()

    fun readTransactions() : List<Transaction> {
        val csvTransactions = readCSV()
        return csvTransactions.map { t -> t.toTransaction()}
    }


    private fun readCSV(): List<ChaseTransaction> {
       return reader!!.parse().toList()
    }
}

class ChaseTransaction {
    @CsvBindByName(column = "Transaction Date")
    @CsvDate("MM/dd/yyy")
    var transactionDate: Date? = null

    @CsvBindByName(column = "Post Date")
    @CsvDate("MM/dd/yyy")
    var postDate: Date? = null

    @CsvBindByName(column = "Description")
    var description: String? = null

    @CsvBindByName(column = "Type")
    var type: String? = null

    @CsvBindByName(column = "Amount")
    var amount: Double? = null

    override fun toString(): String {
        return "ChaseTransaction(transactionDate=$transactionDate, postDate=$postDate, description=$description, type=$type, amount=$amount)"
    }


}
