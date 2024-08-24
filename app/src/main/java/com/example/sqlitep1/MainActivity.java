package com.example.sqlitep1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear un registro
        dbHelper.createUser("Juan", 30);
        dbHelper.createUser("Maria", 25);

        // Leer registros
        List<String> users = dbHelper.readUsers();
        for (String user : users) {
            System.out.println(user);
        }

        // Actualizar un registro
        dbHelper.updateUser(1, "Juan", 18); // Reemplaza 1 con el ID del registro

        // Eliminar un registro
        dbHelper.deleteUser(2); // Reemplaza 2 con el ID del registro


        dbHelper.createProduct("TV",3500000,5);
        dbHelper.createProduct("PC",3200000,10);

        // Leer registros
        List<String> products = dbHelper.readProducts();
        for (String prodcut : products) {
            System.out.println(prodcut);
        }

        // Actualizar un registro
        dbHelper.updateProduct(1, "TV", 2000000, 10); // Reemplaza 1 con el ID del registro

        // Eliminar un registro
        dbHelper.deleteProduct(2); // Reemplaza 2 con el ID del registro
    }
}