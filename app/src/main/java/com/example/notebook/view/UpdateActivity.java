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

public class UpdateActivity extends AppCompatActivity {

    // поля
    private EditText title, description;
    private Button updateNote, deleteNote;
    private String id;
    private ImageButton arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        updateNote = findViewById(R.id.update_note);
        deleteNote = findViewById(R.id.delete_note);
        arrow = findViewById(R.id.arrow);

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");

        // обработка нажатия кнопки
        updateNote.setOnClickListener(listener);
        deleteNote.setOnClickListener(listener);
        arrow.setOnClickListener(listener);
    }
    // слушатель
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                DatabaseHelper database = new DatabaseHelper( UpdateActivity.this);

                switch (view.getId()) {
                    case R.id.update_note:
                        database.updateNotes(title.getText().toString(), description.getText().toString(), id);
                        break;
                    case R.id.delete_note:
                        database.deleteSingleItem(id);
                        break;
                    case R.id.arrow:
                        database.updateNotes(title.getText().toString(), description.getText().toString(), id);
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                }
                startActivity(new Intent(UpdateActivity.this, SecondActivity.class));
            } else {
                Toast.makeText(UpdateActivity.this, "Изменений не внесено", Toast.LENGTH_SHORT).show();
            }
        }
    };
}