package com.projuris.webservice.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProdutoController {

	@RequestMapping("/listar")
	@ResponseBody
	public List<Produto> listarProdutos() {

		Produto produto = new Produto(1, "Ventilador");

		return Arrays.asList(produto, produto, produto);
	}

}
