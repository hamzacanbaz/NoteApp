package com.example.architecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText addtitle;
    private EditText adddescription;
    private NumberPicker addpriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        addtitle = findViewById(R.id.add_title);
        adddescription = findViewById(R.id.add_description);
        addpriority = findViewById(R.id.add_priority);

        addpriority.setMinValue(1);
        addpriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            setTitle("Edit Note");
            addtitle.setText(intent.getStringExtra("title"));
            adddescription.setText(intent.getStringExtra("description"));
            addpriority.setValue(intent.getIntExtra("priority",1));
        }
        else setTitle("Add Note");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_note){
            saveNote();
        }
        return super.onOptionsItemSelected(item);

    }

    private void saveNote() {

    String title = addtitle.getText().toString();
    String description = adddescription.getText().toString();
    int priority = addpriority.getValue();

    if(title.trim().isEmpty() || description.trim().isEmpty()){
        Toast.makeText(this, "Fields must not be empty. ", Toast.LENGTH_SHORT).show();
        return;
    }

        Intent intent = new Intent();
    intent.putExtra("title",title);
    intent.putExtra("description",description);
    intent.putExtra("priority",priority);

    int id = getIntent().getIntExtra("id",-1);
    if (id!=-1){
        intent.putExtra("id",id);
    }

    setResult(RESULT_OK,intent);
    finish();


    }
}