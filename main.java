
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

interface Pembayaran {
    void prosesPembayaran(double total);
}

class FormatUang {
    public static String formatRupiah(double nominal) {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        return formatRupiah.format(nominal);
    }
}


class Menu {
    protected String nama;
    protected double harga;
    protected String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    void setNama(String nama) {
        this.nama = nama;
    }

    void setHarga(double harga) {
        this.harga = harga;
    }

    void setKategori(String kategori) {
        this.kategori = kategori;
    }


    public void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("Nama Menu: " + nama);
        System.out.println("Harga: " + FormatUang.rupiah(harga));
        System.out.println("Kategori: " + kategori);
    }

}

class Pelanggan {
    private String nama;
    private String alamat;
    private String nomorTelepon;

    public Pelanggan(String nama, String alamat, String nomorTelepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }

    public String getNama() {return nama;}
    public String getAlamat() {return alamat;}
    public String getNomorTelepon() {return nomorTelepon;}

    void setNama(String nama) {
        this.nama = nama;
    }

    void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
    public void displayPelanggan() {
        System.out.println("=== Pelanggan ===");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Nomor Telepon: " + nomorTelepon);
    }

}

class BayarTunai implements Pembayaran {
    @Override
    public void prosesPembayaran(double total) {
        System.out.println("Pembayaran tunai sebesar: " + FormatUang.rupiah(total));
        System.out.println("Silakan serahkan uang tunai kepada kasir.");
    }
}

class BayarQris implements Pembayaran {
    @Override
    public void prosesPembayaran(double total) {
        System.out.println("Pembayaran QRIS sebesar: " + FormatUang.rupiah(total));
        System.out.println("Silakan scan QR code untuk melakukan pembayaran.");
    }
}

class Pesanan {
    private Pelanggan pelanggan;
    private ArrayList<Menu> daftarMenu;
    private Pembayaran metodePembayaran;

    public Pesanan(Pelanggan pelanggan, Pembayaran metodePembayaran) {
        this.pelanggan = pelanggan;
        this.daftarMenu = new ArrayList<>();
        this.metodePembayaran = metodePembayaran;
    }

    public void tambahMenu(Menu menu) {
        daftarMenu.add(menu);
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public ArrayList<Menu> getDaftarMenu() {
        return daftarMenu;
    }

    public double hitungTotal() {
        double total = 0;
        for (Menu menu : daftarMenu) {
            total += menu.getHarga();
        }
        return total;
    }

    public void prosesPesanan() {
        double total = hitungTotal();
        System.out.println("Total pembayaran: " + FormatUang.rupiah(total));
        metodePembayaran.prosesPembayaran(total);
    }
}

class Struk {
    private Pesanan pesanan;


    public Struk(Pesanan pesanan) {
        this.pesanan = pesanan;
    }

    public void cetakStruk() {
        System.out.println("=== Struk Pembayaran ===");
        System.out.println("Nama Pelanggan: " + pesanan.getPelanggan().getNama());
        System.out.println("Alamat: " + pesanan.getPelanggan().getAlamat());
        System.out.println("Nomor Telepon: " + pesanan.getPelanggan().getNomorTelepon());
        System.out.println("Daftar Menu:");
        for (Menu menu : pesanan.getDaftarMenu()) {
            System.out.println("- " + menu.getNama() + " (" + menu.getKategori() + "): " + FormatUang.rupiah(menu.getHarga()));
        }
        System.out.println("Total: " + FormatUang.rupiah(pesanan.hitungTotal()));
    }
}




public class main {
    public static void main(String[] args) {
        // Membuat menu
        Menu menu1 = new Menu("Nasi Goreng", 15000, "Makanan");
        Menu menu2 = new Menu("Es Teh Manis", 5000, "Minuman");
        Menu menu3 = new Menu("Ayam Bakar", 20000, "Makanan");
        Menu menu4 = new Menu("Jus Jeruk", 8000, "Minuman");

        // Membuat pelanggan
        Pelanggan pelanggan1 = new Pelanggan("Budi", "Jl. Merdeka No. 123", "08123456789");
        Pelanggan pelanggan2 = new Pelanggan("Ani", "Jl. Sudirman No. 456", "08987654321");

        System.out.println("Pesanan 1:\n");
        // Membuat pesanan untuk pelanggan1 dengan metode pembayaran tunai
        Pesanan pesanan1 = new Pesanan(pelanggan1, new BayarTunai());
        pesanan1.tambahMenu(menu1);
        pesanan1.tambahMenu(menu2);
        pesanan1.prosesPesanan();
        Struk struk1 = new Struk(pesanan1);
        struk1.cetakStruk();

        System.out.println("\n====================\n");
        System.out.println("Pesanan 2:\n");

        // Membuat pesanan untuk pelanggan2 dengan metode pembayaran QRIS
        Pesanan pesanan2 = new Pesanan(pelanggan2, new BayarQris());
        pesanan2.tambahMenu(menu3);
        pesanan2.tambahMenu(menu4);
        pesanan2.prosesPesanan();
        
        Struk struk2 = new Struk(pesanan2);
        struk2.cetakStruk();

    }
}