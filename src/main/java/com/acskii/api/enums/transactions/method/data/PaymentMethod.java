package com.acskii.api.enums.transactions.method.data;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String method;

    /* NoArgs Constructor */
    public PaymentMethod() {}

    /* Getters & Setters */
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getMethod() {return method;}
    public void setMethod(String method) {this.method = method;}
}
