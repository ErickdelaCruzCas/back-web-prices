package com.server.prueba.backwebprices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricesListFilter {

    private Long brandId;
    private Long productId;
    private OffsetDateTime applicationDate;

}
