package com.zerobase.fintech.repository;

import com.zerobase.fintech.domain.ProductInfo;
import com.zerobase.fintech.type.OrganizationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

    Optional<ProductInfo> findByProductNameAndOrganizationCode(String productName, OrganizationCode organizationCode);

    List<ProductInfo> findAllByOrganizationCode(OrganizationCode organizationCode);

}



