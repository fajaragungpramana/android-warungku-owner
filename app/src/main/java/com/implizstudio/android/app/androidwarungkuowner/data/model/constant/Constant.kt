package com.implizstudio.android.app.androidwarungkuowner.data.model.constant

object Constant {

    object HttpCode {
        const val OK = 200
        const val CREATED = 201
        const val ACCEPTED = 202

        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
    }

    object Key {
        const val AUTH = "auth"
        const val ACCOUNT_ID = "account_id"
        const val COUNT_TIMER = "count_timer"
        const val COUNT_FINISH = "count_finish"
        const val IS_LOG_IN = "is_log_in"
        const val IS_INTRO = "is_intro"
    }

    object RequestCode {
        const val CAMERA = 1
    }

}