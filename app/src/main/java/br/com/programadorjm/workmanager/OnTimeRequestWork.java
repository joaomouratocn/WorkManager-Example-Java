package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class OnTimeRequestWork extends AppCompatActivity {
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_time_request_work);

        //creando o requeste
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerEx.class)
                .build();
        //passando o requeste para o workmanager
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);

        //com WorkinfoLiveData, retorna as mudanças do work.
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                //estancia-se este observer para receber a tratar as mudanças
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        //capturando info do work
                        if (workInfo != null){
                            Toast.makeText(getBaseContext(), "OnTimeWorkRequest: " + String.valueOf(workInfo.getState().name()), Toast.LENGTH_SHORT).show();
                        }

                        if(workInfo != null && workInfo.getState().isFinished()){
                            //trate o resultado
                            Toast.makeText(getBaseContext(), "Work finished!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}