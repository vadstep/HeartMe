package com.example.vadstep.heartme.model

data class Test(var name: String, var threshold: Int)
data class Config(var bloodTestConfig: List<Test>)
data class Result(var text: String, var pic: Int)

fun String.cleanString(): List<String> {
    return this.toLowerCase().split(" ", ".", "(", ")", ",", ":", "/", "!", "-")
}

fun String.isTest(s: List<String>): Boolean {
    for (x in s) {
        if (x.equals("cholesterol") || this.equals("cholesterol")) {
            continue
        }
        if (this.equals(x)) {
            return true
        }
    }
    return false
}
