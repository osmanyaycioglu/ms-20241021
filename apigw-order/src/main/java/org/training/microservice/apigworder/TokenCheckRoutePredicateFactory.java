package org.training.microservice.apigworder;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TokenCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenCheckRoutePredicateFactory.TokenCheckConfig> {

    @Data
    public static class TokenCheckConfig {
        boolean checkValid = false;
        String  jwtSubject;
    }

    public TokenCheckRoutePredicateFactory() {
        super(TokenCheckConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(final TokenCheckConfig config) {
        return swe -> {
            List<String> stringsLoc = swe.getRequest()
                                         .getHeaders()
                                         .get("Authorization");
            String token = "";
            if (stringsLoc != null && !stringsLoc.isEmpty()) {
                token = stringsLoc.get(0);
            }
            System.out.println("TokenCheckPredicateFactory running config : " + config + " Token : " + token);
            return true;
        };
    }
}
