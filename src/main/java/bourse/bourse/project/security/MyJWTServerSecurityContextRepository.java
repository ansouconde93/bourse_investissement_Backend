package bourse.bourse.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component

public class MyJWTServerSecurityContextRepository implements ServerSecurityContextRepository {
    @Autowired
    private MyJWTTokenManager myJWTTokenManager;
    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.empty();
    }
    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String autorization = request.getHeaders().getFirst(SecurityConstante.HEADER_STRING);
        if(autorization != null && autorization.startsWith(SecurityConstante.TOKEN_PREFIX)){
            String token = autorization.replace(SecurityConstante.TOKEN_PREFIX,"");
            final UsernamePasswordAuthenticationToken authentication;
            try {
                authentication = myJWTTokenManager.decodeToken(token);
                return Mono.just(new SecurityContextImpl(authentication));
            } catch (Exception exception) {
                return Mono.empty();
            }
        }
        return Mono.empty();
    }
}
