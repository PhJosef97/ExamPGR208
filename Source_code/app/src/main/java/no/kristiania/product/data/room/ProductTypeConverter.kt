package no.kristiania.product.data.room

import androidx.room.TypeConverter

// Converter hvis jeg har tid
//    "rating": {
//        "rate": 3.9,
//        "count": 120
//    }
//}

object ProductTypeConverter {

    // DETTE GJØR PRODUCTS IDS SETTES I KOMMA ETTER HVERANDRE
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<Int> {
        return if (value.isNotEmpty()) {
            value.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<Int>): String {
        return list.joinToString(",")
    }
    /*
    PRØVDE DENNE METODEN MEN FUNKA IKKE
    @TypeConverter
    @JvmStatic
    fun fromIntList(value: Int): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }
    @TypeConverter
    @JvmStatic
    fun fromListInt(list: List<Int>): Int {
        return gson.toJson(list)
    }*/
}