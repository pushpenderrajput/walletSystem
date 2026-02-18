package com.pushpender.wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletOperationRequest(

        @NotNull UUID walletId,
        @NotNull OperationType operationType,
        @NotNull @Positive BigDecimal amount
) {}
