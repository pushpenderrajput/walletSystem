package com.pushpender.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet")
@Data
public class Wallet {

    @Id
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Version
    private Long version;
}
