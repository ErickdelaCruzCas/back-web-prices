package com.server.prueba.backwebprices.api.adapters;

import com.baeldung.openapi.model.PricesListRequest;
import com.baeldung.openapi.model.PricesListResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.prueba.backwebprices.api.mappers.PricesListMapper;
import com.server.prueba.backwebprices.domain.Brand;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.exceptions.ResourceNotFoundException;
import com.server.prueba.backwebprices.ports.in.PricesListServicePort;
import com.server.prueba.backwebprices.repository.adapters.PricesListRepositoryAdapter;
import com.server.prueba.backwebprices.repository.model.BrandMO;
import com.server.prueba.backwebprices.repository.model.PricesListMO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {PricesControllerAdapter.class})
public class PricesControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesListMapper pricesListMapper;

    @MockBean
    private PricesListServicePort pricesListServicePort;

    @Autowired
    private ObjectMapper objectMapper;

    private Brand brand;
    private PricesList pricesList;
    private PricesListResource pricesListResource;

    @BeforeEach
    public void before() {

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
        pricesListResource = PricesListResource.builder()
                .brandId(1L)
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
    void getPricesByFilter() {
        var filterRequest = PricesListRequest.builder().brandId(1L).productId(1L).applicationDate(OffsetDateTime.now()).build();
        var filter = PricesListFilter.builder().brandId(1L).productId(1L).applicationDate(OffsetDateTime.now()).build();
        when(pricesListMapper.getFilter(filterRequest)).thenReturn(filter);
        List<PricesList> singletonListPriceList = Collections.singletonList(pricesList);
        when(pricesListServicePort.findPricesListByFilter(filter)).thenReturn(singletonListPriceList);
        List<PricesListResource> singletonListPriceListResource = Collections.singletonList(pricesListResource);
        when(pricesListMapper.toPricesListResourceList(singletonListPriceList)).thenReturn(singletonListPriceListResource);

        mockMvc.perform(post("/prices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filterRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getPriceById() {
        var id = 1;
        when(pricesListServicePort.findPricesListById(id)).thenReturn(pricesList);
        when(pricesListMapper.toResource(pricesList)).thenReturn(pricesListResource);
        mockMvc.perform(get("/prices/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(pricesListServicePort, times(1)).findPricesListById(id);
    }

}
