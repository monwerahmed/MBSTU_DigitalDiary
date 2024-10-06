package com.example.digital_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.digital_diary.databinding.ActivityRegentboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class regentboard extends AppCompatActivity {

// ActivityRegentboardBinding binding;
    DatabaseReference db1;
    ListView listView;
    ArrayAdapter<String> arrayAdapter1;
    ArrayList<String> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding=ActivityRegentboardBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
       setContentView(R.layout.activity_regentboard);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        db1 = FirebaseDatabase.getInstance().getReference("Regent Board");
        listView = findViewById(R.id.listView557);
        list1 = new ArrayList<String>();  //empty list
        arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(),R.layout.text_color_layout,list1);
        listView.setAdapter(arrayAdapter1);


                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list1.clear();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            String key = data.getKey();
                            stu obj1 = data.getValue(stu.class);
                            String output = "\n\t\t\t" + obj1.getName() + "\n\t\t\t" + obj1.getPosition() + "\n\t\t\t" + obj1.getNumber() + "\n\t\t\t"+obj1.getMail()+"\n";
                            list1.add(output);
                        }
                        arrayAdapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemData = list1.get(position);
                Intent intent = new Intent(getApplicationContext(), list.class);
                intent.putExtra("selectedData", selectedItemData);
                startActivity(intent);
            }
        });

    }
}