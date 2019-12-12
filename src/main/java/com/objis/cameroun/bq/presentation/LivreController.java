package com.objis.cameroun.bq.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.objis.cameroun.bq.domaine.dto.LivreDTO;
import com.objis.cameroun.bq.domaine.entity.Livre;
import com.objis.cameroun.bq.service.IService;

@Controller
public class LivreController {
	
	@Autowired
	private IService service;
	
	@Value("${dir.image}")
	private String imageDir;
	
	@GetMapping("/index")
	public String catalogue(Model model,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="motCle", defaultValue="") String mc){
		Page<Livre> pageLivres=
				service.findPageLivreService(mc,PageRequest.of(page, 5));
		//Récupération de la liste des agriculteurs
		final List<Livre> livres = service.findListeLivreService(mc);
		// Je transforme les livres en livreDTO Data Transfer Object.
		final List<LivreDTO> dtos = Lists.transform(livres, 
				(Livre input) -> new LivreDTO
				(input.getDesignation(),
						input.getAuteur(), 
						input.getPhoto()));
		
		//Enregistrement dans le modèle
				model.addAttribute("listLivres", dtos);
		model.addAttribute("listLivres", pageLivres.getContent());
		model.addAttribute("pages", new int[pageLivres.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle", mc);
		return "livre";
	}
	
	@GetMapping("/delete")
	public String delete(Long id, int page, String motCle) {
		service.deleteLivreService(id);
		return "redirect:/index?page="+page+"&motCle="+motCle;
				
	}
	
	@GetMapping("/edit")
	public String edit(Model model, Long id) {
		Livre livre=service.editLivreService(id).get();
		model.addAttribute("livre", livre);
		
		return "editLivre";
				
	}
	
	@GetMapping("/formulaire")
	public String form(Model model) {
		model.addAttribute("livre", new Livre());
		
		return "formulaire";
				
	}
	
	@PostMapping("/save")
	public String save(@Valid Livre livre,
			BindingResult bindingResult,
			@RequestParam(name="picture") MultipartFile picture) throws Exception {
		if(bindingResult.hasErrors()){
			return "formulaire";
		}
		
		if(!(picture.isEmpty())){
			livre.setPhoto(picture.getOriginalFilename());
		}
		service.saveLivreService(livre);
		if(!(picture.isEmpty())){

			livre.setPhoto(picture.getOriginalFilename());
			picture.transferTo(new File(System.getProperty("user.dir")+"/yvan/"+livre.getId()));
		}
		
		return "confirmation";
				
	}
	
	@GetMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f=new File(imageDir+id);
		return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@PostMapping("/update")
	public String update(@Valid Livre livre,
			BindingResult bindingResult,
			@RequestParam(name="picture") MultipartFile picture) throws Exception {
		if(bindingResult.hasErrors()){
			return "EditProduit";
		}
		
		if(!(picture.isEmpty())){
			livre.setPhoto(picture.getOriginalFilename());
		}
		service.saveLivreService(livre);
		if(!(picture.isEmpty())){

			livre.setPhoto(picture.getOriginalFilename());
			picture.transferTo(new File(imageDir+livre.getId()));
		}
		
		return "confirmation";
				
	}

}
