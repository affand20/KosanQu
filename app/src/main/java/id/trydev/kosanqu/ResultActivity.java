package id.trydev.kosanqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import id.trydev.kosanqu.Adapter.KosAdapter;
import id.trydev.kosanqu.Model.Alternatif;
import id.trydev.kosanqu.Model.Kos;
import id.trydev.kosanqu.Model.Priority;

public class ResultActivity extends AppCompatActivity {

    public static List<Alternatif> listAlternatifWithPriority = new ArrayList<>();
    private List<Priority> listPriorityFinal = new ArrayList<>();
    public List<Kos> listKosFinal;

    private RecyclerView rvKos;
    private KosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listKosFinal = MainActivity.listKos;

        rvKos = findViewById(R.id.rv_kost);
        rvKos.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < PairwiseAlternatifHargaActivity.listAlternatifWithHargaPriority.size(); i++) {
            Alternatif kos = new Alternatif();
            kos.setNamaKos(PairwiseAlternatifHargaActivity.listAlternatifWithHargaPriority.get(i).getNama());
            kos.setPriorityHarga(PairwiseAlternatifHargaActivity.listAlternatifWithHargaPriority.get(i).getSkorPriority());
            kos.setPriorityLuasKamar(PairwiseAlternatifLuasKamarActivity.listAlternatifWithWifiPriority.get(i).getSkorPriority());
            kos.setPriorityWifi(PairwiseAlternatifWifi.listAlternatifWithWifiPriority.get(i).getSkorPriority());
            kos.setPriorityKamarMandi(PairwiseAlternatifKamarMandiActivity.listAlternatifWithKamarMandiPriority.get(i).getSkorPriority());

            listAlternatifWithPriority.add(kos);
        }

        System.out.println("---------DISPLAY KOS-----------");
        for (Alternatif kos : listAlternatifWithPriority){
            System.out.println("KOS : "+kos.getNamaKos());
            System.out.println("HARGA : "+kos.getPriorityHarga());
            System.out.println("WIFI : "+kos.getPriorityWifi());
            System.out.println("LUAS KAMAR : "+kos.getPriorityLuasKamar());
            System.out.println("KAMAR MANDI : "+kos.getPriorityKamarMandi());
            System.out.println("---------------------------");
            double priorityFinal = kos.getPriorityHarga()+kos.getPriorityKamarMandi()+kos.getPriorityLuasKamar()+kos.getPriorityWifi();

//            listAlternatifWithPriorityFinal.add()
            listPriorityFinal.add(new Priority(kos.getNamaKos(), priorityFinal));
        }

        for (int i = 0; i < listPriorityFinal.size(); i++) {
            listKosFinal.get(i).setPriority(listPriorityFinal.get(i).getSkorPriority());
        }

        Collections.sort(listKosFinal, new Comparator<Kos>() {
            @Override
            public int compare(Kos kos, Kos t1) {
                return kos.getPriority()>t1.getPriority() ? -1 : (t1.getPriority()<kos.getPriority()) ? 1 : 0;
            }
        });

        System.out.println("=========== DISPLAY PRIORITY KOS FINAL ===========");
        for (Kos kos : listKosFinal){
            System.out.println("KOS : "+kos.getNama());
            System.out.println("PRIORITAS : "+kos.getPriority());
            System.out.println("---------------------------");
        }
        System.out.println("==================================================");

        adapter = new KosAdapter(listKosFinal);
        rvKos.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
