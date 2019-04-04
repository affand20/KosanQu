package id.trydev.kosanqu.Model;

public class Alternatif {

    private String namaKos;
    private double priorityHarga;
    private double priorityWifi;
    private double priorityLuasKamar;
    private double priorityKamarMandi;

    public Alternatif(){}

    public Alternatif(String namaKos, double priorityHarga, double priorityWifi, double priorityLuasKamar, double priorityKamarMandi) {
        this.namaKos = namaKos;
        this.priorityHarga = priorityHarga;
        this.priorityWifi = priorityWifi;
        this.priorityLuasKamar = priorityLuasKamar;
        this.priorityKamarMandi = priorityKamarMandi;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public double getPriorityHarga() {
        return priorityHarga;
    }

    public void setPriorityHarga(double priorityHarga) {
        this.priorityHarga = priorityHarga;
    }

    public double getPriorityWifi() {
        return priorityWifi;
    }

    public void setPriorityWifi(double priorityWifi) {
        this.priorityWifi = priorityWifi;
    }

    public double getPriorityLuasKamar() {
        return priorityLuasKamar;
    }

    public void setPriorityLuasKamar(double priorityLuasKamar) {
        this.priorityLuasKamar = priorityLuasKamar;
    }

    public double getPriorityKamarMandi() {
        return priorityKamarMandi;
    }

    public void setPriorityKamarMandi(double priorityKamarMandi) {
        this.priorityKamarMandi = priorityKamarMandi;
    }
}
