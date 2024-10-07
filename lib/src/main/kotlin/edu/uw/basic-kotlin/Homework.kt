package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when (arg) {
        "Hello" -> return "world"
        "Bonjour" -> return "Say what?"
        "Howdy" -> return "Say what?"
        0 -> return "zero"
        1 -> return "one"
        in 2..10 -> return "low number"
        is Int -> return "a number"
    }
    return "I don't understand"
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}


// write a class "Person" with first name, last name and age

class Person (val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:${this.firstName} lastName:${this.lastName} age:${this.age}]"
}

// write a class "Money"
class Money (val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount can't be less than 0")
        }
        if (currency !in setOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Currency has to be USD, EUR, CAN, or GBP")
        }
    }

    fun convert(otherCurr: String): Money {
        val conversionRates = mapOf(
            Pair("USD", "GBP") to 0.5,
            Pair("USD", "EUR") to 1.5,
            Pair("USD", "CAN") to 1.25,
            Pair("GBP", "USD") to 2.0,
            Pair("EUR", "USD") to 0.75,
            Pair("CAN", "USD") to 1.25
        )
        return if (this.currency == otherCurr) {
            Money(this.amount, otherCurr)
        } 
            else {
            val conversionRate = conversionRates[Pair(currency, otherCurr)]
            if (conversionRate != null) {
                Money((this.amount * conversionRate).toInt(), otherCurr)
            } else {
                convert("USD").convert(otherCurr)
            }
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + (other.convert(this.currency)).amount, this.currency)
    }
}