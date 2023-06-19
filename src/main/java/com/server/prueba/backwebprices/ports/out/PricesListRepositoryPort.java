package com.server.prueba.backwebprices.ports.out;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;

import java.util.List;

public interface PricesListRepositoryPort{

    List<PricesList> getPricesListFromFilter(PricesListFilter pricesListFilter);

}
