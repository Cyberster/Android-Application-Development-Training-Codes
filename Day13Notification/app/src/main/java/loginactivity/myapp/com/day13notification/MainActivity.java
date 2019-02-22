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
            int id = new Random().nextInt(9999 - 1000) + 1000;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.drawable.png_icon);
            builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.png_icon));
            builder.setColor(Color.RED);
            builder.setContentTitle(id + ": This is a demo notification title");
            builder.setContentText("This is a demo notification body");
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            // Set the intent that will fire when the user taps the notification
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(id, builder.build());

            // https://developer.android.com/reference/android/app/Notification.Builder
            /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                Notification noti = new Notification.Builder(this)
                        .setContentTitle(id + ": This is a demo notification title")
                        .setContentText("This is a demo notification body")
                        .setSmallIcon(R.drawable.png_icon)
                        //.setLargeIcon(aBitmap)
                        .build();
            }*/
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }
    }
}
