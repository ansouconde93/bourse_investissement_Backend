package bourse.bourse.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity

public class SecurityConfig{

    @Autowired
    private ServerSecurityContextRepository securityContextRepository;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService noOps(){
        return s-> Mono.empty();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                        .pathMatchers(HttpMethod.POST,"/login/**").permitAll()
                        .pathMatchers(HttpMethod.POST,"/role/save").hasAuthority("create")
                        .pathMatchers(HttpMethod.POST,"/user/save").hasAuthority("create")
                        .pathMatchers(HttpMethod.POST,"/transaction/save").hasAuthority("create")
                        .pathMatchers(HttpMethod.POST,"/autority/save").hasAuthority("create")
                        .pathMatchers(HttpMethod.POST,"/societe/save").hasAuthority("create")
                        .pathMatchers(HttpMethod.POST,"/transaction/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/transactions/societe/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/autority/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/role/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/societe/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/user/delete/**").hasAuthority("delete")
                        .pathMatchers(HttpMethod.POST,"/transaction/update/**").hasAuthority("update")
                        .pathMatchers(HttpMethod.POST,"/autority/update/**").hasAuthority("update")
                        .pathMatchers(HttpMethod.POST,"/role/update/**").hasAuthority("update")
                        .pathMatchers(HttpMethod.POST,"/societe/update/**").hasAuthority("update")
                        .pathMatchers(HttpMethod.POST,"/user/update/**").hasAuthority("update")
                        .pathMatchers(HttpMethod.POST,"/autority/susppendre/**").hasAuthority("manager")
                        .pathMatchers(HttpMethod.POST,"/role/susppendre/**").hasAuthority("manager")
                        .pathMatchers(HttpMethod.POST,"/user/susppendre/**").hasAuthority("manager")
                        .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()//desactiver crossorigin
                .securityContextRepository(securityContextRepository)
                .build();
    }
}
