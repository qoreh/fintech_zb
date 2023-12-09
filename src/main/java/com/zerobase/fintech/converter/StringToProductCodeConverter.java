package com.zerobase.fintech.converter;

import com.zerobase.fintech.type.ProductCode;
import org.springframework.core.convert.converter.Converter;

public class StringToProductCodeConverter implements Converter<String, ProductCode> {

    @Override
    public ProductCode convert(String source) {
        return ProductCode.find(source);
    }
}
