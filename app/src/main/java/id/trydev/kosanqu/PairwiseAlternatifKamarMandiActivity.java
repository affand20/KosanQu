package id.trydev.kosanqu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.trydev.kosanqu.Model.Priority;
import id.trydev.kosanqu.Utils.AhpAlternatif;

public class PairwiseAlternatifKamarMandiActivity extends AppCompatActivity {

    private Button simpan;
    private RadioGroup kos1VsKos2, kos1VsKos3, kos1VsKos4, kos2VsKos3, kos2VsKos4, kos3VsKos4;
    private TextView hargaKos1VsKos2, hargaKos2VsKos1, hargaKos1VsKos3, hargaKos3VsKos1, hargaKos1VsKos4, hargaKos4VsKos1, hargaKos2VsKos3, hargaKos3VsKos2, hargaKos2VsKos4, hargaKos4VsKos2, hargaKos3VsKos4, hargaKos4VsKos3;

    private double pairwiseAlternativeArray[][] = new double[4][4];
    public static List<Priority> listAlternatifWithKamarMandiPriority = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairwise_alternatif_kamar_mandi);

        Log.d("ACTIVITY", getClass().getSimpleName());

        simpan = findViewById(R.id.simpan);
        kos1VsKos2 = findViewById(R.id.kos1vskos2);
        kos1VsKos3 = findViewById(R.id.kos1vskos3);
        kos1VsKos4 = findViewById(R.id.kos1vskos4);
        kos2VsKos3 = findViewById(R.id.kos2vskos3);
        kos2VsKos4 = findViewById(R.id.kos2vskos4);
        kos3VsKos4 = findViewById(R.id.kos3vskos4);
        hargaKos1VsKos2 = findViewById(R.id.hargakos1vskos2);
        hargaKos2VsKos1 = findViewById(R.id.hargakos2vskos1);
        hargaKos1VsKos3 = findViewById(R.id.hargakos1vskos3);
        hargaKos3VsKos1 = findViewById(R.id.hargakos3vskos1);
        hargaKos1VsKos4 = findViewById(R.id.hargakos1vskos4);
        hargaKos4VsKos1 = findViewById(R.id.hargakos4vskos1);
        hargaKos2VsKos3 = findViewById(R.id.hargakos2vskos3);
        hargaKos3VsKos2 = findViewById(R.id.hargakos3vskos2);
        hargaKos2VsKos4 = findViewById(R.id.hargakos2vskos4);
        hargaKos4VsKos2 = findViewById(R.id.hargakos4vskos2);
        hargaKos3VsKos4 = findViewById(R.id.hargakos3vskos4);
        hargaKos4VsKos3 = findViewById(R.id.hargakos4vskos3);

        hargaKos1VsKos2.setText(TextUtils.join("\n", MainActivity.listKos.get(0).getKamar_mandi()));
        hargaKos1VsKos3.setText(TextUtils.join("\n", MainActivity.listKos.get(0).getKamar_mandi()));
        hargaKos1VsKos4.setText(TextUtils.join("\n", MainActivity.listKos.get(0).getKamar_mandi()));
        hargaKos2VsKos1.setText(TextUtils.join("\n", MainActivity.listKos.get(1).getKamar_mandi()));
        hargaKos2VsKos3.setText(TextUtils.join("\n", MainActivity.listKos.get(1).getKamar_mandi()));
        hargaKos2VsKos4.setText(TextUtils.join("\n", MainActivity.listKos.get(1).getKamar_mandi()));
        hargaKos3VsKos1.setText(TextUtils.join("\n", MainActivity.listKos.get(2).getKamar_mandi()));
        hargaKos3VsKos2.setText(TextUtils.join("\n", MainActivity.listKos.get(2).getKamar_mandi()));
        hargaKos3VsKos4.setText(TextUtils.join("\n", MainActivity.listKos.get(2).getKamar_mandi()));
        hargaKos4VsKos1.setText(TextUtils.join("\n", MainActivity.listKos.get(3).getKamar_mandi()));
        hargaKos4VsKos2.setText(TextUtils.join("\n", MainActivity.listKos.get(3).getKamar_mandi()));
        hargaKos4VsKos3.setText(TextUtils.join("\n", MainActivity.listKos.get(3).getKamar_mandi()));

        initArray();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillArrayKos1VsKos2();
                fillArrayKos1VsKos3();
                fillArrayKos1VsKos4();
                fillArrayKos2VsKos3();
                fillArrayKos2VsKos4();
                fillArrayKos3VsKos4();

                System.out.println("PAIRWISE ARRAY CHECK");
                System.out.println("--------------------");
                for (int i = 0; i < pairwiseAlternativeArray.length; i++) {
                    for (int j = 0; j < pairwiseAlternativeArray.length; j++) {
                        System.out.print(pairwiseAlternativeArray[i][j]+" ");
                    }
                    System.out.println();
                }
                System.out.println("--------------------");
                System.out.println("PRINT DONE");

                AhpAlternatif ahp = new AhpAlternatif(pairwiseAlternativeArray);
                ahp.operate("kamarmandi");
                if (ahp.checkConsistency(4)){
                    listAlternatifWithKamarMandiPriority = ahp.getAlternatifPriorityExport();
                    if (FilterActivity.lastIndex<4){
                        switch (FilterActivity.listPriority.get(FilterActivity.lastIndex++).getNama()){
                            case "harga":
                                startActivity(new Intent(PairwiseAlternatifKamarMandiActivity.this, PairwiseAlternatifHargaActivity.class));
                                break;
                            case "luas kamar":
                                startActivity(new Intent(PairwiseAlternatifKamarMandiActivity.this, PairwiseAlternatifLuasKamarActivity.class));
                                break;
                            case "wifi":
                                startActivity(new Intent(PairwiseAlternatifKamarMandiActivity.this, PairwiseAlternatifWifi.class));
                                break;
                        }
                    } else{
                        startActivity(new Intent(PairwiseAlternatifKamarMandiActivity.this, ResultActivity.class));
                    }
                } else{
                    Toast.makeText(PairwiseAlternatifKamarMandiActivity.this, "Jawaban tidak konsisten", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FilterActivity.lastIndex--;
    }

    private void initArray() {
        pairwiseAlternativeArray[0][0] = 1;
        pairwiseAlternativeArray[1][1] = 1;
        pairwiseAlternativeArray[2][2] = 1;
        pairwiseAlternativeArray[3][3] = 1;
    }

    private void fillArrayKos1VsKos2(){
        switch (kos1VsKos2.getCheckedRadioButtonId()){
            case R.id.kos1vskos2kos1radio9:
                pairwiseAlternativeArray[0][1] = 9;
                pairwiseAlternativeArray[1][0] = (double)1/9;
                break;
            case R.id.kos1vskos2kos1radio7:
                pairwiseAlternativeArray[0][1] = 7;
                pairwiseAlternativeArray[1][0] = (double)1/7;
                break;
            case R.id.kos1vskos2kos1radio5:
                pairwiseAlternativeArray[0][1] = 5;
                pairwiseAlternativeArray[1][0] = (double)1/5;
                break;
            case R.id.kos1vskos2kos1radio3:
                pairwiseAlternativeArray[0][1] = 3;
                pairwiseAlternativeArray[1][0] = (double)1/3;
                break;
            case R.id.kos1vskos2radio1:
                pairwiseAlternativeArray[0][1] = 1;
                pairwiseAlternativeArray[1][0] = (double)1/1;
                break;
            case R.id.kos1vskos2kos2radio3:
                pairwiseAlternativeArray[0][1] = (double)1/3;
                pairwiseAlternativeArray[1][0] = 3;
                break;
            case R.id.kos1vskos2kos2radio5:
                pairwiseAlternativeArray[0][1] = (double)1/5;
                pairwiseAlternativeArray[1][0] = 5;
                break;
            case R.id.kos1vskos2kos2radio7:
                pairwiseAlternativeArray[0][1] = (double)1/7;
                pairwiseAlternativeArray[1][0] = 7;
                break;
            case R.id.kos1vskos2kos2radio9:
                pairwiseAlternativeArray[0][1] = (double)1/9;
                pairwiseAlternativeArray[1][0] = 9;
                break;
        }
    }

    private void fillArrayKos1VsKos3(){
        switch (kos1VsKos3.getCheckedRadioButtonId()){
            case R.id.kos1vskos3kos1radio9:
                pairwiseAlternativeArray[0][2] = 9;
                pairwiseAlternativeArray[2][0] = (double)1/9;
                break;
            case R.id.kos1vskos3kos1radio7:
                pairwiseAlternativeArray[0][2] = 7;
                pairwiseAlternativeArray[2][0] = (double)1/7;
                break;
            case R.id.kos1vskos3kos1radio5:
                pairwiseAlternativeArray[0][2] = 5;
                pairwiseAlternativeArray[2][0] = (double)1/5;
                break;
            case R.id.kos1vskos3kos1radio3:
                pairwiseAlternativeArray[0][2] = 3;
                pairwiseAlternativeArray[2][0] = (double)1/3;
                break;
            case R.id.kos1vskos3radio1:
                pairwiseAlternativeArray[0][2] = 1;
                pairwiseAlternativeArray[2][0] = (double)1/1;
                break;
            case R.id.kos3vskos1kos3radio3:
                pairwiseAlternativeArray[0][2] = (double)1/3;
                pairwiseAlternativeArray[2][0] = 3;
                break;
            case R.id.kos3vskos1kos3radio5:
                pairwiseAlternativeArray[0][2] = (double)1/5;
                pairwiseAlternativeArray[2][0] = 5;
                break;
            case R.id.kos3vskos1kos3radio7:
                pairwiseAlternativeArray[0][2] = (double)1/7;
                pairwiseAlternativeArray[2][0] = 7;
                break;
            case R.id.kos3vskos1kos3radio9:
                pairwiseAlternativeArray[0][2] = (double)1/9;
                pairwiseAlternativeArray[2][0] = 9;
                break;
        }
    }

    private void fillArrayKos1VsKos4(){
        switch (kos1VsKos4.getCheckedRadioButtonId()){
            case R.id.kos1vskos4kos1radio9:
                pairwiseAlternativeArray[0][3] = 9;
                pairwiseAlternativeArray[3][0] = (double)1/9;
                break;
            case R.id.kos1vskos4kos1radio7:
                pairwiseAlternativeArray[0][3] = 7;
                pairwiseAlternativeArray[3][0] = (double)1/7;
                break;
            case R.id.kos1vskos4kos1radio5:
                pairwiseAlternativeArray[0][3] = 5;
                pairwiseAlternativeArray[3][0] = (double)1/5;
                break;
            case R.id.kos1vskos4kos1radio3:
                pairwiseAlternativeArray[0][3] = 3;
                pairwiseAlternativeArray[3][0] = (double)1/3;
                break;
            case R.id.kos1vskos4radio1:
                pairwiseAlternativeArray[0][3] = 1;
                pairwiseAlternativeArray[3][0] = (double)1/1;
                break;
            case R.id.kos4vskos1kos4radio3:
                pairwiseAlternativeArray[0][3] = (double)1/3;
                pairwiseAlternativeArray[3][0] = 3;
                break;
            case R.id.kos4vskos1kos4radio5:
                pairwiseAlternativeArray[0][3] = (double)1/5;
                pairwiseAlternativeArray[3][0] = 5;
                break;
            case R.id.kos4vskos1kos4radio7:
                pairwiseAlternativeArray[0][3] = (double)1/7;
                pairwiseAlternativeArray[3][0] = 7;
                break;
            case R.id.kos4vskos1kos4radio9:
                pairwiseAlternativeArray[0][3] = (double)1/9;
                pairwiseAlternativeArray[3][0] = 9;
                break;
        }
    }

    private void fillArrayKos2VsKos3(){
        switch (kos2VsKos3.getCheckedRadioButtonId()){
            case R.id.kos2vskos3kos2radio9:
                pairwiseAlternativeArray[1][2] = 9;
                pairwiseAlternativeArray[2][1] = (double)1/9;
                break;
            case R.id.kos2vskos3kos2radio7:
                pairwiseAlternativeArray[1][2] = 7;
                pairwiseAlternativeArray[2][1] = (double)1/7;
                break;
            case R.id.kos2vskos3kos2radio5:
                pairwiseAlternativeArray[1][2] = 5;
                pairwiseAlternativeArray[2][1] = (double)1/5;
                break;
            case R.id.kos2vskos3kos2radio3:
                pairwiseAlternativeArray[1][2] = 3;
                pairwiseAlternativeArray[2][1] = (double)1/3;
                break;
            case R.id.kos2vskos3radio1:
                pairwiseAlternativeArray[1][2] = 1;
                pairwiseAlternativeArray[2][1] = (double)1/1;
                break;
            case R.id.kos3vskos2kos3radio3:
                pairwiseAlternativeArray[1][2] = (double)1/3;
                pairwiseAlternativeArray[2][1] = 3;
                break;
            case R.id.kos3vskos2kos3radio5:
                pairwiseAlternativeArray[1][2] = (double)1/5;
                pairwiseAlternativeArray[2][1] = 5;
                break;
            case R.id.kos3vskos2kos3radio7:
                pairwiseAlternativeArray[1][2] = (double)1/7;
                pairwiseAlternativeArray[2][1] = 7;
                break;
            case R.id.kos3vskos2kos3radio9:
                pairwiseAlternativeArray[1][2] = (double)1/9;
                pairwiseAlternativeArray[2][1] = 9;
                break;
        }
    }

    private void fillArrayKos2VsKos4(){
        switch (kos2VsKos4.getCheckedRadioButtonId()){
            case R.id.kos2vskos4kos2radio9:
                pairwiseAlternativeArray[1][3] = 9;
                pairwiseAlternativeArray[3][1] = (double)1/9;
                break;
            case R.id.kos2vskos4kos2radio7:
                pairwiseAlternativeArray[1][3] = 7;
                pairwiseAlternativeArray[3][1] = (double)1/7;
                break;
            case R.id.kos2vskos4kos2radio5:
                pairwiseAlternativeArray[1][3] = 5;
                pairwiseAlternativeArray[3][1] = (double)1/5;
                break;
            case R.id.kos2vskos4kos2radio3:
                pairwiseAlternativeArray[1][3] = 3;
                pairwiseAlternativeArray[3][1] = (double)1/3;
                break;
            case R.id.kos2vskos4radio1:
                pairwiseAlternativeArray[1][3] = 1;
                pairwiseAlternativeArray[3][1] = (double)1/1;
                break;
            case R.id.kos4vskos2kos4radio3:
                pairwiseAlternativeArray[1][3] = (double)1/3;
                pairwiseAlternativeArray[3][1] = 3;
                break;
            case R.id.kos4vskos2kos4radio5:
                pairwiseAlternativeArray[1][3] = (double)1/5;
                pairwiseAlternativeArray[3][1] = 5;
                break;
            case R.id.kos4vskos2kos4radio7:
                pairwiseAlternativeArray[1][3] = (double)1/7;
                pairwiseAlternativeArray[3][1] = 7;
                break;
            case R.id.kos4vskos2kos4radio9:
                pairwiseAlternativeArray[1][3] = (double)1/9;
                pairwiseAlternativeArray[3][1] = 9;
                break;
        }
    }

    private void fillArrayKos3VsKos4(){
        switch (kos3VsKos4.getCheckedRadioButtonId()){
            case R.id.kos3vskos4kos3radio9:
                pairwiseAlternativeArray[2][3] = 9;
                pairwiseAlternativeArray[3][2] = (double)1/9;
                break;
            case R.id.kos3vskos4kos3radio7:
                pairwiseAlternativeArray[2][3] = 7;
                pairwiseAlternativeArray[3][2] = (double)1/7;
                break;
            case R.id.kos3vskos4kos3radio5:
                pairwiseAlternativeArray[2][3] = 5;
                pairwiseAlternativeArray[3][2] = (double)1/5;
                break;
            case R.id.kos3vskos4kos3radio3:
                pairwiseAlternativeArray[2][3] = 3;
                pairwiseAlternativeArray[3][2] = (double)1/3;
                break;
            case R.id.kos3vskos4radio1:
                pairwiseAlternativeArray[2][3] = 1;
                pairwiseAlternativeArray[3][2] = (double)1/1;
                break;
            case R.id.kos4vskos3kos4radio3:
                pairwiseAlternativeArray[2][3] = (double)1/3;
                pairwiseAlternativeArray[3][2] = 3;
                break;
            case R.id.kos4vskos3kos4radio5:
                pairwiseAlternativeArray[2][3] = (double)1/5;
                pairwiseAlternativeArray[3][2] = 5;
                break;
            case R.id.kos4vskos3kos4radio7:
                pairwiseAlternativeArray[2][3] = (double)1/7;
                pairwiseAlternativeArray[3][2] = 7;
                break;
            case R.id.kos4vskos3kos4radio9:
                pairwiseAlternativeArray[2][3] = (double)1/9;
                pairwiseAlternativeArray[3][2] = 9;
                break;
        }
    }
}
