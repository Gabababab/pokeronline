package it.prova.pokeronline.web.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

@Controller
@RequestMapping(value = "/tavolo")
public class TavoloController {

	@Autowired
	private TavoloService tavoloService;
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping
	public ModelAndView listAllTavoli() {
		ModelAndView mv = new ModelAndView();
		List<Tavolo> tavoli = tavoloService.listAllElements();
		mv.addObject("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		mv.setViewName("tavolo/list");
		return mv;
	}
	
	@PostMapping("/listTavoliUtente")
	public String listTavoliUtente(TavoloDTO tavoloExample, ModelMap model) {
		List<Tavolo> tavoli = tavoloService.findMieiTavoliByExample(tavoloExample.buildTavoloModel());
		for(Tavolo item:tavoli) {
			System.out.println(item.getDenominazione());
			System.out.println(item.getCreditoMinimo());
			System.out.println(item.getEsperienzaMinima());
			System.out.println(item.getUtenteCreatore().getNome());
		}
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/list";
	}
	
	@PostMapping("/list")
	public String listTavoli(TavoloDTO tavoloExample, ModelMap model) {
		List<Tavolo> tavoli = tavoloService.listAllElements();
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/list";
	}
	
	@GetMapping("/searchTavoliUtente")
	public String searchTavoliUtente(TavoloDTO tavoloExample ,Model model, HttpServletRequest request) {
		model.addAttribute("search_tavolo_attr", new TavoloDTO());
		
		return "tavolo/searchtavoliutente";
	}
	
	
	@GetMapping("/search")
	public String searchTavolo(Model model) {
		model.addAttribute("search_tavolo_attr", new TavoloDTO());
		return "tavolo/search";
	}
	
	@GetMapping("/insert")
	public String createTavolo(Model model) {
		model.addAttribute("insert_tavolo_attr", new TavoloDTO());
		return "tavolo/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_tavolo_attr") TavoloDTO tavoloDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request, Principal principal) {


		if (result.hasErrors()) 
			return "tavolo/insert";
		
		Utente utenteInserimento= utenteService.findByUsername(request.getUserPrincipal().getName());
		UtenteDTO utenteCreatore = UtenteDTO.buildUtenteDTOFromModel(utenteInserimento);
		
		tavoloDTO.setUtenteCreatore(utenteCreatore);
	
		tavoloService.inserisciNuovo(tavoloDTO.buildTavoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/tavolo/searchTavoliUtente";
	}
	
}
