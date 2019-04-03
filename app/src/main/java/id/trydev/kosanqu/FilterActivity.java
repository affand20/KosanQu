package id.trydev.kosanqu;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class FilterActivity extends AppCompatActivity {

    private TextInputEditText nama;
    private RadioGroup jk, hargaVsWifi, hargaVsAc, hargaVsKamarMandi, wifiVsAc, wifiVsKamarMandi, acVsKamarMandi;
    private Button simpan;

    private double pairwiseArray[][] = new double[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        nama = findViewById(R.id.isinama);
        jk = findViewById(R.id.jk);
        hargaVsWifi = findViewById(R.id.hargavswifi);
        hargaVsAc = findViewById(R.id.hargavsac);
        hargaVsKamarMandi = findViewById(R.id.hargavskamarmandi);
        wifiVsAc = findViewById(R.id.wifivsac);
        wifiVsKamarMandi = findViewById(R.id.wifivskamarmandi);
        acVsKamarMandi = findViewById(R.id.acvskamarmandi);
        simpan = findViewById(R.id.simpan);

        initArray();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillArrayHargaVsWifi();
                fillArrayHargaVsAc();
                fillArrayHargaVsKamarMandi();
                fillArrayWifiVsAc();
                fillArrayWifiVsKamarMandi();
                fillArrayAcKamarMandi();

                System.out.println("PAIRWISE ARRAY CHECK");
                System.out.println("--------------------");
                for (int i = 0; i < pairwiseArray.length; i++) {
                    for (int j = 0; j < pairwiseArray.length; j++) {
                        System.out.print(pairwiseArray[i][j]+" ");
                    }
                    System.out.println();
                }
                System.out.println("--------------------");
                System.out.println("PRINT DONE");

                Ahp ahp = new Ahp(pairwiseArray);
                ahp.operate();
            }
        });

    }

    private void initArray(){
        pairwiseArray[0][0] = 1;
        pairwiseArray[1][1] = 1;
        pairwiseArray[2][2] = 1;
        pairwiseArray[3][3] = 1;
    }

    private void fillArrayHargaVsWifi(){
        switch (hargaVsWifi.getCheckedRadioButtonId()){
            case R.id.hargavswifihargaradio9:
                pairwiseArray[0][1] = 9;
                pairwiseArray[1][0] = (double) 1/9;
                break;
            case R.id.hargavswifihargaradio7:
                pairwiseArray[0][1] = 7;
                pairwiseArray[1][0] = (double) 1/7;
                break;
            case R.id.hargavswifihargaradio5:
                pairwiseArray[0][1] = 5;
                pairwiseArray[1][0] = (double) 1/5;
                break;
            case R.id.hargavswifihargaradio3:
                pairwiseArray[0][1] = 3;
                pairwiseArray[1][0] = (double) 1/3;
                break;
            case R.id.hargavswifiradio1:
                pairwiseArray[0][1] = 1;
                pairwiseArray[1][0] = (double) 1/1;
                break;
            case R.id.hargavswifiwifiadio3:
                pairwiseArray[0][1] = (double) 1/3;
                pairwiseArray[1][0] =  3;
                break;
            case R.id.hargavswifiwifiadio5:
                pairwiseArray[0][1] = (double) 1/5;
                pairwiseArray[1][0] =  5;
                break;
            case R.id.hargavswifiwifiadio7:
                pairwiseArray[0][1] = (double) 1/7;
                pairwiseArray[1][0] =  7;
                break;
            case R.id.hargavswifiwifiadio9:
                pairwiseArray[0][1] = (double) 1/9;
                pairwiseArray[1][0] =  9;
                break;
        }
    }

    private void fillArrayHargaVsAc(){
        switch (hargaVsAc.getCheckedRadioButtonId()){
            case R.id.hargavsachargaradio9:
                pairwiseArray[0][2] = 9;
                pairwiseArray[2][0] = (double) 1/9;
                break;
            case R.id.hargavsachargaradio7:
                pairwiseArray[0][2] = 7;
                pairwiseArray[2][0] =  (double) 1/7;
                break;
            case R.id.hargavsachargaradio5:
                pairwiseArray[0][2] = 5;
                pairwiseArray[2][0] =  (double) 1/5;
                break;
            case R.id.hargavsachargaradio3:
                pairwiseArray[0][2] = 3;
                pairwiseArray[2][0] =  (double) 1/3;
                break;
            case R.id.hargavsacradio1:
                pairwiseArray[0][2] = 1;
                pairwiseArray[2][0] = (double)  1/1;
                break;
            case R.id.hargavsacacradio3:
                pairwiseArray[0][2] =(double)  1/3;
                pairwiseArray[2][0] =  3;
                break;
            case R.id.hargavsacacradio5:
                pairwiseArray[0][2] = (double) 1/5;
                pairwiseArray[2][0] =  5;
                break;
            case R.id.hargavsacacradio7:
                pairwiseArray[0][2] = (double) 1/7;
                pairwiseArray[2][0] =  7;
                break;
            case R.id.hargavsacacradio9:
                pairwiseArray[0][2] = (double) 1/9;
                pairwiseArray[2][0] =  9;
                break;
        }
    }

    private void fillArrayHargaVsKamarMandi(){
        switch (hargaVsKamarMandi.getCheckedRadioButtonId()){
            case R.id.hargavskamarmandihargaradio9:
                pairwiseArray[0][3] = 9;
                pairwiseArray[3][0] =  (double) 1/9;
                break;
            case R.id.hargavskamarmandihargaradio7:
                pairwiseArray[0][3] = 7;
                pairwiseArray[3][0] = (double)  1/7;
                break;
            case R.id.hargavskamarmandihargaradio5:
                pairwiseArray[0][3] = 5;
                pairwiseArray[3][0] =  (double) 1/5;
                break;
            case R.id.hargavskamarmandihargaradio3:
                pairwiseArray[0][3] = 3;
                pairwiseArray[3][0] =  (double) 1/3;
                break;
            case R.id.hargavskamarmandiradio1:
                pairwiseArray[0][3] = 1;
                pairwiseArray[3][0] =  (double) 1/1;
                break;
            case R.id.hargavskamarmandikamarmandiradio3:
                pairwiseArray[0][3] =(double)  1/3;
                pairwiseArray[3][0] =  3;
                break;
            case R.id.hargavskamarmandikamarmandiradio5:
                pairwiseArray[0][3] = (double) 1/5;
                pairwiseArray[3][0] =  5;
                break;
            case R.id.hargavskamarmandikamarmandiradio7:
                pairwiseArray[0][3] = (double) 1/7;
                pairwiseArray[3][0] =  7;
                break;
            case R.id.hargavskamarmandikamarmandiradio9:
                pairwiseArray[0][3] =(double)  1/9;
                pairwiseArray[3][0] =  9;
                break;
        }
    }

    private void fillArrayWifiVsAc(){
        switch (wifiVsAc.getCheckedRadioButtonId()){
            case R.id.wifivsacwifiradio9:
                pairwiseArray[1][2] = 9;
                pairwiseArray[2][1] =  (double) 1/9;
                break;
            case R.id.wifivsacwifiradio7:
                pairwiseArray[1][2] = 7;
                pairwiseArray[2][2] =  (double) 1/7;
                break;
            case R.id.wifivsacwifiradio5:
                pairwiseArray[1][2] = 5;
                pairwiseArray[2][1] =  (double) 1/5;
                break;
            case R.id.wifivsacwifiradio3:
                pairwiseArray[1][2] = 3;
                pairwiseArray[2][1] =  (double) 1/3;
                break;
            case R.id.wifivsacradio1:
                pairwiseArray[1][2] = 1;
                pairwiseArray[2][1] =  (double) 1/1;
                break;
            case R.id.wifivsacacradio3:
                pairwiseArray[1][2] = (double) 1/3;
                pairwiseArray[2][1] =  3;
                break;
            case R.id.wifivsacacradio5:
                pairwiseArray[1][2] = (double) 1/5;
                pairwiseArray[2][1] =  5;
                break;
            case R.id.wifivsacacradio7:
                pairwiseArray[1][2] = (double) 1/7;
                pairwiseArray[2][1] =  7;
                break;
            case R.id.wifivsacacradio9:
                pairwiseArray[1][2] = (double) 1/9;
                pairwiseArray[2][1] =  9;
                break;
        }
    }

    private void fillArrayWifiVsKamarMandi(){
        switch (wifiVsKamarMandi.getCheckedRadioButtonId()){
            case R.id.wifivskamarmandiwifiradio9:
                pairwiseArray[1][3] = 9;
                pairwiseArray[3][1] =  (double) 1/9;
                break;
            case R.id.wifivskamarmandiwifiradio7:
                pairwiseArray[1][3] = 7;
                pairwiseArray[3][1] =  (double) 1/7;
                break;
            case R.id.wifivskamarmandiwifiradio5:
                pairwiseArray[1][3] = 5;
                pairwiseArray[3][1] =  (double) 1/5;
                break;
            case R.id.wifivskamarmandiwifiradio3:
                pairwiseArray[1][3] = 3;
                pairwiseArray[3][1] =  (double) 1/3;
                break;
            case R.id.wifivskamarmandiradio1:
                pairwiseArray[1][3] = 1;
                pairwiseArray[3][1] =  (double) 1/1;
                break;
            case R.id.wifivskamarmandikamarmandiradio3:
                pairwiseArray[1][3] = (double) 1/3;
                pairwiseArray[3][1] =  3;
                break;
            case R.id.wifivskamarmandikamarmandiradio5:
                pairwiseArray[1][3] = (double) 1/5;
                pairwiseArray[3][1] =  5;
                break;
            case R.id.wifivskamarmandikamarmandiradio7:
                pairwiseArray[1][3] = (double) 1/7;
                pairwiseArray[3][1] =  7;
                break;
            case R.id.wifivskamarmandikamarmandiradio9:
                pairwiseArray[1][3] = (double) 1/9;
                pairwiseArray[3][1] =  9;
                break;
        }
    }

    private void fillArrayAcKamarMandi(){
        switch (acVsKamarMandi.getCheckedRadioButtonId()){
            case R.id.acvskamarmandiacradio9:
                pairwiseArray[2][3] = 9;
                pairwiseArray[3][2] =  (double) 1/9;
                break;
            case R.id.acvskamarmandiacradio7:
                pairwiseArray[2][3] = 7;
                pairwiseArray[3][2] =  (double) 1/7;
                break;
            case R.id.acvskamarmandiacradio5:
                pairwiseArray[2][3] = 5;
                pairwiseArray[3][2] =  (double) 1/5;
                break;
            case R.id.acvskamarmandiacradio3:
                pairwiseArray[2][3] = 3;
                pairwiseArray[3][2] =  (double) 1/3;
                break;
            case R.id.acvskamarmandiradio1:
                pairwiseArray[2][3] = 1;
                pairwiseArray[3][2] =  (double) 1/1;
                break;
            case R.id.acvskamarmandikamarmandiradio3:
                pairwiseArray[2][3] = (double) 1/3;
                pairwiseArray[3][2] =  3;
                break;
            case R.id.acvskamarmandikamarmandiradio5:
                pairwiseArray[2][3] = (double) 1/5;
                pairwiseArray[3][2] =  5;
                break;
            case R.id.acvskamarmandikamarmandiradio7:
                pairwiseArray[2][3] = (double) 1/7;
                pairwiseArray[3][2] =  7;
                break;
            case R.id.acvskamarmandikamarmandiradio9:
                pairwiseArray[2][3] = (double) 1/9;
                pairwiseArray[3][2] =  9;
                break;
        }
    }
}
