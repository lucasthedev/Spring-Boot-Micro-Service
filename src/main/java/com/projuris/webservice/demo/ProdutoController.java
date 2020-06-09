package com.projuris.webservice.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoReposiory;

	@RequestMapping("/listar")
	@ResponseBody
	public List<Produto> listarProdutos() {

		List<Produto> produtos = produtoReposiory.findAll();

		return produtos;
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
		
		Optional<Produto> produto = this.produtoReposiory.findById(id);
		
		if(produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrar")
	@ResponseBody
	public Produto cadastrarProduto(@Validated @RequestBody Produto produto) {		
		
		return this.produtoReposiory.save(produto);
	}

}
