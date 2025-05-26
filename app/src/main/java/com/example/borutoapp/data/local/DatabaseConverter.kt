package com.example.borutoapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
//        val stringBuilder = StringBuilder()
//        for (item in list){
//            stringBuilder.append(item).append(separator)
//        }
//
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()

        return list.joinToString(separator = separator)
    }

    @TypeConverter
    fun convertStringToList(elements: String): List<String> {
        return elements.split(separator)
    }
}