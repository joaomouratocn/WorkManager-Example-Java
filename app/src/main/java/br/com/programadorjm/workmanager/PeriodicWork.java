package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class PeriodicWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_work);

        Button btnCancelPeriodic = findViewById(R.id.btn_periodc_cancel);

        //estanciando o trabalho
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                WorkManagerEx.class, 17, TimeUnit.MINUTES)
                .build();

        //passando um trabalho periodico para workmanager
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

        btnCancelPeriodic.setOnClickListener(v -> {
            //cancelando um periodicWork
            WorkManager.getInstance(this).cancelWorkById(periodicWorkRequest.getId());
        });

    }
}