package com.projuris.webservice.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${demo.jwt.expiration}")
	private String expiration;

	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();

		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + 86400000);

		return Jwts.builder().setIssuer("Micro Service").setSubject(usuario.getId().toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, "projuris").compact();

	}

}
