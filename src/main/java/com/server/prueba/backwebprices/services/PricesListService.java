package com.server.prueba.backwebprices.services;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.ports.in.PricesListServicePort;
import com.server.prueba.backwebprices.ports.out.PricesListRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PricesListService implements PricesListServicePort {

    private final PricesListRepositoryPort pricesListRepositoryPort;
    @Override
    public List<PricesList> findPricesListByFilter(PricesListFilter filter) {
        return pricesListRepositoryPort.getPricesListFromFilter(filter);
    }
}
