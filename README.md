# Sistem Pemesanan Restoran Sederhana (Java OOP)

## Deskripsi Kasus
Program ini mensimulasikan proses pemesanan di restoran sederhana, mulai dari pemilihan menu, perhitungan total belanja, pemrosesan pembayaran, sampai pencetakan struk.

Studi kasus yang diangkat:
1. Restoran memiliki beberapa item menu makanan dan minuman.
2. Pelanggan dapat membuat pesanan berisi banyak menu.
3. Sistem mendukung lebih dari satu metode pembayaran.
4. Sistem mencetak struk berisi data pelanggan, daftar menu, dan total pembayaran.

Implementasi menggunakan konsep OOP agar struktur program rapi, modular, dan mudah dikembangkan.

## Class Diagram
Class diagram dapat dilihat pada gambar berikut (folder components):

![Class Diagram](components/Payment%20Processing%20Model-2026-03-18-053124.png)

## Kode Program Java
Berikut source code utama pada file main.java:

```java
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

interface Pembayaran {
	void prosesPembayaran(double total);
}

class FormatUang {
	public static String rupiah(double nominal) {
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
```

## Screenshot Output
Screenshot output eksekusi program:

![Screenshot Output Program](components/output-program.png)

Jika file screenshot belum ada, jalankan program lalu simpan screenshot terminal ke path di atas agar gambar tampil otomatis di README.

Sebagai referensi, berikut contoh output teksnya:

```text
Pesanan 1:

Total pembayaran: Rp20.000,00
Pembayaran tunai sebesar: Rp20.000,00
Silakan serahkan uang tunai kepada kasir.
=== Struk Pembayaran ===
Nama Pelanggan: Budi
Alamat: Jl. Merdeka No. 123
Nomor Telepon: 08123456789
Daftar Menu:
- Nasi Goreng (Makanan): Rp15.000,00
- Es Teh Manis (Minuman): Rp5.000,00
Total: Rp20.000,00

====================

Pesanan 2:

Total pembayaran: Rp28.000,00
Pembayaran QRIS sebesar: Rp28.000,00
Silakan scan QR code untuk melakukan pembayaran.
=== Struk Pembayaran ===
Nama Pelanggan: Ani
Alamat: Jl. Sudirman No. 456
Nomor Telepon: 08987654321
Daftar Menu:
- Ayam Bakar (Makanan): Rp20.000,00
- Jus Jeruk (Minuman): Rp8.000,00
Total: Rp28.000,00
```

## Prinsip OOP yang Diterapkan
1. Encapsulation
Data penting disembunyikan menggunakan access modifier private, contohnya pada class Pelanggan dan Pesanan. Akses data dilakukan melalui getter/setter.

2. Abstraction
Interface Pembayaran menyembunyikan detail implementasi pembayaran. Class lain cukup tahu bahwa ada method prosesPembayaran.

3. Polymorphism
Pesanan menerima objek bertipe Pembayaran, tetapi implementasinya bisa BayarTunai atau BayarQris. Method yang dipanggil sama, perilakunya berbeda.

4. Composition
Class Pesanan memiliki relasi komposisi dengan Pelanggan dan daftar Menu. Class Struk juga menggunakan objek Pesanan untuk mencetak detail transaksi.

5. Single Responsibility (pendekatan desain OOP)
Setiap class memiliki tanggung jawab jelas:
- Menu untuk data menu
- Pelanggan untuk data pelanggan
- Pesanan untuk proses transaksi
- Struk untuk cetak bukti pembayaran
- BayarTunai/BayarQris untuk mekanisme pembayaran

## Keunikan Program
Keunikan yang ditonjolkan pada program ini:

1. Strategi pembayaran fleksibel berbasis interface
Metode pembayaran dapat diganti saat runtime (tunai/QRIS) tanpa mengubah class Pesanan.

2. Format nominal otomatis Rupiah
Semua nominal harga dan total ditampilkan dalam format mata uang Indonesia menggunakan helper FormatUang.

3. Simulasi multi-pelanggan dalam satu eksekusi
Program tidak hanya menampilkan satu transaksi, tetapi dua skenario transaksi berbeda untuk membuktikan reusability class.

4. Pemisahan alur bisnis dan output struk
Perhitungan pesanan dilakukan di class Pesanan, sedangkan tampilan bukti transaksi difokuskan di class Struk agar arsitektur lebih terstruktur.

Keunikan ini membuat program lebih siap dikembangkan, misalnya menambah metode pembayaran baru, diskon, pajak, atau penyimpanan riwayat transaksi.
