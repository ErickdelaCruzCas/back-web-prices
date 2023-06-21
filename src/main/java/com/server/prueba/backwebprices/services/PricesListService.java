package com.server.prueba.backwebprices.services;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.ports.in.PricesListServicePort;
import com.server.prueba.backwebprices.ports.out.PricesListRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class PricesListService implements PricesListServicePort {

    private final PricesListRepositoryPort pricesListRepositoryPort;


    @Override
    public PricesList findPricesListById(Integer id) {
        return pricesListRepositoryPort.findPricesListById(id);
    }

    @Override
    public List<PricesList> findPricesListByFilter(PricesListFilter filter) {
        var pricesListFiltered = pricesListRepositoryPort.getPricesListFromFilter(filter);
     pricesListFiltered = checkIfProcutHasMultiplePricesList(filter, pricesListFiltered);
        return pricesListFiltered;
    }

    private List<PricesList> checkIfProcutHasMultiplePricesList(PricesListFilter filter, List<PricesList> pricesListFiltered) {
        if(Objects.nonNull(filter.getApplicationDate()) && Objects.nonNull(filter.getProductId()) && pricesListFiltered.size() > 1) {
            pricesListFiltered = getPricesListMaxPriority(pricesListFiltered);
        }
        return pricesListFiltered;
    }

    private List<PricesList> getPricesListMaxPriority(List<PricesList> pricesListFromFilter) {
        pricesListFromFilter =
                Collections.singletonList(
                        pricesListFromFilter.stream()
                                .max(Comparator.comparingLong(PricesList::getPriority)).get());
        return pricesListFromFilter;
    }

}
