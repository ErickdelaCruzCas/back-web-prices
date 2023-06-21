package com.server.prueba.backwebprices.repository.adapters;

import com.server.prueba.backwebprices.domain.Brand;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.exceptions.ResourceNotFoundException;
import com.server.prueba.backwebprices.ports.out.PricesListRepositoryPort;
import com.server.prueba.backwebprices.repository.PricesListMOJpaRepository;
import com.server.prueba.backwebprices.repository.mappers.PricesListMOMapper;
import com.server.prueba.backwebprices.repository.model.BrandMO;
import com.server.prueba.backwebprices.repository.model.PricesListMO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PricesListRepositoryAdapterTest {

    @Mock
    private PricesListMOJpaRepository repository;

    @Mock
    private PricesListMOMapper mapper;

    private PricesListRepositoryPort service;
    private Brand brand;
    private BrandMO brandMO;
    private PricesListMO pricesListMO;
    private PricesList pricesList;

    @BeforeEach
    public void before() {
        service = new PricesListRepositoryAdapter(repository, mapper);
        brandMO = BrandMO.builder()
                .id(1L)
                .name("Zara")
                .build();

        brand = Brand.builder()
                .id(1L)
                .name("Zara")
                .build();
        pricesList = PricesList.builder()
                .id(1)
                .brand(brand)
                .startDate(OffsetDateTime.now().minusDays(1))
                .endDate(OffsetDateTime.now().plusDays(1))
                .priceList(1L)
                .productId(1L)
                .priority(0L)
                .price(11.11)
                .curr("EUR").build();
        pricesListMO = PricesListMO.builder()
                .id(1)
                .brandMO(brandMO)
                .startDate(OffsetDateTime.now().minusDays(1))
                .endDate(OffsetDateTime.now().plusDays(1))
                .priceList(1L)
                .productId(1L)
                .priority(0L)
                .price(11.11)
                .curr("EUR").build();
    }

    @Test
    void getPricesListByFullFilterTest() {
        var filter =
                PricesListFilter.builder()
                        .brandId(1L)
                        .productId(1L)
                        .applicationDate(OffsetDateTime.now()).build();
        var list = new ArrayList<PricesListMO>();
        list.add(pricesListMO);
        given(repository.findPricesListByFilter(
                filter.getBrandId(),
                filter.getProductId(),
                filter.getApplicationDate()))
                .willReturn(list);
        given(mapper.fromModel(pricesListMO)).willReturn(pricesList);


        var response = service.getPricesListFromFilter(filter);
        assertNotNull(response);
        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getPrice(), 11.11);

    }


    @Test
    void findPricesListByIdTest() {
        var id = 1;
        given(repository.findById(id)).willReturn(Optional.of(pricesListMO));
        given(mapper.fromModel(pricesListMO)).willReturn(pricesList);
        var response = service.findPricesListById(id);
        assertNotNull(response);
        assertEquals(response.getId(), id);
    }

    @Test
    @SneakyThrows
    void findPricesListByIdWithExceptionTest() {
        var id = 1;
        given(repository.findById(id)).willReturn(Optional.empty());
        Executable response = () -> service.findPricesListById(id);
        assertThrows(ResourceNotFoundException.class, response);

    }
}
