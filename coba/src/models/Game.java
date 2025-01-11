package models;

public class Game {
    private String nama;
    private double harga;
    private String kategori;

    public Game(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getGenre() {
        return kategori;
    }

    public void setGenre(String genre) {
        this.kategori = genre;
    }

    @Override
    public String toString() {
        return "Game: " + nama + " || kategori: " + kategori + " || Harga: Rp" + harga;
    }
}
