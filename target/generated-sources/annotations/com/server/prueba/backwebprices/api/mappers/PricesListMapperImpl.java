package com.server.prueba.backwebprices.api.mappers;

import com.baeldung.openapi.model.PricesListRequest;
import com.baeldung.openapi.model.PricesListResource;
import com.server.prueba.backwebprices.domain.Brand;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.domain.PricesListFilter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-18T20:37:14+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
@Component
public class PricesListMapperImpl implements PricesListMapper {

    @Override
    public PricesListFilter getFilter(PricesListRequest pricesListRequest) {
        if ( pricesListRequest == null ) {
            return null;
        }

        PricesListFilter pricesListFilter = new PricesListFilter();

        pricesListFilter.setBrandId( pricesListRequest.getBrandId() );
        pricesListFilter.setProductId( pricesListRequest.getProductId() );
        pricesListFilter.setApplicationDate( pricesListRequest.getApplicationDate() );

        return pricesListFilter;
    }

    @Override
    public PricesListResource toResource(PricesList priceList) {
        if ( priceList == null ) {
            return null;
        }

        PricesListResource pricesListResource = new PricesListResource();

        pricesListResource.setBrandId( priceListBrandId( priceList ) );
        pricesListResource.setStartDate( priceList.getStartDate() );
        pricesListResource.setEndDate( priceList.getEndDate() );
        if ( priceList.getPriceList() != null ) {
            pricesListResource.setPriceList( priceList.getPriceList().longValue() );
        }
        if ( priceList.getProductId() != null ) {
            pricesListResource.setProductId( priceList.getProductId().longValue() );
        }
        if ( priceList.getPriority() != null ) {
            pricesListResource.setPriority( priceList.getPriority().longValue() );
        }
        pricesListResource.setPrice( priceList.getPrice() );
        pricesListResource.setCurr( priceList.getCurr() );

        return pricesListResource;
    }

    private Long priceListBrandId(PricesList pricesList) {
        if ( pricesList == null ) {
            return null;
        }
        Brand brand = pricesList.getBrand();
        if ( brand == null ) {
            return null;
        }
        Long id = brand.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
