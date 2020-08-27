package kr.co.tjoeun.daily10min_200824.utils

import android.content.Context

class ContextUtil {

    companion object {

        private val prefName = "Daily10MinutesPref"

        private val AUTO_LOGIN_CHECK = "AUTO_LOGIN_CHECK"
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        fun setAutoLoginCheck(context: Context, isAuto : Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN_CHECK, isAuto).apply()
        }

        fun getAutoLoginCheck(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN_CHECK, false)
        }

        fun setLoginUserToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getLoginUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

    }

}

