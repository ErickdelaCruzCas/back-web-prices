package com.server.prueba.backwebprices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricesListFilter {

    private Long brandId;
    private Long productId;
    private OffsetDateTime applicationDate;

}
