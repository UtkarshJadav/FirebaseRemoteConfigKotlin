package com.utkarsh.firebaseremoteconfigdemo


import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initFirebaseRemoteConfig()
    }

    private fun initFirebaseRemoteConfig() {
        FirebaseApp.initializeApp(this)
        FirebaseRemoteConfig.getInstance().apply {
            //set this during development
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
            setConfigSettingsAsync(configSettings)
            //set this during development
            setDefaultsAsync(R.xml.remote_config_defaults)

            fetchAndActivate().addOnCompleteListener { task->
                var updated = task.result
                if (task.isSuccessful) {
                    updated = task.result
                    Log.d("TAG", "Config params updated: $updated")
                } else {
                    Log.d("TAG", "Config params updated: $updated")
                }
            }
        }
    }
}