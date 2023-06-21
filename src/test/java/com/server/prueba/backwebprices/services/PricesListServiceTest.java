package com.server.prueba.backwebprices.services;

import com.server.prueba.backwebprices.domain.Brand;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.ports.in.PricesListServicePort;
import com.server.prueba.backwebprices.ports.out.PricesListRepositoryPort;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PricesListServiceTest {

    @Mock
    private PricesListRepositoryPort pricesListRepositoryPort;

    private PricesListServicePort service;
    private Brand brand;
    private PricesList firstPricesList;

    @BeforeEach
    public void before() {
        service = new PricesListService(pricesListRepositoryPort);
        brand = Brand.builder()
                .id(1L)
                .name("Zara")
                .build();
        firstPricesList = PricesList.builder()
                .id(1)
                .brand(brand)
                .startDate(OffsetDateTime.now().minusDays(1))
                .endDate(OffsetDateTime.now().plusDays(1))
                .priceList(1L)
                .productId(1L)
                .priority(0L)
                .price(11.11)
                .curr("EUR").build();
    }

    @Test
    @SneakyThrows
    void findPricesListByFullFilter() {
        var filter = PricesListFilter.builder().brandId(1L).productId(1L).applicationDate(OffsetDateTime.now()).build();
        var list = new ArrayList<PricesList>();


        var secondPriceList =
                PricesList.builder()
                        .id(2)
                        .brand(brand)
                        .startDate(OffsetDateTime.now().minusDays(2))
                        .endDate(OffsetDateTime.now().plusDays(2))
                        .priceList(1L)
                        .productId(1L)
                        .priority(1L)
                        .price(22.22)
                        .curr("EUR").build();
        list.add(firstPricesList);
        list.add(secondPriceList);
        given(pricesListRepositoryPort.getPricesListFromFilter(filter)).willReturn(list);

        var response = service.findPricesListByFilter(filter);
        assertNotNull(response);
        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getPrice(), 22.22);
    }

    @Test
    void findPricesListByIdTest() {
        var id = 1;
        given(pricesListRepositoryPort.findPricesListById(id)).willReturn(firstPricesList);
        var response = service.findPricesListById(id);
        assertNotNull(response);
        assertEquals(response.getId(), id);
        assertEquals(response.getPrice(), 11.11);
    }

}
