package id.trydev.kosanqu.Utils;

import java.util.ArrayList;
import java.util.List;

import id.trydev.kosanqu.FilterActivity;
import id.trydev.kosanqu.Model.Priority;

public class AhpAlternatif {

    private double pairwiseTable[][];
    private double sumA, sumB, sumC, sumD;
    private double kos1LocalPriority, kos2LocalPriority, kos3LocalPriority, kos4LocalPriority;
    private double kos1Priority,kos2Priority,kos3Priority,kos4Priority;
    private double RITable[] = {0,0,0.58,0.9,1.12,1.24,1.32,1.41,1.45,1.49};

    public AhpAlternatif(double pairwiseTable[][]){
        this.pairwiseTable = pairwiseTable;
    }

    public void operate(String kriteria){
        sumTable();
        divElemWithSum();
        getPriority();
        getAlternatifPriority(kriteria);
    }

    private void getPriority(){
        System.out.println("GETTING LOCAL PRIORITY");
        System.out.println("-----------------");
        for (int i = 0; i < pairwiseTable.length; i++) {

            double priority = 0;
            for (int j = 0; j < pairwiseTable.length; j++) {
                priority+=pairwiseTable[i][j];
            }
            if (i==0) {
                kos1LocalPriority = priority/4;
            }
            if (i==1) {
                kos2LocalPriority = priority/4;
            }
            if (i==2) {
                kos3LocalPriority = priority/4;
            }
            if (i==3) {
                kos4LocalPriority = priority/4;
            }
        }
        System.out.println("harga = "+kos1LocalPriority);
        System.out.println("wifi = "+kos2LocalPriority);
        System.out.println("ac = "+kos3LocalPriority);
        System.out.println("kamar mandi = "+kos4LocalPriority);
    }

    private void getAlternatifPriority(String kriteria){
        System.out.println("GETTING ALTERNATIF PRIORITY");
        System.out.println("-----------------");
        for (int i = 0; i < pairwiseTable.length; i++) {
            double priority = 0;
            for (int j = 0; j < pairwiseTable.length; j++) {
                priority+=pairwiseTable[i][j];
            }
            switch (kriteria){
                case "harga":
                    if (i==0) kos1Priority = (priority/4)* FilterActivity.sumH;
                    if (i==1) kos2Priority = (priority/4)*FilterActivity.sumH;
                    if (i==2) kos3Priority = (priority/4)*FilterActivity.sumH;
                    if (i==3) kos4Priority = (priority/4)*FilterActivity.sumH;
                    break;
                case "wifi":
                    if (i==0) kos1Priority = (priority/4)*FilterActivity.sumW;
                    if (i==1) kos2Priority = (priority/4)*FilterActivity.sumW;
                    if (i==2) kos3Priority = (priority/4)*FilterActivity.sumW;
                    if (i==3) kos4Priority = (priority/4)*FilterActivity.sumW;
                    break;
                case "luaskamar":
                    if (i==0) kos1Priority = (priority/4)*FilterActivity.sumLK;
                    if (i==1) kos2Priority = (priority/4)*FilterActivity.sumLK;
                    if (i==2) kos3Priority = (priority/4)*FilterActivity.sumLK;
                    if (i==3) kos4Priority = (priority/4)*FilterActivity.sumLK;
                    break;
                case "kamarmandi":
                    if (i==0) kos1Priority = (priority/4)*FilterActivity.sumKM;
                    if (i==1) kos2Priority = (priority/4)*FilterActivity.sumKM;
                    if (i==2) kos3Priority = (priority/4)*FilterActivity.sumKM;
                    if (i==3) kos4Priority = (priority/4)*FilterActivity.sumKM;
                    break;
            }
        }
        System.out.println("kos1 = "+kos1Priority);
        System.out.println("kos2 = "+kos2Priority);
        System.out.println("kos3 = "+kos3Priority);
        System.out.println("kos4 = "+kos4Priority);
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
        double lambdaMax = (sumA*kos1LocalPriority)+(sumB*kos2LocalPriority)+(sumC*kos3LocalPriority)+(sumD*kos4LocalPriority);
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

    public List<Priority> getAlternatifPriorityExport(){
        List<Priority> listPriority = new ArrayList<>();
        listPriority.add(new Priority("kos1", kos1Priority));
        listPriority.add(new Priority("kos2", kos2Priority));
        listPriority.add(new Priority("kos3", kos3Priority));
        listPriority.add(new Priority("kos4", kos4Priority));

        System.out.println("PRIORITY TIAP KOS");
        System.out.println("-----------------");
        for (int i = 0; i < listPriority.size(); i++) {
            System.out.println(listPriority.get(i).getNama()+"->"+listPriority.get(i).getSkorPriority());
        }
        return listPriority;
    }
}
