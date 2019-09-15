package com.example.swipequiz

data class Question(
    var question: String
) {
    companion object {
        val QUIZ_QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grans 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
        )

        val QUIZ_ANSWERS = arrayOf(
            "false",
            "true",
            "false",
            "true"
        )
    }
}
