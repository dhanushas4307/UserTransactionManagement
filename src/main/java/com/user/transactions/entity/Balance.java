package com.user.transactions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="USER_BALANCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_BALANCE")
    @SequenceGenerator(name = "ID_BALANCE", sequenceName = "ID_BALANCE", allocationSize = 1)
    private Long id;
    private Long accountId;
    private String holderName;
    private BigDecimal amount;
    private String currency;
    @Column
    private Date updatedOn;


}
