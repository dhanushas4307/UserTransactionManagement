package com.user.transactions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT_DETAILS")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ACCOUNT_DETAILS")
    @SequenceGenerator(name = "ID_ACCOUNT_DETAILS", sequenceName = "ID_ACCOUNT_DETAILS", allocationSize = 1)
    private Long id;
    private Long accountId;
    private String name;
    private String bankName;
    private String accountType;
    private String currencyType;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name ="BALANCE_ID")
    private Balance balance;

    public Account(Long accountId, String name, String bankName, String accountType, String currencyType){
        this.accountId = accountId;
        this.name = name;
        this.bankName = bankName;
        this.accountType = accountType;
        this.currencyType = currencyType;
    }

}
