package br.com.vipmania.service;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileService {

	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = "C:\\Users\\lucas\\Pictures\\VipMania\\" + baseFolder;
			
			File dir = new File(realPath);
			
			if(!dir.exists())
				dir.mkdirs();
			
			file.transferTo(new File(realPath + "\\" + file.getOriginalFilename()));
			
//			return baseFolder + "/" + file.getOriginalFilename();
			return realPath + "\\" + file.getOriginalFilename();
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}