package com.byandev.submission2repositorylivedata.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ConvertDate {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convertReleaseDate(date: String): String {
            val timeZone: TimeZone = TimeZone.getDefault()
            val timeZoneConvert : TimeZone = TimeZone.getTimeZone("Asia/Jakarta")

            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dateConvert = SimpleDateFormat("MMM dd, yyyy")
            dateFormat.timeZone = timeZone
            dateConvert.timeZone = timeZoneConvert

            var dateOri = Calendar.getInstance().time
            try {
                dateOri = dateFormat.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return dateConvert.format(dateOri)
        }
    }


}