package com.inventory_management.Inventory.Management.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PurchaseOrderVerificationToken {


    private static final int EXPIRATION_TIME = 2880;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String token;
    private String reject;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TOKEN"))
    private PurchaseOrder purchaseOrder;

    public PurchaseOrderVerificationToken(PurchaseOrder purchaseOrder, String token, String reject) {
        super();
        this.purchaseOrder = purchaseOrder;
        this.token = token;
        this.reject = reject;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    public PurchaseOrderVerificationToken(String token, String reject) {
        super();
        this.token = token;
        this.reject = reject;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());

    }
}
