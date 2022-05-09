/*
 * Copyright 2013 - 2021 Anton Tananaev (anton@in.in)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package `in`.csias.tcclient

import android.content.Context
import android.content.Intent
import androidx.preference.PreferenceManager

class AutostartReceiver : `in`.csias.tcclient.WakefulBroadcastReceiver() {

    @Suppress("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (sharedPreferences.getBoolean(`in`.csias.tcclient.MainFragment.Companion.KEY_STATUS, false)) {
            `in`.csias.tcclient.WakefulBroadcastReceiver.Companion.startWakefulForegroundService(
                context,
                Intent(context, `in`.csias.tcclient.TrackingService::class.java)
            )
        }
    }

}