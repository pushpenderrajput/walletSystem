package com.pushpender.wallet.service;

import com.pushpender.wallet.dto.*;
import com.pushpender.wallet.entity.Wallet;
import com.pushpender.wallet.exception.*;
import com.pushpender.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    @Transactional
    public WalletResponse operate(WalletOperationRequest request) {

        for (int i = 0; i < 5; i++) {

            try {

                Wallet wallet = repository.findById(request.walletId())
                        .orElseThrow(WalletNotFoundException::new);

                switch (request.operationType()) {
                    case DEPOSIT ->
                            wallet.setBalance(wallet.getBalance().add(request.amount()));

                    case WITHDRAW -> {
                        if (wallet.getBalance().compareTo(request.amount()) < 0) {
                            throw new InsufficientFundsException();
                        }
                        wallet.setBalance(wallet.getBalance().subtract(request.amount()));
                    }
                }

                return new WalletResponse(wallet.getId(), wallet.getBalance());

            } catch (ObjectOptimisticLockingFailureException e) {
            }
        }

        throw new RuntimeException("Too many concurrent updates");
    }

    @Transactional(readOnly = true)
    public WalletResponse getBalance(UUID id) {

        Wallet wallet = repository.findById(id)
                .orElseThrow(WalletNotFoundException::new);

        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }

    @Transactional
    public WalletResponse createWallet(CreateWalletRequest request) {

        Wallet wallet = new Wallet();
        wallet.setId(UUID.randomUUID());

        if (request.initialBalance() == null) {
            wallet.setBalance(BigDecimal.ZERO);
        } else {
            wallet.setBalance(request.initialBalance());
        }

        repository.save(wallet);

        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }

}
