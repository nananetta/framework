package com.konkow.framework.service;

import com.konkow.framework.domain.ClassicError;
import com.konkow.framework.exception.BusinessException;

public interface IErrorService {

    ClassicError getError(String errorCode);

    BusinessException createBusinessException(String errorCode);
}
