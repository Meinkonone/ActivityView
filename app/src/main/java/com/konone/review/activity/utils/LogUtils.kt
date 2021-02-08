package com.konone.review.activity.utils

/**
 * Created by konone on 2/8/21.
 */
object LogUtils {
    private const val BASE_TAG = "Konone_"

    fun getClassTag(className: String): String {
        return if (className.length > 5) {
            "$BASE_TAG${className.substring(0, 4)}"
        } else {
            "$BASE_TAG$className"
        }
    }
}