package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParallelWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallel_work);

        //estanciando works
        OneTimeWorkRequest oneTimeWorkRequestA = new OneTimeWorkRequest.Builder(WorkManagerEx.class)
                .build();

        OneTimeWorkRequest oneTimeWorkRequestB = new OneTimeWorkRequest.Builder(WorkManagerExB.class)
                .build();

        OneTimeWorkRequest oneTimeWorkRequestC = new OneTimeWorkRequest.Builder(WorkManagerExC.class)
                .build();

        //criando um lista de work para rodar em paralelo
        List<OneTimeWorkRequest> periodicWorkRequestList = new ArrayList<>();
        periodicWorkRequestList.add(oneTimeWorkRequestA);
        periodicWorkRequestList.add(oneTimeWorkRequestB);

        //passando works para o workmanager
        WorkManager.getInstance(this)
                .beginWith(periodicWorkRequestList)
                .then(oneTimeWorkRequestC)
                .enqueue();
    }
}