package com.springwebfluxproject.springwebfluxproject.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.savedrequest.ServerRequestCache;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MySimpleUrlAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    private URI location = URI.create("/");
    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
    private ServerRequestCache requestCache = new WebSessionServerRequestCache();

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange var1, Authentication var2)
    {
        String targetUrl = determineTargetUrl(var2);
        this.location = URI.create(targetUrl);
        ServerWebExchange exchange = var1.getExchange();
        return this.requestCache.getRedirectUri(exchange).defaultIfEmpty(this.location).flatMap((location) -> {
            return this.redirectStrategy.sendRedirect(exchange, location);
        });
    }
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response, Authentication authentication)
//            throws IOException {
//
//        handle(request, response, authentication);
////        clearAuthenticationAttributes(request);
//    }
//    protected void handle(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication
//    ) throws IOException {
//
//        String targetUrl = determineTargetUrl(authentication);
//
//
//
//        redirectStrategy.sendRedirect(request, response, targetUrl);
//    }
    protected String determineTargetUrl(final Authentication authentication) {

        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/all");
        roleTargetUrlMap.put("ROLE_ADMIN", "/getD");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }
}
