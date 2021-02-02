  package br.com.programadorjm.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OnTimeRequestWorkWithData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_time_request_work_with_data);

        EditText txtMessege = findViewById(R.id.txt_msg);
        EditText txtValue = findViewById(R.id.txt_value);

        Button btnWork = findViewById(R.id.btn_work);
        btnWork.setOnClickListener(v -> {
            if(txtMessege.getText().toString().isEmpty() || txtMessege.getText() == null){
                Toast.makeText(getBaseContext(), "O Campo de mensagem deve ser preenchido", Toast.LENGTH_LONG).show();
            }else if(txtValue.getText().toString().isEmpty() || txtValue.getText() == null){
                Toast.makeText(getBaseContext(), "O Campo de valor de ser preenchido", Toast.LENGTH_LONG).show();
            }else {
                String msg = txtMessege.getText().toString();
                int value = Integer.parseInt(txtValue.getText().toString());

                //estanciando a data a ser passada
                Data inputData = new Data.Builder()
                        .putString("MSG", msg)
                        .putInt("VALUE", value)
                        .build();

                //creando a requisição de trabalho
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerEx.class)
                        .setInputData(inputData)
                        .build();

                //criando o trabalho
                WorkManager.getInstance(getBaseContext()).enqueue(oneTimeWorkRequest);

                //pegano as mudanças do trabalho
                WorkManager.getInstance(getBaseContext()).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                        .observe(this, workInfo -> {
                            if (workInfo != null && workInfo.getState().isFinished()){

                                //workInfo.getOutputData() pega o retorno do trabalho
                                Toast.makeText(getBaseContext(), workInfo.getOutputData().getString("MSG"), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}