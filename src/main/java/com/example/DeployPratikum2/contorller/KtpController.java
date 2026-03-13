package com.example.DeployPratikum2.contorller;


import com.example.DeployPratikum2.entity.Ktp;
import com.example.DeployPratikum2.service.KtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ktp")
@RequiredArgsConstructor
public class KtpController {

    private final KtpService ktpService;

    @GetMapping
    public ResponseEntity<List<Ktp>> getAllKtp() {
        return ResponseEntity.ok(ktpService.getAllKtp());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ktp> getKtpById(@PathVariable Integer id) {
        return ResponseEntity.ok(ktpService.getKtpById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createKtp(@RequestBody Ktp ktp) {
        try {
            Ktp createdKtp = ktpService.createKtp(ktp);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdKtp);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKtp(@PathVariable Integer id, @RequestBody Ktp ktp) {
        try {
            Ktp updatedKtp = ktpService.updateKtp(id, ktp);
            return ResponseEntity.ok(updatedKtp);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteKtp(@PathVariable Integer id) {
        try {
            ktpService.deleteKtp(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "KTP deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
