import kotlin.math.roundToInt
public var income = 0

fun printRow(list: MutableList<MutableList<Int>>, row: Int, seats: Int): String {
    var hat = "  "
    for (k in 1..seats) hat += "${k.toString()} "
    var str = "Cinema:\n" + hat + "\n"
    for (i in 0 until row){
            for (j in 0 until seats) {
                if (j == 0) str += "${(i + 1).toString()} "
                if (list[j][i] == 0) {
                    str += "S "
                } else str += "B "
            }
        str.trim()
        str += "\n"
    }
    return str
}
fun main() {
    println("Enter the number of rows:")
    var rows = readln()!!.toInt()
    println("Enter the number of seats in each row:")
    var seats = readln()!!.toInt()
//инициализируем и присваеваем нулевые значения для матрицы с местами 0
    var listRow = MutableList(seats){MutableList(rows) {0}}
    var notEnd = true
    while (notEnd) {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )

        var input = readln()!!.toInt()
        when (input) {
            1 -> println(printRow(listRow, rows, seats))
            2 -> println(bay(listRow, rows, seats))
            3->  println(statistics(listRow, rows, seats))
            0 -> notEnd = false
        }
    }

}

fun bay(list: MutableList<MutableList<Int>>, rows: Int, seats : Int) : String{
    var theEnd = true
    var price = 0
    while (theEnd) {
        println("Enter a row number:")
        var numRow = readln()!!.toInt()
        println("Enter a seat number in that row:")
        var numSeat = readln()!!.toInt()


        if ((numSeat) > seats || (numRow) >rows) println("Wrong input!") else
        if (list[numSeat - 1][numRow - 1] == 1)
            println("That ticket has already been purchased!")
         else  {
            list[numSeat - 1][numRow - 1] = 1
            theEnd = false
        }

        price = if (rows * seats <= 60) 10 else {
            if (numRow <= rows / 2) 10 else 8
        }
    }
    income += price
    return "Ticket price: $$price\n"
}
fun statistics(list: MutableList<MutableList<Int>>, rows: Int, seats : Int) : String {
    var count = 0
    for (i in 0 until rows){
        for (j in 0 until seats) {
            if (list[i][j] == 1) count++
        }
    }
// находим предполагаемую вырочку при продаже всех билетов
    var total =  if (rows * seats <= 60) 10 * rows * seats else
        ((rows / 2) *  10 * seats)  + (((rows - (rows / 2)) * 8 * seats))

// определение процента проданных билетов
    var percent = (count * 100.0) / (rows * seats)

    var result = "Number of purchased tickets: $count\n" +
            "Percentage:" + "%.2f".format(percent) + "%" + "\n" +
            "Current income: \$$income\n" +
            "Total income: \$$total"
    return result
}


