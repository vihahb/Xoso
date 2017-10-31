package com.xproject.xoso.sdk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.xproject.xoso.R;

/**
 * Helper class for showing and canceling message
 * notifications.
 * <p>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class MessageNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private static final String NOTIFICATION_TAG = "Message";

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * <p>
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     * <p>
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of message notifications. Make
     * sure to follow the
     * <a href="https://developer.android.com/design/patterns/notifications.html">
     * Notification design guidelines</a> when doing so.
     *
     * @see #cancel(Context, int)
     */
    public static void notify(final Context context,
                              final String MessageString, final int id_notify, Intent intent) {
        final Resources res = context.getResources();

        // This image is used as the notification's large icon (thumbnail).
        // TODO: Remove this if your notification has no relevant thumbnail.
        Bitmap picture = null;
        switch (id_notify) {
            case 1:
                picture = BitmapFactory.decodeResource(res, R.mipmap.ic_nav_north);
                break;
            case 2:
                picture = BitmapFactory.decodeResource(res, R.mipmap.ic_nav_central);
                break;
            case 3:
                picture = BitmapFactory.decodeResource(res, R.mipmap.ic_nav_south);
                break;
        }

        String uri_parse = "android.resource://" + context.getPackageName() + "/" + R.raw.notification11;

        Uri uri = Uri.parse(uri_parse);

        final String ticker = MessageString;
        final String title = "Thông báo";
        final String text = MessageString;

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                // Set appropriate defaults for the notification light, sound,
                // and vibration.
//                .setDefaults(Notification.DEFAULT_ALL)
                .setVibrate(new long[]{50,100,150})
                // Set required fields, including the small icon, the
                // notification title, and text.
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)

                // All fields below this line are optional.

                // Use a default priority (recognized on devices running Android
                // 4.1 or later)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

                // Provide a large icon, shown with the notification in the
                // notification drawer on devices running Android 3.0 or later.
                // Set ticker text (preview) information for this notification.
                .setTicker(ticker)

                .setVibrate(new long[]{50})
                // If this notification relates to a past or upcoming event, you
                // should set the relevant time information using the setWhen
                // method below. If this call is omitted, the notification's
                // timestamp will by set to the time at which it was shown.
                // TODO: Call setWhen if this notification relates to a past or
                // upcoming event. The sole argument to this method should be
                // the notification timestamp in milliseconds.
                //.setWhen(...)

                // Set the pending intent to be initiated when the user touches
                // the notification.
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT))

                // Example additional actions for this notification. These will
                // only show on devices running Android 4.1 or later, so you
                // should ensure that the activity in this notification's
                // content intent provides access to the same actions in
                // another way.

                .addAction(
                        R.drawable.ic_action_stat_reply,
                        "Open",
                        PendingIntent.getActivity(
                                context,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT))
                // Automatically dismiss the notification when it is touched.
                .setSound(uri)
                .setAutoCancel(true);
        if (picture != null) {
            builder.setLargeIcon(picture);
        }


        notify(context, builder.build(), id_notify);
    }

    private static void notify(final Context context, final Notification notification, int id_notify) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_TAG, id_notify, notification);
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, int, Intent)}.
     */
    public static void cancel(final Context context, int id_notify) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_TAG, id_notify);
    }

    static int changeAlpha(int origColor, int userInputedAlpha) {
        origColor = origColor & 0x00ffffff; //drop the previous alpha value
        return (userInputedAlpha << 24) | origColor; //add the one the user inputted
    }
}
