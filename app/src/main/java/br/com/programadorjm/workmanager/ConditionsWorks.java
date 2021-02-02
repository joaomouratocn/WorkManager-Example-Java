package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class ConditionsWorks extends AppCompatActivity {

    //Trabalho passa ser executados se algumas condiçoes forem atendidas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_works);

        Constraints constraints = new Constraints.Builder()
                //.setRequiredNetworkType(NetworkType.NOT_ROAMING)//Defite que para exeutar deve estar em WIFI
                .setRequiresBatteryNotLow(true)// se a bateria está em um nivel aceitavel para realizar o trabalho
                //.setRequiresCharging(true)// se o dispositivo esta carregando
                //.setRequiresDeviceIdle(true)//Se dispositivo esta ocioso
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                WorkManagerEx.class, 17, TimeUnit.MINUTES)
                //setando condições para rodar o trabalho
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);


        //OnTimeWorkReques com condições
        //OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder()
        //        .setConstraints(constraints)
        //        .build();

        //WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }
}