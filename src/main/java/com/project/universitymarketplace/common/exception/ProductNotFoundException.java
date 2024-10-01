package com.project.universitymarketplace.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException(Long id) {
        super(ExceptionEnum.PRODUCT_NOT_FOUND, id);
    }
}
