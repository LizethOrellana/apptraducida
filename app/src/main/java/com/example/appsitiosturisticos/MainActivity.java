package com.example.appsitiosturisticos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.appsitiosturisticos.databinding.ActivityDetalleBinding;
import com.example.appsitiosturisticos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        binding.btnGuardar.setOnClickListener(v -> {
            String nombre = binding.txtNombre.getText().toString();
            String ubicacion = binding.txtUbicacion.getText().toString();
            String informacion = binding.txtInform.getText().toString();
            float valoracion = binding.rtbValoracion.getRating();
            abrirActivityDetalle(nombre, ubicacion, informacion, valoracion);


        });

        binding.imgSitio.setOnClickListener(v1 ->{
            AbrirCamara();
        });
    }
     private void AbrirCamara(){
        Intent camaraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(camaraIntent);
        startActivityForResult(camaraIntent,1000);
     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==1000){
            if(data!=null){
                bitmap= data.getExtras().getParcelable("data");
                binding.imgSitio.setImageBitmap(bitmap);
            }

        }
    }
    private void abrirActivityDetalle(String nom,String ubicacion, String informacion, float valoracion){
        Intent intent = new Intent(this, ActivityDetalle.class);
        SitioTuristico sitio = new SitioTuristico(nom,ubicacion,informacion,valoracion);
        intent.putExtra(ActivityDetalle.SITIO_TURISTICO_KEY, sitio);
        intent.putExtra(ActivityDetalle.BITMAP_KEY,bitmap);
        startActivity(intent);
    }
}