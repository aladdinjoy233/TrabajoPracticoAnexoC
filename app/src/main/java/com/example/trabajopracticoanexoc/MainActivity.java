package com.example.trabajopracticoanexoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.trabajopracticoanexoc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);

        binding.btnLogin.setOnClickListener(view -> {
            vm.login(binding.etUsuario.getText().toString(), binding.etPassword.getText().toString());
        });

        vm.getError().observe(this, error -> {
            binding.tvErrores.setText(error);
        });
    }
}