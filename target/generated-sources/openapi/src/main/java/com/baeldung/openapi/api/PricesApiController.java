package com.baeldung.openapi.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-18T20:37:11.047804100+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.backPrices.base-path:}")
public class PricesApiController implements PricesApi {

    private final PricesApiDelegate delegate;

    public PricesApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) PricesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new PricesApiDelegate() {});
    }

    @Override
    public PricesApiDelegate getDelegate() {
        return delegate;
    }

}
