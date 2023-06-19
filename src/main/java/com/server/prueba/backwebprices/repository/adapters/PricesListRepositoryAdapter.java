package com.server.prueba.backwebprices.repository.adapters;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.ports.out.PricesListRepositoryPort;
import com.server.prueba.backwebprices.repository.PricesListMOJpaRepository;
import com.server.prueba.backwebprices.repository.mappers.PricesListMOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PricesListRepositoryAdapter implements PricesListRepositoryPort {

        private final PricesListMOJpaRepository repository;

        private final PricesListMOMapper mapper;

        @Override
        public List<PricesList> getPricesListFromFilter(PricesListFilter pricesListFilter) {
                return repository.findPricesListByFilter(
                        pricesListFilter.getBrandId(),
                        pricesListFilter.getProductId(),
                        pricesListFilter.getApplicationDate()
                        ).stream()
                .map(mapper::fromModel)
                .collect(Collectors.toList());
        }

}

