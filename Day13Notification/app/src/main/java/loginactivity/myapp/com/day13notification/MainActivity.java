package loginactivity.myapp.com.day13notification;

// https://developer.android.com/guide/topics/ui/notifiers/notifications
// https://developer.android.com/training/notify-user/build-notification

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnonClick(View view) {
        try {
            int uniqueID = new Random().nextInt(9999 - 1000) + 1000;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification");
            builder.setSmallIcon(R.drawable.png_icon); // 1
            builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.png_icon)); // 2
            builder.setColor(Color.RED);
            builder.setContentTitle(uniqueID + ": This is a demo notification title"); // 5
            builder.setContentText("This is a demo notification body"); // 6
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            // Set the intent that will fire when the user taps the notification
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(uniqueID, builder.build());

            // https://developer.android.com/reference/android/app/Notification.Builder
            /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                Notification noti = new Notification.Builder(this)
                        .setContentTitle(id + ": This is a demo notification title")
                        .setContentText("This is a demo notification body")
                        .setSmallIcon(R.drawable.png_icon)
                        //.setLargeIcon(aBitmap)
                        .build();
            }*/
            Log.d("MainActivity", "Notification sent");
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }

        //sendNotification("title", "body");
    }

//    private void sendNotification(String title, String body) {
//        Intent i = new Intent(this, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pi = PendingIntent.getActivity(this,
//                0 /* Request code */,
//                i,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
//                "test")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(body)
//                .setAutoCancel(true)
//                .setSound(sound)
//                .setContentIntent(pi);
//
//        NotificationManager manager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        manager.notify(0, builder.build());
//    }
}
