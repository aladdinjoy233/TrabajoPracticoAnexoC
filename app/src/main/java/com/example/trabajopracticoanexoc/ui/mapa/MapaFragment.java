package com.example.trabajopracticoanexoc.ui.mapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopracticoanexoc.R;
import com.example.trabajopracticoanexoc.databinding.FragmentMapaBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class MapaFragment extends Fragment {

    private FragmentMapaBinding binding;
    private MapaViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MapaViewModel mapaViewModel = new ViewModelProvider(this).get(MapaViewModel.class);

        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new MapaViewModel();
        vm.getMapa().observe(getViewLifecycleOwner(), mapa -> {
            if (mapa != null) {
                SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                supportMapFragment.getMapAsync(mapa);
            }
        });

        vm.marcar();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}