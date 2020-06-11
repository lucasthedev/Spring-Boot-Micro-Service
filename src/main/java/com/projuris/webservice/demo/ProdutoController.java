package com.projuris.webservice.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@CrossOrigin
@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoReposiory;

	@GetMapping("/listar")
	@Cacheable(value = "listaProdutos")
	public List<Produto> listarProdutos() {

		List<Produto> produtos = produtoReposiory.findAll();

		return produtos;
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {

		Optional<Produto> produto = this.produtoReposiory.findById(id);

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("buscarpornome")
	public ResponseEntity<Produto> buscarPorNome(String nome) {
		if (nome == null) {
			return ResponseEntity.notFound().build();
		} else {
			Optional<Produto> produto = this.produtoReposiory.findByNome(nome);

			if (produto.isPresent()) {
				return ResponseEntity.ok(produto.get());
			}

			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/cadastrar")	
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<Produto> cadastrarProduto(@Validated @RequestBody Produto produto, UriComponentsBuilder uriBuilder) {

		Produto novoProduto = this.produtoReposiory.save(produto);
		if(novoProduto != null) {
			URI uri = uriBuilder.path("/cadastrar/{id}").buildAndExpand(novoProduto.getId()).toUri();
			return ResponseEntity.created(uri).body(novoProduto);
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/alterar")
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<Produto> alterarProduto(@Validated @RequestBody Produto produto) {

		Optional<Produto> produtosExistentes = this.produtoReposiory.findById(produto.getId());

		if (produtosExistentes.isPresent()) {
			this.produtoReposiory.save(produto);
			return ResponseEntity.ok(produto);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("exlcuir/{id}")
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<Produto> excluirProduto(@PathVariable Long id) {

		Optional<Produto> produto = this.produtoReposiory.findById(id);

		if (produto.isPresent()) {
			this.produtoReposiory.deleteById(id);
			return ResponseEntity.ok(produto.get());
		}

		return ResponseEntity.notFound().build();
	}

}
