package `in`.csias.tcclient

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ManagerMessagingService : FirebaseMessagingService() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), flags)
        val builder = NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
            .setSmallIcon(R.drawable.ic_stat_notify)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(remoteMessage.notification?.body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(remoteMessage.hashCode(), builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        (application as MainApplication).broadcastToken(token)
    }
}