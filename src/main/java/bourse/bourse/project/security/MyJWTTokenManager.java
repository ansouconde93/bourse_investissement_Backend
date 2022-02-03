package bourse.bourse.project.security;

import bourse.bourse.project.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyJWTTokenManager {
    // créer le token associer au autorités(droit d'accès) de l'utilisateur
    public String genereteToken(User user){
        //acces token definition
        Set<String> autorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            role.getAutorities().forEach(autority -> {
                autorities.add(autority.getNom());
            });
        });
        return Jwts.builder()
                .setSubject(user.getEmail())//on peut mettre tout ce qu'on veut
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstante.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
                .claim("autorities",autorities)
                .compact();
    }
    public UsernamePasswordAuthenticationToken decodeToken(String token) {
        try{
            Claims claims= Jwts.parser()//recuperer les revendications
                    .setSigningKey(SecurityConstante.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            String useremail = claims.getSubject();//lecture du username de l'utilisateur qui a envoyer la requet
            // recuperer les autorités de l'utilisations
            List<String> roles=(List<String>) claims.get("autorities", List.class);
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            roles.forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r));
            });
            //formuler l'utilisateur authentifié
            UsernamePasswordAuthenticationToken utilisateurAuthentifier=
                    new UsernamePasswordAuthenticationToken(useremail, null,authorities);
            return utilisateurAuthentifier;
        }catch (JwtException exception){
            return null;
        }

    }
}
