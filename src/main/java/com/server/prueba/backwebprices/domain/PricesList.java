package com.server.prueba.backwebprices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricesList {

    private Integer id;
    private Brand brand;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Double price;
    private String curr;

}
