package com.baeldung.openapi.api;

import com.baeldung.openapi.model.Error;
import com.baeldung.openapi.model.PricesListRequest;
import com.baeldung.openapi.model.PricesListResource;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link PricesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-18T20:37:11.047804100+02:00[Europe/Paris]")
public interface PricesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /prices : List all prices by filter
     *
     * @param pricesListRequest  (optional)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     * @see PricesApi#getPricesByFilter
     */
    default ResponseEntity<List<PricesListResource>> getPricesByFilter(PricesListRequest pricesListRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productId\" : 1, \"endDate\" : \"2000-01-23T04:56:07.000+00:00\", \"price\" : 5.637376656633329, \"brandId\" : 0, \"priority\" : 5, \"curr\" : \"curr\", \"startDate\" : \"2000-01-23T04:56:07.000+00:00\", \"priceList\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
