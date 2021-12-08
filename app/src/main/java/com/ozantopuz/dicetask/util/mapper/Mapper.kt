package com.ozantopuz.dicetask.util.mapper

@FunctionalInterface
interface Mapper<in T, out R> {

    suspend fun map(item: T): R
}