package com.server.prueba.backwebprices.repository;

import com.server.prueba.backwebprices.repository.model.PricesListMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PricesListMOJpaRepository extends JpaRepository<PricesListMO, Integer> {

    @Query(
      value =
       "SELECT Distinct priceList FROM PricesListMO priceList " +
       "WHERE " +
       "(:brandId IS NULL OR priceList.brandMO.id = :brandId) " +
       "AND (:productId IS NULL OR priceList.productId = :productId) " +
       "AND (:applicationDate IS NULL OR :applicationDate BETWEEN priceList.startDate AND priceList.endDate) "
    )
    List<PricesListMO> findPricesListByFilter(Long brandId, Long productId, OffsetDateTime applicationDate);
}
