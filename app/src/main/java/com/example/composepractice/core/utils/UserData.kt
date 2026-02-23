package com.example.composepractice.core.utils

import android.content.Context
import com.example.composepractice.domain.model.userInformation.ClientInformation
import com.google.gson.Gson

object UserData {

    private const val clientInformationFile = "client_information_file"
    private const val clientInformationKey = "client_information_key"

    fun saveData(clientInformation: ClientInformation, context: Context)
    {
        val sharedPreferences = context.getSharedPreferences(clientInformationFile, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gao = Gson()
        val json= gao.toJson(clientInformation)
        editor.putString(clientInformationKey, json)
        editor.apply()

    }

    fun getSaveData(context: Context): ClientInformation
    {
        val  preferences= context.getSharedPreferences(clientInformationFile, Context.MODE_PRIVATE)
        val json = preferences.getString(clientInformationKey, "")
        val gao =  Gson()
        return gao.fromJson(json, ClientInformation::class.java)

    }

    fun checkDataSession(context: Context):Boolean
    {
        val  preferences= context.getSharedPreferences(clientInformationFile, Context.MODE_PRIVATE)
        val json = preferences.getString(clientInformationKey, "")
        return json?.length==0
    }

    fun clearSaveData(context: Context)
    {
        val sharedPreferences = context.getSharedPreferences(clientInformationFile, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

    }
}