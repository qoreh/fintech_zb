package com.zerobase.fintech.converter;

import com.zerobase.fintech.type.ProductCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductCodeConverter implements AttributeConverter<ProductCode, String> {
    @Override
    public String convertToDatabaseColumn(ProductCode productCode) {
        return productCode.getCode();
    }

    @Override
    public ProductCode convertToEntityAttribute(String dbData) {
        return ProductCode.find(dbData);
    }
}
