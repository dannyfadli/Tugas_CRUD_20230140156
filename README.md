# KTP Management System - DeployPratikum2

Sistem manajemen data KTP berbasis web menggunakan **Spring Boot** untuk backend dan **jQuery AJAX** untuk frontend. Proyek ini mendemonstrasikan implementasi CRUD (Create, Read, Update, Delete) yang lengkap dengan validasi nomor KTP unik.

---

## Stack Teknologi
* **Backend:** Java 17, Spring Boot 3, Spring Data JPA
* **Database:** MySQL / H2 (Jakarta Persistence)
* **Library:** Lombok, Jakarta Validation
* **Frontend:** HTML5, jQuery, AJAX, CSS3

---

##Fitur Utama
- [x] **Daftar KTP:** Menampilkan seluruh data KTP dari database.
- [x] **Tambah Data:** Validasi nomor KTP tidak boleh duplikat.
- [x] **Edit Data:** Memperbarui informasi KTP yang sudah ada.
- [x] **Hapus Data:** Menghapus record KTP dengan konfirmasi.
- [x] **Global Error Handling:** Pesan error yang seragam dari server ke client.

---

## endpoints API (KTP Controller)

| Method | Endpoint | Deskripsi |
| :--- | :--- | :--- |
| **GET** | `/ktp` | Mengambil semua daftar KTP |
| **GET** | `/ktp/{id}` | Mengambil detail KTP berdasarkan ID |
| **POST** | `/ktp` | Menambah KTP baru (Validasi nomor unik) |
| **PUT** | `/ktp/{id}` | Memperbarui data KTP berdasarkan ID |
| **DELETE** | `/ktp/{id}` | Menghapus data KTP |

---

### **Tabel: ktp**
| Kolom | Tipe Data | Keterangan |
| :--- | :--- | :--- |
| **id** | Integer | Primary Key (Auto Increment) |
| **nomor_ktp** | String(16) | Unique, Not Null |
| **nama_lengkap**| String | Not Null |
| **alamat** | Text | - |
| **tanggal_lahir**| LocalDate | Not Null |
| **jenis_kelamin**| String(20) | Not Null |

---

1.  **Clone Repository:**
    ```bash
    git clone [https://github.com/username/DeployPratikum2.git](https://github.com/username/DeployPratikum2.git)
    ```
2.  **Konfigurasi Database:**
    Sesuaikan `src/main/resources/application.properties` dengan database kamu.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/db_pratikum
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  **Build & Run:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Akses UI:**
    Buka browser dan akses `http://localhost:8080`

---

## Pengujian (Unit Test)
Proyek ini dilengkapi dengan pengujian konteks dasar:
```bash
./mvnw test
