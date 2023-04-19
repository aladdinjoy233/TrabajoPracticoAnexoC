package com.example.trabajopracticoanexoc.ui.telefono;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TelefonoViewModel extends ViewModel {

    private MutableLiveData<String> error;

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<String>();
        }
        return error;
    }

    public void hacerLlamada(String telefono, Context context) {
//        Verficar que no este vacio el telefono
        if (telefono.isEmpty()) {
            error.setValue("El teléfono no puede estar vacío");
            return;
        }

//        Verificar que halla permiso de llamada
        if (ActivityCompat.checkSelfPermission(context, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            error.setValue("Permisos no habilitado");
            return;
        }

//        Llamar al telefono
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + telefono));
        error.setValue("");
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(callIntent);
    }

}