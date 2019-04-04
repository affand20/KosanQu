package id.trydev.kosanqu;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import id.trydev.kosanqu.Model.Priority;

public class Ahp {

    private double pairwiseTable[][];
    private double sumA, sumB, sumC, sumD;
    private double hargaPriority, wifiPriority, acPriority, kamarMandiPriority;
    private double kos1Priority,kos2Priority,kos3Priority,kos4Priority;
    private double RITable[] = {0,0,0.58,0.9,1.12,1.24,1.32,1.41,1.45,1.49};

    public Ahp(double pairwiseTable[][]){
        this.pairwiseTable = pairwiseTable;
    }

    public void operate(){
        sumTable();
        divElemWithSum();
        getPriority();
    }

    private void validateTable(){
        //
    }

    public List<Priority> getSortedPriority(){
        List<Priority> listPriority = new ArrayList<>();
        listPriority.add(new Priority("harga", hargaPriority));
        listPriority.add(new Priority("wifi", wifiPriority));
        listPriority.add(new Priority("kamar mandi", kamarMandiPriority));
        listPriority.add(new Priority("luas kamar", acPriority));

        Collections.sort(listPriority, new Comparator<Priority>() {
            @Override
            public int compare(Priority priority, Priority t1) {
                return priority.getSkorPriority()>t1.getSkorPriority() ? -1 : (t1.getSkorPriority()<priority.getSkorPriority()) ? 1 : 0;
            }
        });
        return listPriority;
    }

    private void getPriority(){
        System.out.println("GETTING PRIORITY");
        System.out.println("-----------------");
        for (int i = 0; i < pairwiseTable.length; i++) {

            double priority = 0;
            for (int j = 0; j < pairwiseTable.length; j++) {
                priority+=pairwiseTable[i][j];
            }
            if (i==0) {
                hargaPriority = priority/4;
                FilterActivity.sumH = hargaPriority;
            }
            if (i==1) {
                wifiPriority = priority/4;
                FilterActivity.sumW = wifiPriority;
            }
            if (i==2) {
                acPriority = priority/4;
                FilterActivity.sumLK = acPriority;
            }
            if (i==3) {
                kamarMandiPriority = priority/4;
                FilterActivity.sumKM = kamarMandiPriority;
            }
        }
        System.out.println("harga = "+hargaPriority);
        System.out.println("wifi = "+wifiPriority);
        System.out.println("ac = "+acPriority);
        System.out.println("kamar mandi = "+kamarMandiPriority);
    }

    private void sumTable(){
        System.out.println("SUMMING EACH COLLUMNS OF TABLE");
        for (int i = 0; i < pairwiseTable.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < pairwiseTable.length; j++) {
                sum+=pairwiseTable[j][i];
            }
            if (i==0) sumA = sum;
            if (i==1) sumB = sum;
            if (i==2) sumC = sum;
            if (i==3) sumD = sum;
        }
        System.out.println("CHECK SUM ABCD");
        System.out.println("--------------");
        System.out.print(sumA+" "+sumB+" "+sumC+" "+sumD+"\n");
        System.out.println("--------------");
        System.out.println("DONE");
    }

    private void divElemWithSum(){
        System.out.println("DIVIDING EACH ELEMENT OF COLLUMN WITH SUM EACH COLLUMN");
        for (int i = 0; i < pairwiseTable.length; i++) {
            System.out.println("UPDATING COLLUMN-"+(i+1));
            for (int j = 0; j < pairwiseTable.length; j++) {
                if (i==0)
                    pairwiseTable[j][i]/=sumA;
                if (i==1)
                    pairwiseTable[j][i]/=sumB;
                if (i==2)
                    pairwiseTable[j][i]/=sumC;
                if (i==3)
                    pairwiseTable[j][i]/=sumD;
            }
        }
        display();
        System.out.println("DONE");
    }

    private void display(){
        System.out.println("DISPLAYING PAIRWISE TABLE");
        System.out.println("--------------------------");
        for (int i = 0; i < pairwiseTable.length; i++) {
            for (int j = 0; j < pairwiseTable.length; j++) {
                System.out.print(pairwiseTable[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean checkConsistency(int totalCriteria){
        System.out.println("CHECK CONSISTENCY");
        System.out.println("-----------------");
        double lambdaMax = (sumA*hargaPriority)+(sumB*wifiPriority)+(sumC*acPriority)+(sumD*kamarMandiPriority);
        double CI = (lambdaMax-totalCriteria)/(totalCriteria-1);
        double CR = CI/RITable[totalCriteria-1];
        System.out.println("Lambda Max = "+lambdaMax);
        System.out.println("CI = "+CI);
        System.out.println("CR = "+CR);
        if (CR<0.1) {
            System.out.println("CONSISTENCY PASSED !");
            return true;
        }
        else {
            System.out.println("CONSISTENCY NOT PASSED !");
            return false;
        }
    }
}
