package com.pushpender.wallet.dto;

import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CreateWalletRequest(
        @PositiveOrZero BigDecimal initialBalance
) {}
