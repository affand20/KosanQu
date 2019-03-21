package id.trydev.kosanqu.Model;

import java.io.Serializable;

public class Kos implements Serializable {

    private String judul;
    private String alamat;
    private String harga;
    private String jenisPenghuni;
    private String[] fasilitas;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

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

    public String getJenisPenghuni() {
        return jenisPenghuni;
    }

    public void setJenisPenghuni(String jenisPenghuni) {
        this.jenisPenghuni = jenisPenghuni;
    }

    public String[] getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String[] fasilitas) {
        this.fasilitas = fasilitas;
    }
}
