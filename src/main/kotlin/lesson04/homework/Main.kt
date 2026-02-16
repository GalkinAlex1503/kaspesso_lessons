package org.example.lesson04.homework

import kotlin.random.Random

// 1.
class Inventory() {
    private val items = mutableListOf<String>()
    operator fun plus(item: String) {
        items.add(item)
    }
    operator fun get(index: Int): String {
        return items[index]
    }
    operator fun contains(item: String): Boolean {
        return items.contains(item)
    }
}

// 2.
class Toggle(val enabled: Boolean) {
    operator fun not(): Toggle {
        return Toggle(!enabled)
    }
}

//3
class Price(private val amount: Int) {
    operator fun times(count: Int) = amount * count
}

//4
class Step(val number: Int) {
    operator fun rangeTo(other: Step) = number..other.number
}
operator fun IntRange.contains(step: Step) = step.number in this

//5
class Log() {
    private val entries = mutableListOf<String>()
    operator fun plus(other: String): Log {
        entries.add(other)
        return this
    }

    fun printLog() {
        println(entries.joinToString())
    }
}

//6

class Person(private val name: String) {

    private val phrases = mutableListOf<String>()

    infix fun says(phrase: String): Person {
        phrases.add(phrase)
        return this
    }

    infix fun and(phrase: String): Person {
        check(phrases.isNotEmpty()) { "Не может быть вызван первой" }
        phrases.add(phrase)
        return this
    }

    infix fun or(phrase: String): Person {
        check(phrases.isNotEmpty()) { "Не может быть вызван первой" }
        phrases[phrases.lastIndex] = selectPhrase(phrases[phrases.lastIndex], phrase)
        return this
    }

    fun print() {
        println(phrases.joinToString(" "))
    }

    private fun selectPhrase(first: String, second: String): String {
        val random = Random.nextInt(0, 2)
        return if (random == 0) first else second
    }
}

fun main() {
    //1
    val inv = Inventory()
    inv + "first"
    inv + "second"
    println(inv[0])
    println(inv[1])
    println("first" in inv)

    //2
    val toggle = Toggle(true)
    println(toggle.enabled)
    println(!toggle.enabled)

    //3
    val price = Price(10)
    println(price * 3)

    //4
    val step1 = Step(1)
    val step2 = Step(5)
    val range = step1..step2
    println(range)
    println(Step(3) in range)
    println(Step(7) in range)

    //5
    val log = Log()
    log + "first" + "second" + "third"
    log.printLog()

    //6
    val andrew = Person("Andrew")
    andrew says "Hello" and "brothers." or "sisters." and "I believe" and "you" and "can do it" or "can't"
    andrew.print()

}