package br.com.vipmania.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Product;

@Controller
public class ImageController {

	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping("/image/{productId}")
	@ResponseBody
	public byte[] getImage(@PathVariable("productId") Long productId) throws IOException {
		Product product = productDAO.get(productId);

		File file = null;
		
		try {
			file = new File(product.getPhotoPath());
			
			return Files.readAllBytes(file.toPath());
		}
		catch(Exception e) {
			file = new File("C:\\Users\\lucas\\Pictures\\VipMania\\photos\\produto-sem-imagem.png");
			
			return Files.readAllBytes(file.toPath());
		}
	}
	
}