package com.example.shaloonapp.model.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

fun Context.getEncrypterSharedPreferences(): SharedPreferences {
    val masterKey = MasterKey.Builder(this, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    return EncryptedSharedPreferences.create(
        this,
        "EncryptedSharedPref",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun Context.putStringInSecuredSharedPreferences(key:String, value:String){
    getEncrypterSharedPreferences().edit().putString(key,value).apply()
}

fun Context.getStringInSecuredSharedPreferences(key:String): String{
    return getEncrypterSharedPreferences().getString(key,"")?:""
}

fun Context.deleteSharedPreferences(){
    getEncrypterSharedPreferences().edit().remove("email").apply()
    getEncrypterSharedPreferences().edit().remove("password").apply()
}

