package com.zerobase.fintech.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_INFO")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationCode;
    private String productCode;
    private String productName;
    private Double productMaximumInterest;
    private Double productMinimumInterest;


}
