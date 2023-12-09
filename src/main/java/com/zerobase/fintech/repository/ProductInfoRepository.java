package com.zerobase.fintech.repository;

import com.zerobase.fintech.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

    Optional<ProductInfo> findByProductNameAndOrganizationCode(String productName, String organizationCode);
    List<ProductInfo> findAllByOrganizationCodeOrderByProductCode(String organizationCode);

}
