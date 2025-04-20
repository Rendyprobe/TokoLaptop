public class GamingLaptop extends Laptop {
    private String gpu;

    public GamingLaptop(String merk, String prosessor, int ram, int penyimpanan, String gpu) {
        super(merk, prosessor, ram, penyimpanan);
        this.gpu = gpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        return "GamingLaptop [" + getMerk() + "] - " + getProsessor() + ", RAM: " + getRam() +
               "GB, Storage: " + getPenyimpanan() + "GB, GPU: " + gpu;
    }
}
