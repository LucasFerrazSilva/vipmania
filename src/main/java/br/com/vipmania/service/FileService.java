package br.com.vipmania.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileService {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			
			file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
			
			return baseFolder + "/" + file.getOriginalFilename();
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}