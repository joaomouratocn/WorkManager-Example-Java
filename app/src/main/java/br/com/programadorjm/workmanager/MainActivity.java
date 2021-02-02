package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOnTime = findViewById(R.id.btn_onTime_work);
        Button btnOnTimeData = findViewById(R.id.btn_onTime_work_data);
        Button btnParallel = findViewById(R.id.btn_onTime_parallel_work);
        Button btnPeriodic = findViewById(R.id.btn_periodic_work);
        Button btnChainable = findViewById(R.id.btn_chainable_work);

        btnOnTime.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), OnTimeRequestWork.class);
            startActivity(intent);
        });

        btnOnTimeData.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), OnTimeRequestWorkWithData.class);
            startActivity(intent);
        });

        btnParallel.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ParallelWork.class);
            startActivity(intent);
        });

        btnPeriodic.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), PeriodicWork.class);
            startActivity(intent);
        });

        btnChainable.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ChainableWork.class);
            startActivity(intent);
        });
    }
}