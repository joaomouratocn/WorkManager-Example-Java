package br.com.programadorjm.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManagerExC extends Worker {

    public WorkManagerExC(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {

        int count = 0;

        while (count < 10){
            try {
                Thread.sleep(1000);
                Log.d("teste", "Count: " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.failure();
            }
            count++;
        }
        return Result.success();
    }
}
