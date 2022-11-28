package br.com.alura.forum.data.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}
