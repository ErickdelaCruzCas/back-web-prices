package com.server.prueba.backwebprices.repository.mappers;

import com.server.prueba.backwebprices.domain.Brand;
import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.repository.model.BrandMO;
import com.server.prueba.backwebprices.repository.model.PricesListMO;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-18T20:41:06+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
*/
@Component
public class PricesListMOMapperImpl implements PricesListMOMapper {

    @Override
    public PricesList fromModel(PricesListMO mo) {
        if ( mo == null ) {
            return null;
        }

        PricesList pricesList = new PricesList();

        pricesList.setBrand( brandMOToBrand( mo.getBrandMO() ) );
        pricesList.setId( mo.getId() );
        pricesList.setStartDate( mo.getStartDate() );
        pricesList.setEndDate( mo.getEndDate() );
        if ( mo.getPriceList() != null ) {
            pricesList.setPriceList( mo.getPriceList().intValue() );
        }
        if ( mo.getProductId() != null ) {
            pricesList.setProductId( mo.getProductId().intValue() );
        }
        if ( mo.getPriority() != null ) {
            pricesList.setPriority( mo.getPriority().intValue() );
        }
        pricesList.setPrice( mo.getPrice() );
        pricesList.setCurr( mo.getCurr() );

        return pricesList;
    }

    protected Brand brandMOToBrand(BrandMO brandMO) {
        if ( brandMO == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandMO.getId() );
        brand.setName( brandMO.getName() );

        return brand;
    }
}
