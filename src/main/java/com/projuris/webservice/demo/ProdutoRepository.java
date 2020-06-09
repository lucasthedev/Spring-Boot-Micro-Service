package com.projuris.webservice.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public Optional<Produto> findByNome(String nome);
}
