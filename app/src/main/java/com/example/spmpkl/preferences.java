package com.example.spmpkl;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.tech.Ndef;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class preferences {


    private static final String DATA_LOGIN="status_login",
            DATA_AS = "Name , Username, Alamat, Dosbing";


   preferences sharedPreferences;

    public static SharedPreferences getSharedReferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);


    }

    public static void setDataAs(Context context, String data){

        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(DATA_AS,"Name, Alamat, Username, Dosbing");
        editor.apply();
    }
    public static String getDataAs(Context context){

        return getSharedReferences(context).getString (DATA_AS, "Null");


    }
    public static void setDataLogin (Context context, boolean status){
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }


    public static boolean getDataLogin(Context context){
        return getSharedReferences(context).getBoolean(DATA_LOGIN, false);
    }

    public static void clearData(Context context ){
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }
}

