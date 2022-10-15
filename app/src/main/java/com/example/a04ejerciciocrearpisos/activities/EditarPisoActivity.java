package com.example.a04ejerciciocrearpisos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04ejerciciocrearpisos.databinding.ActivityEditarPisoBinding;
import com.example.a04ejerciciocrearpisos.modelos.Piso;

public class EditarPisoActivity extends AppCompatActivity {

    private ActivityEditarPisoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditarPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnCancelarEditarPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearEditarPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso pisoEditado;

                if ((pisoEditado = editarPiso()) != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("EDITARPISO", pisoEditado);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(EditarPisoActivity.this, "Piso Editado", Toast.LENGTH_SHORT).show();
                    finish();
                    
                }else{
                    Toast.makeText(EditarPisoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso editarPiso() {
        if (binding.txtDireccionEditarPiso.getText().toString().isEmpty() ||
                binding.txtNumeroEditarPiso.getText().toString().isEmpty() ||
                binding.txtCiudadEditarPiso.getText().toString().isEmpty() ||
                binding.txtProvinciaEditarPiso.getText().toString().isEmpty() ||
                binding.txtCpEditarPiso.getText().toString().isEmpty() ||
                binding.rtbCalificacionEditarPiso.getNumStars() == 0){
            return null;

        }
        String direccion = binding.txtDireccionEditarPiso.getText().toString();
        int numero = Integer.parseInt(binding.txtNumeroEditarPiso.getText().toString());
        String ciudad = binding.txtCiudadEditarPiso.getText().toString();
        String provincia = binding.txtProvinciaEditarPiso.getText().toString();
        String cp = binding.txtCpEditarPiso.getText().toString();
        float valoracion = binding.rtbCalificacionEditarPiso.getNumStars();

        return new Piso(direccion, numero, ciudad, provincia, cp, valoracion);
    }
}