package com.example.a04ejerciciocrearpisos;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04ejerciciocrearpisos.activities.EditarPisoActivity;
import com.example.a04ejerciciocrearpisos.activities.NuevoPisoActivity;
import com.example.a04ejerciciocrearpisos.databinding.ActivityEditarPisoBinding;
import com.example.a04ejerciciocrearpisos.modelos.Piso;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.a04ejerciciocrearpisos.databinding.ActivityMainBinding;


import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //1º-Definir el binding
    private ActivityMainBinding binding;
    private ActivityEditarPisoBinding bindingEditarPiso;
    private ActivityResultLauncher<Intent> launcherCrearPiso;
    private ActivityResultLauncher<Intent> launcherEditarPiso;

    // Elemeentos necesarios para mostrar datos en un contenedor:
    // Contenedor de los datos (binding.content.contenidoMain)
    // Los datos
    private ArrayList<Piso> listaPisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2º-Instanciar el binding al layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //3º-Asignacion del binding al contenido de la actividad.
        setContentView(binding.getRoot());

        listaPisos = new ArrayList<>();
        inicializaLaunchers();
        pintarPisos();
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPiso.launch(new Intent(MainActivity.this, NuevoPisoActivity.class));
            }
        });
    }

    private void pintarPisos() {
        binding.content.contenedorMain.removeAllViews();
        for (Piso piso:listaPisos) {
            View pisoView = LayoutInflater.from(this).inflate(R.layout.piso_model_view, null);
            TextView lblDireccion = pisoView.findViewById(R.id.lblDireccionPisoModelView);
            TextView lblNumero = pisoView.findViewById(R.id.lblNumeroPisoModelView);
            TextView lblCiudad = pisoView.findViewById(R.id.lblCiudadPisoModelView);
            TextView lblProvincia = pisoView.findViewById(R.id.lblProvinciaPisoModelView);
            TextView lblCp = pisoView.findViewById(R.id.lblCpPisoModelView);
            RatingBar rtbCalificacion = pisoView.findViewById(R.id.rtbCalificacionPisoModelView);
            Button btnEditar = pisoView.findViewById(R.id.btnEditarPisoModelView);

            lblDireccion.setText(piso.getDireccion());
            lblNumero.setText(String.valueOf(piso.getNumero()));
            lblCiudad.setText(piso.getCiudad());
            lblProvincia.setText(piso.getProvincia());
            lblCp.setText(piso.getCp());
            binding.content.contenedorMain.addView(pisoView);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launcherEditarPiso.launch(new Intent(MainActivity.this, EditarPisoActivity.class));
                }
            });
        }
    }

    private void inicializaLaunchers() {
        launcherCrearPiso = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null){
                            Piso piso = (Piso) result.getData().getExtras().getSerializable("PISO");
                            listaPisos.add(piso);
                            pintarPisos();
                        }
                    }
                }
        );

        launcherEditarPiso = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null){
                            Piso piso =  (Piso) result.getData().getExtras().getSerializable("EDITARPISO");
                            listaPisos.add(piso);
                            pintarPisos();
                        }
                    }
                }
        );
    }
}