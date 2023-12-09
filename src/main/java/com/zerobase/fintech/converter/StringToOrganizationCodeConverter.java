package com.zerobase.fintech.converter;

import com.zerobase.fintech.type.OrganizationCode;
import org.springframework.core.convert.converter.Converter;

public class StringToOrganizationCodeConverter implements Converter<String, OrganizationCode> {

    @Override
    public OrganizationCode convert(String source) {
        return OrganizationCode.find(source);
    }
}
