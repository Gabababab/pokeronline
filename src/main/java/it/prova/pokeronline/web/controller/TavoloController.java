package it.prova.pokeronline.web.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String listTavoliUtente(TavoloDTO tavoloExample, ModelMap model, HttpServletRequest request) {
		tavoloExample.setUtenteCreatore(
				UtenteDTO.buildUtenteDTOFromModel(utenteService.findByUsername(request.getUserPrincipal().getName())));
		List<Tavolo> tavoli = tavoloService.findMieiTavoliByExample(tavoloExample);
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/listtavoliutente";
	}

	@PostMapping("/list")
	public String listTavoli(TavoloDTO tavoloExample, ModelMap model, HttpServletRequest request) {
		
//		Utente utente=utenteService.findByUsername(request.getUserPrincipal().getName());
		
//		if(tavoloExample.getEsperienzaMinima()==null)
//			tavoloExample.setEsperienzaMinima(0);
//		
//		if(tavoloExample.getCreditoMinimo()==null)
//			tavoloExample.setEsperienzaMinima(0);
		
		List<Tavolo> tavoli = tavoloService.findByExample(tavoloExample);
//		System.out.println(tavoli.get(0).getDenominazione());
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/list";
	}

	@GetMapping("/searchTavoliUtente")
	public String searchTavoliUtente(TavoloDTO tavoloExample, Model model, HttpServletRequest request) {
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

		Utente utenteInserimento = utenteService.findByUsername(request.getUserPrincipal().getName());
		UtenteDTO utenteCreatore = UtenteDTO.buildUtenteDTOFromModel(utenteInserimento);

		tavoloDTO.setUtenteCreatore(utenteCreatore);

		tavoloService.inserisciNuovo(tavoloDTO.buildTavoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/tavolo";
	}

	@GetMapping("/delete/{idTavolo}")
	public String delete(@PathVariable(required = true) Long idTavolo, Model model) {

		Tavolo tavolo = tavoloService.caricaSingoloElemento(idTavolo);
		model.addAttribute("delete_tavolo_attr", tavolo);
		return "tavolo/delete";
	}

	@PostMapping("/savedelete")
	public String salvadelete(@RequestParam Long idTavolo, Model model, RedirectAttributes redirectAttrs,
			HttpServletRequest request) {

		if (tavoloService.caricaSingoloTavoloConGiocatori(idTavolo).getUtenti().size() == 0) {
			tavoloService.rimuoviById(idTavolo);
			request.setAttribute("successMessage", "Operazione eseguita correttamente");
		} else
			request.setAttribute("errorMessage", "Ci sono ancora giocatori che stanno giocando");

		List<Tavolo> tavoli = tavoloService
				.listAllTavoliUtente(utenteService.findByUsername(request.getUserPrincipal().getName()));
		model.addAttribute("tavolo_list_attribute", tavoli);

		return "tavolo/listtavoliutente";
	}
	
	@GetMapping("/edit/{idTavolo}")
	public String edit(@PathVariable(required = true) Long idTavolo, Model model) {
		Tavolo tavolo = tavoloService.caricaSingoloElemento(idTavolo);
		model.addAttribute("update_tavolo_attr", tavolo);
		return "tavolo/edit";
	}

	@PostMapping("/saveUpdate")
	public String saveUpdate(@Valid @ModelAttribute("update_tavolo_attr") TavoloDTO tavoloDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors())
			return "tavolo/edit";

		Tavolo tavolo = tavoloService.caricaSingoloElemento(tavoloDTO.getId());
		tavoloDTO.setUtentiGiocatori(tavolo.getUtenti());
		tavoloDTO.setUtenteCreatore(UtenteDTO.buildUtenteDTOFromModel(tavolo.getUtenteCreatore()));

		tavoloService.inserisciNuovo(tavoloDTO.buildTavoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/tavolo/listTavoliUtente";
	}
}
