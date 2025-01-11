package models;

public class MobileGame extends Game {
    private String minimumOS;

    public MobileGame(String nama, double harga, String kategori, String minimumOS) {
        super(nama, harga, kategori);
        this.minimumOS = minimumOS;
    }

    public String getMinimumOS() {
        return minimumOS;
    }

    public void setMinimumOS(String minimumOS) {
        this.minimumOS = minimumOS;
    }

    @Override
    public String toString() {
        return super.toString() + " | Minimum OS: " + minimumOS;
    }
}
