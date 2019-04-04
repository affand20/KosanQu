package id.trydev.kosanqu.Model;

import java.io.Serializable;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kos implements Serializable {

    private String alamat;
    private String harga;
    private String jenis;
    private List<String> kamar_mandi;
    private String luas_kamar;
    private String nama;
    private String sistem_pembayaran;
    private String wifi;
    private double priority;

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Kos(String alamat, String harga, String jenis, List<String> kamar_mandi, String luas_kamar, String nama, String sistem_pembayaran, String wifi) {
        this.alamat = alamat;
        this.harga = harga;
        this.jenis = jenis;
        this.kamar_mandi = kamar_mandi;
        this.luas_kamar = luas_kamar;
        this.nama = nama;
        this.sistem_pembayaran = sistem_pembayaran;
        this.wifi = wifi;
    }

    public Kos(){}

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public List<String> getKamar_mandi() {
        return kamar_mandi;
    }

    public void setKamar_mandi(List<String> kamar_mandi) {
        this.kamar_mandi = kamar_mandi;
    }

    public String getLuas_kamar() {
        return luas_kamar;
    }

    public void setLuas_kamar(String luas_kamar) {
        this.luas_kamar = luas_kamar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSistem_pembayaran() {
        return sistem_pembayaran;
    }

    public void setSistem_pembayaran(String sistem_pembayaran) {
        this.sistem_pembayaran = sistem_pembayaran;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
}
