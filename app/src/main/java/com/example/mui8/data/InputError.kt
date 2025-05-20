package com.example.mui8.data

sealed class InputError(val message: String) {
    object UppercaseNotAllowed : InputError("Wielkie litery są niedozwolone")
    object LowercaseNotAllowed : InputError("Małe litery są niedozwolone")
    object NonAlphanumericNotAllowed : InputError("Dozwolone są tylko znaki alfanumeryczne")
    object DigitsNotAllowed : InputError("Cyfry są niedozwolone")
}
