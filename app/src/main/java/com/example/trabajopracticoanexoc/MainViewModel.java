package com.example.trabajopracticoanexoc;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

public class MainViewModel extends AndroidViewModel {

    private Context context;
    private HashMap<String, String> usuarios;
    private MutableLiveData<String> error = null;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

//        Generar usuarios
        usuarios = new HashMap<>();
        usuarios.put("admin", "admin");
        usuarios.put("user", "user");
        usuarios.put("allan", "12345");
    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void login(String usuario, String password){
        if(usuarios.containsKey(usuario)){
            if(usuarios.get(usuario).equals(password)){
                error.setValue("");

                Intent intent = new Intent(context, NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("usuario", usuario);
                context.startActivity(intent);
            }else{
                error.setValue("Contraseña incorrecta");
            }
        }else{
            error.setValue("Usuario no registrado");
        }
    }

}
