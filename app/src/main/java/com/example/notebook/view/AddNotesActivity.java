package com.example.notebook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.notebook.R;
import com.example.notebook.viewmodel.DatabaseHelper;

public class AddNotesActivity extends AppCompatActivity {
    // поля
    private EditText title, description;
    private Button addNote;
    private ImageButton arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        addNote = findViewById(R.id.add_note);
        arrow = findViewById(R.id.arrow);

        // обработка нажатия кнопки
        addNote.setOnClickListener(listener);
        arrow.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add_note:
                    if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){

                        DatabaseHelper database = new DatabaseHelper(AddNotesActivity.this);
                        database.addNotes(title.getText().toString(), description.getText().toString());

                        Intent intent = new Intent(AddNotesActivity.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
                    } else {
                        Toast.makeText(AddNotesActivity.this, "Необходимо заполнить оба поля", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.arrow:
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    break;
            }
        }
    };
}