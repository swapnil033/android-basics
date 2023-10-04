package com.swapnil.receiverapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == "TEST-SIGNAL"){
            Toast.makeText(context, "received from sender", Toast.LENGTH_SHORT).show()
        }
    }
}