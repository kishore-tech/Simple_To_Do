package sg.edu.rp.c346.id18037611.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText task;
    Button btnadd;
    Button btndelete;
    Button btnclear;
    ListView lvtask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;
    Spinner spnAddRemove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editTextTask);
        btnadd = findViewById(R.id.buttonAdd);
        btndelete = findViewById(R.id.buttonDelete);
        btnclear = findViewById(R.id.buttonClear);
        lvtask = findViewById(R.id.listViewTask);
        spnAddRemove = findViewById(R.id.spinner);

        alTasks = new ArrayList<String>();

        aaTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTasks);
        lvtask.setAdapter(aaTasks);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        task.setHint(getString(R.string.add_task));
                        btndelete.setEnabled(false);
                        btnadd.setEnabled(true);
                        break;
                    case 1:
                        if (alTasks.size()!=0){
                            task.setHint(getString(R.string.remove_task));
                            btnadd.setEnabled(false);
                            btndelete.setEnabled(true);

                        }else{
                            Toast.makeText(MainActivity.this, R.string.no_task, Toast.LENGTH_SHORT).show();

                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Task = task.getText().toString();
                alTasks.add(Task);
                task.setText("");
                aaTasks.notifyDataSetChanged();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(task.getText().toString());
                if(index >= alTasks.size() || Integer.parseInt(task.getText().toString())<0){
                    Toast.makeText(MainActivity.this, R.string.wrong_index, Toast.LENGTH_SHORT).show();

                }else{
                    alTasks.remove(index);
                    task.setText("");
                    aaTasks.notifyDataSetChanged();
                }

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });
    }
}

