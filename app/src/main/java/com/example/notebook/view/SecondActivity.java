package com.example.notebook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notebook.R;
import com.example.notebook.model.Notebook;
import com.example.notebook.viewmodel.Adapter;
import com.example.notebook.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    // поля
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd, fabDelete;

    private List<Notebook> notesList;

    private DatabaseHelper database;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycler_list);
        fabAdd = findViewById(R.id.fabAdd);
        fabDelete = findViewById(R.id.fabDelete);

        notesList = new ArrayList<>();
        database = new DatabaseHelper(this);

        fetchAllNotes();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, SecondActivity.this, notesList);
        recyclerView.setAdapter(adapter);

        // обработка нажатия кнопки
        fabAdd.setOnClickListener(listener);
        fabDelete.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fabAdd:
                    startActivity(new Intent(SecondActivity.this, AddNotesActivity.class));
                    break;
                case R.id.fabDelete:
                    database.deleteAllNotes();
                    break;
            }
        }
    };

    public void fetchAllNotes(){
        Cursor cursor = database.readNotes();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                notesList.add(new Notebook(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

}