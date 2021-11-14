package it.prova.pokeronline.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;

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
	
	@PostMapping("/list")
	public String listTavoli(TavoloDTO tavoloExample, ModelMap model) {
		System.out.println(tavoloExample);
		List<Tavolo> tavoli = tavoloService.findByExample(tavoloExample.buildTavoloModel());
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/list";
	}
	
	@GetMapping("/searchTavoliUtente")
	public String searchTavoliUtente(Model model, HttpServletRequest request) {
		List<Tavolo> tavoli = tavoloService.listAllTavoliUtente(utenteService.findByUsername(request.getUserPrincipal().getName()));
		model.addAttribute("tavolo_list_attribute", tavoli);
		return "tavolo/list";
	}
	
	@GetMapping("/search")
	public String searchTavolo(Model model) {
		model.addAttribute("search_tavolo_attr", new TavoloDTO());
		return "tavolo/search";
	}
}
