import java.util.ArrayList;
import java.util.List;

class Laptop {
    private String Merk;
    private String Prosessor;
    private int Ram;
    protected int Penyimpanan;

    public Laptop(String Merk, String Prosessor, int Ram, int Penyimpanan) {
        this.Merk = Merk;
        this.Prosessor = Prosessor;
        this.Ram = Ram;
        this.Penyimpanan = Penyimpanan;
    }

    public void setMerk(String Merk) {
        this.Merk = Merk;
    }

    public String getMerk() {
        return Merk;
    }

    public void setProsessor(String Prosessor) {
        this.Prosessor = Prosessor;
    }

    public String getProsessor() {
        return Prosessor;
    }

    public void setRam(int Ram) {
        if (Ram >= 4) {
            this.Ram = Ram;
        } else {
            System.out.println(">>> RAM minimal 4GB <<<");
        }
    }

    public int getRam() {
        return Ram;
    }

    public void setPenyimpanan(int Penyimpanan) {
        int[] validValues = {32, 64, 128, 256, 512, 1024, 2048};
        boolean valid = false;

        for (int v : validValues) {
            if (Penyimpanan == v) {
                valid = true;
                break;
            }
        }

        if (valid) {
            this.Penyimpanan = Penyimpanan;
        } else {
            System.out.println(">>> Nilai penyimpanan tidak valid <<<");
        }
    }

    public int getPenyimpanan() {
        return Penyimpanan;
    }

    public static List<Laptop> cariLaptop(List<Laptop> listLaptop, int Ram) {
        List<Laptop> hasilPencarian = new ArrayList<>();
        for (Laptop laptop : listLaptop) {
            if (laptop.getRam() == Ram) {
                hasilPencarian.add(laptop);
            }
        }
        return hasilPencarian;
    }

    public static void quickSortAscending(ArrayList<Laptop> list) {
        quickSortLoop(list, 0, list.size() - 1, true);
    }

    public static void quickSortDescending(ArrayList<Laptop> list) {
        quickSortLoop(list, 0, list.size() - 1, false);
    }

    private static void quickSortLoop(ArrayList<Laptop> daftar, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(daftar, low, high, ascending);
            quickSortLoop(daftar, low, pi - 1, ascending);
            quickSortLoop(daftar, pi + 1, high, ascending);
        }
    }

    private static int partition(ArrayList<Laptop> daftar, int low, int high, boolean ascending) {
        int pivot = daftar.get(high).getRam();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && daftar.get(j).getRam() <= pivot) || (!ascending && daftar.get(j).getRam() >= pivot)) {
                i++;
                Laptop temp = daftar.get(i);
                daftar.set(i, daftar.get(j));
                daftar.set(j, temp);
            }
        }

        Laptop temp = daftar.get(i + 1);
        daftar.set(i + 1, daftar.get(high));
        daftar.set(high, temp);

        return i + 1;
    }

    @Override
    public String toString() {
        return "Merk: " + Merk + " | Prosessor: " + Prosessor + " | RAM: " + Ram + "GB | Penyimpanan: " + Penyimpanan + "GB";
    }
}
