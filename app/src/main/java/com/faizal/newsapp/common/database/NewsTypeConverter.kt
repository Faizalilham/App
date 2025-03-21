package com.faizal.newsapp.common.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.faizal.newsapp.common.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}