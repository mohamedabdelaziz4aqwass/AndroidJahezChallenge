package com.mohamedabdelaziz.jahez.core.utils.languages

import com.mohamedabdelaziz.jahez.core.utils.constants.Constants


object  Language {
    fun getLanguage(): String {
        val savedLanguage = LanguageUtils.getFromSharedPreference(Constants.LANGUAGE)
        return if (savedLanguage.isEmpty()) {
            // English default language
            "en"
        } else {
            savedLanguage
        }
    }
}