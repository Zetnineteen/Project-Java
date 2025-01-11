package models;

public class PCGame extends Game {
    private String requiredSpecs;

    public PCGame(String nama, double harga, String kategori, String requiredSpecs) {
        super(nama, harga, kategori);
        this.requiredSpecs = requiredSpecs;
    }

    public String getRequiredSpecs() {
        return requiredSpecs;
    }

    public void setRequiredSpecs(String requiredSpecs) {
        this.requiredSpecs = requiredSpecs;
    }

    @Override
    public String toString() {
        return super.toString() + " | Required Specs: " + requiredSpecs;
    }
}
