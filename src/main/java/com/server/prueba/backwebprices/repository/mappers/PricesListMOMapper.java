package com.server.prueba.backwebprices.repository.mappers;

import com.server.prueba.backwebprices.domain.PricesList;
import com.server.prueba.backwebprices.repository.model.PricesListMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesListMOMapper {


    @Mapping(source = "brandMO", target = "brand")
    PricesList fromModel(PricesListMO mo);

}
