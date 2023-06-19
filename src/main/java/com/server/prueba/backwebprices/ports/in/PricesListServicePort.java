package com.server.prueba.backwebprices.ports.in;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;

import java.util.List;

public interface PricesListServicePort {
    List<PricesList> findPricesListByFilter(PricesListFilter filter);
}
