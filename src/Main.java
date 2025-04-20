import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Laptop> listLaptop = loadFromFile("list_laptop.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Tampilkan Semua Laptop");
                System.out.println("2. Cari Laptop Berdasar RAM");
                System.out.println("3. Cari Laptop Berdasar Merk");
                System.out.println("4. Cari Laptop Berdasar Penyimpanan");
                System.out.println("5. Urutkan Laptop Berdasar RAM");
                System.out.println("6. Tambah Laptop Baru");
                System.out.println("7. Simpan ke File");
                System.out.println("8. Keluar");

                System.out.print("Pilihan: ");
                String pilihan = scanner.nextLine();

                switch (pilihan) {
                    case "1":
                    if (listLaptop.isEmpty()) {
                        System.out.println("Laptop kosong");
                    } else {
                        for (Laptop l : listLaptop) {
                            System.out.println(l);
                        }
                    }
                    break;
                
                    case "2":
                        System.out.print("Masukkan RAM (GB): ");
                        int ram = Integer.parseInt(scanner.nextLine());
                        List<Laptop> hasilRam = Laptop.cariLaptop(listLaptop, ram);
                        hasilRam.forEach(System.out::println);
                        break;

                    case "3":
                        System.out.print("Masukkan merk: ");
                        String merk = scanner.nextLine();
                        listLaptop.stream()
                                .filter(l -> l.getMerk().equalsIgnoreCase(merk))
                                .forEach(System.out::println);
                        break;

                    case "4":
                        System.out.print("Masukkan penyimpanan (GB): ");
                        int storage = Integer.parseInt(scanner.nextLine());
                        listLaptop.stream()
                                .filter(l -> l.getPenyimpanan() == storage)
                                .forEach(System.out::println);
                        break;

                    case "5":
                        System.out.println("1. Urutkan dari terkecil");
                        System.out.println("2. Urutkan dari terbesar");
                        String sort = scanner.nextLine();
                        if (sort.equals("1")) {
                            Laptop.quickSortAscending(listLaptop);
                        } else {
                            Laptop.quickSortDescending(listLaptop);
                        }
                        System.out.println("Setelah Diurutkan:");
                        listLaptop.forEach(System.out::println);
                        break;

                    case "6":
                        System.out.print("Merk: ");
                        String m = scanner.nextLine();
                        System.out.print("Processor: ");
                        String p = scanner.nextLine();
                        System.out.print("RAM (GB): ");
                        int r = Integer.parseInt(scanner.nextLine());
                        System.out.print("Penyimpanan (GB): ");
                        int s = Integer.parseInt(scanner.nextLine());

                        System.out.print("Apakah laptop ini Gaming? (y/n): ");
                        String isGaming = scanner.nextLine();
                        if (isGaming.equalsIgnoreCase("y")) {
                            System.out.print("GPU: ");
                            String g = scanner.nextLine();
                            listLaptop.add(new GamingLaptop(m, p, r, s, g));
                        } else {
                            listLaptop.add(new Laptop(m, p, r, s));
                        }
                        System.out.println("Laptop berhasil ditambahkan.");
                        break;

                    case "7":
                        try (PrintWriter writer = new PrintWriter("list_laptop.txt")) {
                            for (Laptop l : listLaptop) {
                                if (l instanceof GamingLaptop) {
                                    GamingLaptop g = (GamingLaptop) l;
                                    writer.printf("Gaming;%s;%s;%d;%d;%s%n", g.getMerk(), g.getProsessor(), g.getRam(), g.getPenyimpanan(), g.getGpu());
                                } else {
                                    writer.printf("Laptop;%s;%s;%d;%d%n", l.getMerk(), l.getProsessor(), l.getRam(), l.getPenyimpanan());
                                }
                            }
                            System.out.println("Data disimpan ke list_laptop.txt");
                        } catch (IOException e) {
                            System.out.println("Gagal menyimpan file.");
                        }
                        break;


                    case "8":
                        System.out.println("Keluar dari program.");
                        return;

                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            }
        }
    }

    public static ArrayList<Laptop> loadFromFile(String filename) {
        ArrayList<Laptop> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals("Gaming") && parts.length == 6) {
                    list.add(new GamingLaptop(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]));
                } else if (parts[0].equals("Laptop") && parts.length == 5) {
                    list.add(new Laptop(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                }
            }
        } catch (IOException e) {
        }
        return list;
    }
}
