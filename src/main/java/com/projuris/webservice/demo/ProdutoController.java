package com.projuris.webservice.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
