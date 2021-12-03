package com.clintonsoares.ttx.utils

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class Constants {
    companion object {
        @IntDef(APPROVE, REJECT, DELIST)
        @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
        annotation class PROPERTY_STATUS

        const val APPROVE = 0x00000003
        const val REJECT = 0x00000006
        const val DELIST = 0x00000009

        val CONNECTION_TIME: Long = 20
        val HTTP: String = "http"

    }
}