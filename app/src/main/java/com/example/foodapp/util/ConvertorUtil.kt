package com.example.foodapp.util
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class ConvertorUtil {
    companion object {
        fun getWeekdayName(dateString: String): String {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = format.parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date!!

            return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) ?: "Unknown"
        }
    }
}
