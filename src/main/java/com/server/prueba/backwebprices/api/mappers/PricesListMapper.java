package com.server.prueba.backwebprices.api.mappers;
import com.baeldung.openapi.model.PricesListRequest;
import com.baeldung.openapi.model.PricesListResource;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PricesListMapper {

    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "applicationDate", target = "applicationDate")
    PricesListFilter getFilter(PricesListRequest pricesListRequest);

    default List<PricesListResource> toPricesListResourceList(List<PricesList> pricesLists){
        return pricesLists.stream().map(this::toResource).collect(Collectors.toList());
    }

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "curr", target = "curr")
    PricesListResource toResource(PricesList priceList);

}
