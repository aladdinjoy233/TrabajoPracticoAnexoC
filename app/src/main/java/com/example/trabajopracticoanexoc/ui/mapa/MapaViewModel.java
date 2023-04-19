package com.example.trabajopracticoanexoc.ui.mapa;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaViewModel extends ViewModel {

    private static final LatLng AIELLO = new LatLng(-33.181587173345584, -66.31302381220519);
    private static final LatLng CHINO = new LatLng(-33.1826839279212, -66.31365440302528);
    private static final LatLng GRIDO = new LatLng(-33.18266539492199, -66.3140796509235);
    private MutableLiveData<MapaActual> mapa;

    public LiveData<MapaActual> getMapa() {
        if (mapa == null) {
            mapa = new MutableLiveData<MapaActual>();
        }
        return mapa;
    }

    public void marcar() { this.mapa.setValue(new MapaActual()); }

    public class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.addMarker(new MarkerOptions().position(AIELLO).title("Aiello La Punta"));
            googleMap.addMarker(new MarkerOptions().position(CHINO).title("Supermercado Chino"));
            googleMap.addMarker(new MarkerOptions().position(GRIDO).title("Grido"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(AIELLO)
                    .zoom(15)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(update);
        }
    }
}