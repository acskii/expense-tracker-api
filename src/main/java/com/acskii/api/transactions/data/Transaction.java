package com.acskii.api.transactions.data;

import com.acskii.api.transactions.data.enums.PaymentMethod;
import com.acskii.api.transactions.data.enums.TransactionType;
import com.acskii.api.users.data.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String description;

    @Column(
            nullable = false
    )
    private String location;

    @Column(
            name = "created_on",
            nullable = false,
            updatable = false
    )
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(
            name = "updated_at",
            nullable = false
    )
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false, precision = 12, scale = 3)
    @Digits(integer = 12, fraction = 3, message = "'amount' must be exceed 999999999.999")
    @DecimalMin(value = "0.000", inclusive = false, message = "'amount' must be positive and above 0")
    private BigDecimal amount;

    @Column(name = "profit", nullable = false)
    private boolean profit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @Column(nullable = false)
//    private TransactionCurrency currency;

    /* Handled by @Converter */
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private PaymentMethod method;

    /* Determined at Runtime */
    @PrePersist
    @PreUpdate
    protected void determineProfit() {
        this.profit = this.type == null || this.type.isIncome();
    }

    /* NoArgsConstructor */
    public Transaction() {}

    /* Getters & Setters */
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public LocalDateTime getCreatedOn() {return createdOn;}
    public void setCreatedOn(LocalDateTime createdOn) {this.createdOn = createdOn;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public TransactionType getType() {return type;}
    public void setType(TransactionType type) {this.type = type;}
    public boolean isProfit() {return profit;}
    public void setProfit(boolean profit) {this.profit = profit;}
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public PaymentMethod getMethod() {return method;}
    public void setMethod(PaymentMethod method) {this.method = method;}
//    public TransactionCurrency getCurrency() {return currency;}
//    public void setCurrency(TransactionCurrency currency) {this.currency = currency;}
}
