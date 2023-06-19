package com.server.prueba.backwebprices.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
public class BrandMO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
