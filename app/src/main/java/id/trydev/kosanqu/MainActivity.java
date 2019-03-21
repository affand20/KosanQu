package id.trydev.kosanqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import id.trydev.kosanqu.Adapter.KosAdapter;
import id.trydev.kosanqu.Model.Kos;
import id.trydev.kosanqu.Utils.ItemClickSupport;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListKos;

    private KosAdapter adapter;
    private List<Kos> listKos = new ArrayList<>();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListKos = findViewById(R.id.rv_kost);
        rvListKos.setLayoutManager(new LinearLayoutManager(this));

        ItemClickSupport.addTo(rvListKos).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("kos_item", listKos.get(position));
                startActivity(intent);
            }
        });

        adapter = new KosAdapter(listKos);
        rvListKos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.filter){
            Toast.makeText(this, "Filter tapped !", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
