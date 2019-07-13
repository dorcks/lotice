package dev.dorcks.lotice

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("No arguments provided")
    } else {
        println("${args.size} arguments provided: ${args.joinToString("\", \"", "\"", "\"")}")
    }
}