package id.trydev.kosanqu.Model;

public class Priority {

    private String nama;
    private double skorPriority;


    public Priority(){}

    public Priority(String nama, double skorPriority) {
        this.nama = nama;
        this.skorPriority = skorPriority;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getSkorPriority() {
        return skorPriority;
    }

    public void setSkorPriority(double skorPriority) {
        this.skorPriority = skorPriority;
    }
}
