package com.example.trabajopracticoanexoc;

import androidx.lifecycle.MutableLiveData;

public class NavigationViewModel {
    private MutableLiveData<Boolean> mutableDialogoSalida;

    public MutableLiveData<Boolean> getMutableDialogoSalida() {
        if (mutableDialogoSalida == null) {
            mutableDialogoSalida = new MutableLiveData<>();
        }
        return mutableDialogoSalida;
    }

    public void dialogoSalida() { mutableDialogoSalida.setValue(true); }
}
