package com.example.DeployPratikum2.service;

import com.example.DeployPratikum2.entity.Ktp;
import com.example.DeployPratikum2.repository.KtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtpService {

    private final KtpRepository ktpRepository;

    public List<Ktp> getAllKtp() {
        return ktpRepository.findAll();
    }

    public Ktp getKtpById(Integer id) {
        return ktpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KTP not found with id: " + id));
    }

    @Transactional
    public Ktp createKtp(Ktp ktp) {
        if (ktpRepository.existsByNomorKtp(ktp.getNomorKtp())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "KTP with nomor " + ktp.getNomorKtp() + " already exists.");
        }
        return ktpRepository.save(ktp);
    }

    @Transactional
    public Ktp updateKtp(Integer id, Ktp updatedKtp) {
        Ktp existingKtp = getKtpById(id);

        if (ktpRepository.existsByNomorKtpAndIdNot(updatedKtp.getNomorKtp(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "KTP with nomor " + updatedKtp.getNomorKtp() + " already exists for another record.");
        }

        existingKtp.setNomorKtp(updatedKtp.getNomorKtp());
        existingKtp.setNamaLengkap(updatedKtp.getNamaLengkap());
        existingKtp.setAlamat(updatedKtp.getAlamat());
        existingKtp.setTanggalLahir(updatedKtp.getTanggalLahir());
        existingKtp.setJenisKelamin(updatedKtp.getJenisKelamin());

        return ktpRepository.save(existingKtp);
    }

    @Transactional
    public void deleteKtp(Integer id) {
        Ktp existingKtp = getKtpById(id);
        ktpRepository.delete(existingKtp);
    }
}
