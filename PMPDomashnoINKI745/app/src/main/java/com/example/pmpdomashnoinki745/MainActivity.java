package com.example.pmpdomashnoinki745;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void saveToFile(View view) {
        String text = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("en_mk_recnik.txt", Context.MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(text);
            writer.newLine();
            writer.close();
            Toast.makeText(this, "Text saved to file.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchFile(View view) {
        EditText editText = findViewById(R.id.editText); // Assuming your EditText has id "editText"
        String searchTerm = editText.getText().toString().trim();

        try {
            FileInputStream fis = openFileInput("en_mk_recnik.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            StringBuilder result = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchTerm.toLowerCase())) {
                    // Append the matching line to the result
                    result.append(line).append("\n");
                }
            }
            reader.close();

            String content = result.toString();
            if (!content.isEmpty()) {
                Toast.makeText(this, "Search result:\n" + content, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Term not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "File not found.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}