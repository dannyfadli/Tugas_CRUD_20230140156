package com.example.DeployPratikum2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ktp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomor_ktp", unique = true, nullable = false, length = 16)
    private String nomorKtp;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "jenis_kelamin", nullable = false, length = 20)
    private String jenisKelamin;
}
