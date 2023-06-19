package com.server.prueba.backwebprices.api.adapters;

import com.baeldung.openapi.api.PricesApi;
import com.baeldung.openapi.model.PricesListRequest;
import com.baeldung.openapi.model.PricesListResource;
import com.server.prueba.backwebprices.api.mappers.PricesListMapper;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import com.server.prueba.backwebprices.ports.in.PricesListServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping
public class PricesControllerAdapter implements PricesApi {

    private final PricesListMapper pricesListMapper;

    private final PricesListServicePort pricesListServicePort;

    @Override
    public ResponseEntity<List<PricesListResource>> getPricesByFilter(@Valid PricesListRequest pricesListRequest) {
        PricesListFilter filter = pricesListMapper.getFilter(pricesListRequest);
        var response =
                pricesListServicePort.findPricesListByFilter(filter);
        return ResponseEntity.ok(pricesListMapper.toPricesListResourceList(response));
    }

}
