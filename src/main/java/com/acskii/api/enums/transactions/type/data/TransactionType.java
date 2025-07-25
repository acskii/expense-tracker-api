package com.acskii.api.enums.transactions.type.data;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_types")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "name",
            unique = true,
            length = 50
    )
    private String type;

    @Column(
            name = "is_income",
            nullable = false
    )
    private boolean income;

    /* NoArgs Constructor */
    public TransactionType() {}

    /* Getters & Setters */
    public Integer getId() {return id;}
    public String getType() {return type;}
    public void setId(Integer id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public boolean isIncome() {return income;}
    public void setIncome(boolean income) {this.income = income;}
}
