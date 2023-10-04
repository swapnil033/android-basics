package com.swapnil.receiverapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirplaneModeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){

            val isTurnedOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) != 0

            Toast.makeText(context, "Airplane mode is enable? $isTurnedOn", Toast.LENGTH_SHORT).show()
        }

    }
}