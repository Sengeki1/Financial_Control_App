package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DailyNotificationWorker extends Worker {

    public DailyNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Enviar a notificação
        sendNotification();
        return Result.success();
    }

    public void sendNotification() {
        String channedID = "daily_notification_channel";
        String channelName = "Notificações Diárias";

        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Criar o canal de notificação
            NotificationChannel channel = new NotificationChannel(
                    channedID, channelName, NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Criar a notificação
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channedID)
                .setContentTitle("Lembrete Diário")
                .setContentText("Não se esqueça de atualizar suas finanças hoje!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }
}
