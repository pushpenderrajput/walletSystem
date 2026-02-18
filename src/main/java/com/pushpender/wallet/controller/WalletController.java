package com.pushpender.wallet.controller;
import com.pushpender.wallet.dto.*;
import com.pushpender.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService service;

    @PostMapping("/wallet")
    public ResponseEntity<WalletResponse> operate(
            @RequestBody WalletOperationRequest request) {

        return ResponseEntity.ok(service.operate(request));
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<WalletResponse> getBalance(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.getBalance(id));
    }

    @PostMapping("/wallets")
    public ResponseEntity<WalletResponse> createWallet(
            @Valid @RequestBody CreateWalletRequest request) {

        return ResponseEntity.ok(service.createWallet(request));
    }

}
