package id.trydev.kosanqu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.trydev.kosanqu.Adapter.KosAdapter;
import id.trydev.kosanqu.Model.Kos;
import id.trydev.kosanqu.Utils.ItemClickSupport;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListKos;
    private FirebaseDatabase database;
    private DatabaseReference myKos;
    private FloatingActionButton filterBtn;
    private ProgressBar progressBar;

    private KosAdapter adapter;
    private List<Kos> listKos= new ArrayList();


    //private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListKos = findViewById(R.id.rv_kost);
        filterBtn = findViewById(R.id.filter_btn);
        progressBar = findViewById(R.id.progress_bar);

        rvListKos.setLayoutManager(new LinearLayoutManager(this));

        database =FirebaseDatabase.getInstance();
        myKos = database.getReference("kosanqu");

        myKos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                     Kos value= dataSnapshot1.getValue(Kos.class);
                     listKos.add(value);
                 }

                 adapter = new KosAdapter(listKos);
                 progressBar.setVisibility(View.GONE);
                 rvListKos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        ItemClickSupport.addTo(rvListKos).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("kos_item", listKos.get(position));
                startActivity(intent);
            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FilterActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.filter){
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
