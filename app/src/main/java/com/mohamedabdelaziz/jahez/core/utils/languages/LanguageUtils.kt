package com.mohamedabdelaziz.jahez.core.utils.languages

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.mohamedabdelaziz.jahez.BaseApplication
import com.mohamedabdelaziz.jahez.core.utils.constants.Constants
import java.util.*

object LanguageUtils {
    private val settings =
        BaseApplication.appContext.getSharedPreferences(Constants.LANGUAGE, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = settings.edit()

    fun getFromSharedPreference(key: String): String {
        return settings.getString(key, "") ?: ""
    }

    fun saveInSharedPreference(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun setLanguageWithoutReload(language: String, context: Activity) {
        val activityRes = context.resources
        val activityConf = activityRes.configuration
        val newLocale = Locale(language)
        activityConf.setLocale(newLocale)
        activityRes.updateConfiguration(activityConf, activityRes.displayMetrics)
        val applicationRes = context.applicationContext.resources
        val applicationConf = applicationRes.configuration
        applicationConf.setLocale(newLocale)
        applicationRes.updateConfiguration(applicationConf, applicationRes.displayMetrics)
    }

    fun setLanguage(language: String, context: Activity) {
        val activityRes = context.resources
        val activityConf = activityRes.configuration
        val newLocale = Locale(language)
        activityConf.setLocale(newLocale)
        activityRes.updateConfiguration(activityConf, activityRes.displayMetrics)
        val applicationRes = context.applicationContext.resources
        val applicationConf = applicationRes.configuration
        applicationConf.setLocale(newLocale)
        applicationRes.updateConfiguration(applicationConf, applicationRes.displayMetrics)
        context.recreate()
    }
}