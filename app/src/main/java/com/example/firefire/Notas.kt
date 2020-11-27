package com.example.firefire

data class Notas(val nombre: String = "", val materia: String = "", val nota: Double = 0.0) {

    override fun toString() = nombre + "\t" + materia + "\t" + nota
}