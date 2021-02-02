package br.com.programadorjm.workmanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/*
WorkManager e gerenciado pelo sistema atendento as configuraçãoe passadas a ele como:
*Qual condição realizar a terefa
*Se irá realizar uma unica vez ou repetidamente
*Em caso de falha qual ação tomar
 */

public class WorkManagerEx extends Worker{

    public WorkManagerEx(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @Override
    public Result doWork() {
        int count = 0;
        while (count < 10){
            try {
                Thread.sleep(1000);
                Log.d("test", "Count: " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.failure();// retorna que a terefa falhou
            }
            count++;
        }

        //pegando parametros passando na criação do work
        String msg = getInputData().getString("MSG");
        int value = getInputData().getInt("VALUE", 0); //segundo parametro é um valor default, caso VALUE retorne vazio

        //creando um estandia da saida do work
        Data outputData = new Data.Builder()
                .putString("MSG", msg)
                .putInt("VALUE", value)
                .build();

        return Result.success(outputData); //retorna que a terafa foi bem sucedida
        //return  Result.retry();//retorna que a terefa falhou e precisa ser chamada novamente
    }
}
