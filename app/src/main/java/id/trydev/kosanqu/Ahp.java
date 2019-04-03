package id.trydev.kosanqu;

public class Ahp {

    private double pairwiseTable[][] = new double[4][4];
    private double sumA, sumB, sumC, sumD;
    private double hargaPriority, wifiPriority, acPriority, kamarMandiPriority;


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

    private void getPriority(){
        System.out.println("GETTING PRIORITY");
        System.out.println("-----------------");
        for (int i = 0; i < pairwiseTable.length; i++) {
            double priority = 0;
            for (int j = 0; j < pairwiseTable.length; j++) {
                priority+=pairwiseTable[i][j];
            }
            if (i==0) hargaPriority = priority/4;
            if (i==1) wifiPriority = priority/4;
            if (i==2) acPriority = priority/4;
            if (i==3) kamarMandiPriority = priority/4;
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
}
