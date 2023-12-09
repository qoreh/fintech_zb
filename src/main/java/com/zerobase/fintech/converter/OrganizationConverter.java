package com.zerobase.fintech.converter;

import com.zerobase.fintech.type.OrganizationCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrganizationConverter implements AttributeConverter<OrganizationCode, String> {
    @Override
    public String convertToDatabaseColumn(OrganizationCode organizationCode) {
        return organizationCode.getCode();
    }

    @Override
    public OrganizationCode convertToEntityAttribute(String dbData) {
        return OrganizationCode.find(dbData);
    }
}
