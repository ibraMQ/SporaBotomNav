package com.example.botomexample

import java.io.Serializable

data class Contact (
    val name: String,
    val number: String,
    val company: String) :Serializable {
}