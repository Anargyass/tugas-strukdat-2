# 🛒 Sistem Manajemen Pemesanan Restoran - Java OOP

Proyek ini adalah implementasi sederhana dari sistem pemesanan restoran berbasis Java yang menerapkan konsep-konsep utama **Object-Oriented Programming (OOP)**. Sistem ini dirancang untuk menangani data menu, pelanggan, transaksi pemesanan, hingga pencetakan struk pembayaran.

---

## 🚀 Konsep OOP yang Diterapkan

Dalam proyek ini, saya telah menerapkan beberapa pilar penting dalam pemrograman berorientasi objek:

* **Encapsulation (Enkapsulasi):** Penggunaan hak akses `private` dan `protected` pada atribut kelas (seperti di kelas `Pelanggan` dan `Menu`) serta penyediaan metode *getter* dan *setter*.
* **Inheritance & Polymorphism:** Penggunaan `interface Pembayaran` yang diimplementasikan oleh kelas `BayarTunai` dan `BayarQris`, memungkinkan sistem menangani berbagai metode pembayaran secara dinamis.
* **Abstraction (Abstraksi):** Memisahkan logika proses pembayaran melalui antarmuka (`interface`) agar kelas `Pesanan` tidak perlu mengetahui detail teknis dari masing-masing metode bayar.
* **Composition (Komposisi):** Kelas `Pesanan` memiliki relasi objek dengan kelas `Pelanggan` dan `Menu` (menggunakan `ArrayList`).

---

## 🛠️ Struktur Kelas

| Kelas / Interface | Deskripsi |
| :--- | :--- |
| **`Pembayaran`** | *Interface* dasar untuk mendefinisikan kontrak metode pembayaran. |
| **`FormatUang`** | Kelas utilitas untuk memformat angka menjadi mata uang Rupiah (IDR). |
| **`Menu`** | Representasi entitas menu (makanan/minuman). |
| **`Pelanggan`** | Menyimpan profil data diri pelanggan. |
| **`Pesanan`** | Inti dari sistem yang mengelola logika penambahan menu, penghitungan total, dan eksekusi bayar. |
| **`BayarTunai` & `BayarQris`** | Implementasi konkret dari metode pembayaran yang tersedia. |
| **`Struk`** | Bertanggung jawab untuk memproses tampilan akhir transaksi kepada pelanggan. |

---

## 📊 Alur Kerja Program

1.  **Inisialisasi Data:** Program membuat objek-objek `Menu` dan `Pelanggan`.
2.  **Pembuatan Pesanan:** Objek `Pesanan` dibuat dengan menyertakan data pelanggan dan pilihan metode pembayaran.
3.  **Input Menu:** Menu ditambahkan ke dalam daftar pesanan menggunakan metode `tambahMenu()`.
4.  **Proses Transaksi:** Sistem menghitung total harga dan memicu metode `prosesPembayaran()` sesuai tipe yang dipilih (Tunai/QRIS).
5.  **Output Struk:** Objek `Struk` mengambil data dari pesanan untuk dicetak ke layar konsol.

---

## 💻 Cara Menjalankan

1. Pastikan Java Development Kit (JDK) sudah terpasang di komputer Anda.
2. Simpan kode dalam file bernama `main.java`.
3. Kompilasi kode melalui terminal:
   ```bash
   javac main.java
