package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

public class ChainableWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chainable_work);

        //estanciando os works
        OneTimeWorkRequest oneTimeWorkRequestA = new OneTimeWorkRequest.Builder(WorkManagerEx.class).build();
        OneTimeWorkRequest oneTimeWorkRequestB = new OneTimeWorkRequest.Builder(WorkManagerExB.class).build();
        OneTimeWorkRequest oneTimeWorkRequestC = new OneTimeWorkRequest.Builder(WorkManagerExC.class).build();

        //Encadeando os Works
        WorkManager.getInstance(this).beginWith(oneTimeWorkRequestA)
                .then(oneTimeWorkRequestB)
                .then(oneTimeWorkRequestC)
                .enqueue();
    }
}