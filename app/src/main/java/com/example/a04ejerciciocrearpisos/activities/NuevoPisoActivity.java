package com.example.a04ejerciciocrearpisos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04ejerciciocrearpisos.databinding.ActivityNuevoPisoBinding;
import com.example.a04ejerciciocrearpisos.modelos.Piso;

public class NuevoPisoActivity extends AppCompatActivity {

    private ActivityNuevoPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNuevoPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.btnCancelarNuevoPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        
        binding.btnCrearNuevoPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso nuevoPiso;
                
                if ((nuevoPiso = crearNuevoPiso()) != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PISO", nuevoPiso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(NuevoPisoActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso crearNuevoPiso() {
        if (binding.txtDireccionNuevoPiso.getText().toString().isEmpty() ||
        binding.txtNumeroNuevoPiso.getText().toString().isEmpty() ||
        binding.txtCiudadNuevoPiso.getText().toString().isEmpty() ||
        binding.txtProvinciaNuevoPiso.getText().toString().isEmpty() ||
        binding.txtCpNuevoPiso.getText().toString().isEmpty() ||
        binding.rtbValoracionNuevoPiso.getNumStars() == 0){
            return null;
            
        }
         String direccion = binding.txtDireccionNuevoPiso.getText().toString();
         int numero = Integer.parseInt(binding.txtNumeroNuevoPiso.getText().toString());
         String ciudad = binding.txtCiudadNuevoPiso.getText().toString();
         String provincia = binding.txtProvinciaNuevoPiso.getText().toString();
         String cp = binding.txtCpNuevoPiso.getText().toString();
        float valoracion = binding.rtbValoracionNuevoPiso.getNumStars();

        return new Piso(direccion, numero, ciudad, provincia, cp, valoracion);
    }
}