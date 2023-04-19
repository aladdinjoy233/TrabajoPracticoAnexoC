package com.example.trabajopracticoanexoc.ui.telefono;

import static android.Manifest.permission.CALL_PHONE;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.trabajopracticoanexoc.databinding.FragmentTelefonoBinding;

public class TelefonoFragment extends Fragment {

    private FragmentTelefonoBinding binding;
    private TelefonoViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTelefonoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        Permisos de llamada
        if (ActivityCompat.checkSelfPermission(requireContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{CALL_PHONE}, 1);
        } else {
            binding.btnLlamar.setEnabled(true);
        }

        vm = new TelefonoViewModel();

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            binding.tvErroresTelefono.setText(error);
        });

        binding.btnLlamar.setOnClickListener(v -> {
            vm.hacerLlamada(binding.etTelefono.getText().toString(), requireContext());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.btnLlamar.setEnabled(true);
            } else {
                binding.tvErroresTelefono.setText("Permisos denegados");
            }
        }
    }

}