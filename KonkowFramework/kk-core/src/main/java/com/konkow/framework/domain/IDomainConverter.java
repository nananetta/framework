package com.konkow.framework.domain;

public interface IDomainConverter<D, M> {
	
    M toModel(D domain);

    D toDomain(M model);
}
