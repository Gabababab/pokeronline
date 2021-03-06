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
		tavoloExample.setUtenteCreatore(utenteService.findByUsername(request.getUserPrincipal().getName()).getId());
		List<Tavolo> tavoli = tavoloService.findMieiTavoliByExample(tavoloExample);
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		return "tavolo/listtavoliutente";
	}
	
	@PostMapping("/list")
	public String listTavoli(TavoloDTO tavoloExample, ModelMap model, HttpServletRequest request) {
		
		List<Tavolo> tavoli = tavoloService.findByExample(tavoloExample);
		Utente utente = utenteService.findByUsername(request.getUserPrincipal().getName());
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		model.addAttribute("utente_attribute", utente);
		return "tavolo/list";
	}

	@GetMapping("/searchTavoliUtente")
	public String searchTavoliUtente(Model model, HttpServletRequest request) {
		
		model.addAttribute("search_tavolo_attr", new TavoloDTO());
		return "tavolo/searchtavoliutente";
	}
	
	@GetMapping("/searchTavoliGestione")
	public String searchTavoliGestione(Model model, HttpServletRequest request) {
		model.addAttribute("search_tavolo_attr", new TavoloDTO());

		return "tavolo/searchtavoligestione";
	}
	
	@PostMapping("/listGestione")
	public String listTavoliGestione(TavoloDTO tavoloExample, ModelMap model, HttpServletRequest request) {

		List<Tavolo> tavoli = tavoloService.findByExampleGestioneAdmin(tavoloExample);
		Utente utente = utenteService.findByUsername(request.getUserPrincipal().getName());
		model.addAttribute("tavolo_list_attribute", TavoloDTO.createTavoloDTOListFromModelList(tavoli));
		model.addAttribute("utente_attribute", utente);
		return "tavolo/listtavoliutente";
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

		tavoloDTO.setUtenteCreatore(utenteCreatore.getId());

		tavoloService.inserisciNuovo(tavoloDTO.buildTavoloModel(utenteCreatore.buildUtenteModel(false)));

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
		UtenteDTO utenteCreatore=UtenteDTO.buildUtenteDTOFromModel(tavolo.getUtenteCreatore());
		tavoloDTO.setUtenteCreatore(utenteCreatore.getId());

		tavoloService.aggiorna(tavoloDTO.buildTavoloModel(utenteCreatore.buildUtenteModel(true)));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/tavolo/listTavoliUtente";
	}

	@GetMapping("/gioca/{idTavolo}")
	public String gioca(@PathVariable(required = true) Long idTavolo, Model model, Principal principal) {

		Tavolo tavoloDiGioco = tavoloService.caricaSingoloTavoloConGiocatori(idTavolo);
		Utente utenteInSessione = utenteService.findByUsername(principal.getName());

		utenteInSessione.setTavolo(tavoloDiGioco);
		
		utenteService.aggiorna(utenteInSessione);
		model.addAttribute("tavolo_gioco_attr", tavoloDiGioco);
		model.addAttribute("utente_gioco_attr", utenteInSessione);
		return "tavolo/gioco";
	}

	@GetMapping("/eseguigioco/{idTavolo}")
	public String eseguiGioco(@PathVariable(required = true) Long idTavolo, Model model, Principal principal) {

		Utente utenteInSessione = utenteService.findByUsername(principal.getName());
		Tavolo tavoloGioco = tavoloService.caricaSingoloTavoloConGiocatori(idTavolo);

		double segno = Math.random();
		int random = (int) Math.round(Math.random() * 1000);
		
		if (segno >= 0.5) {
			utenteInSessione.setCreditoAccumulato(utenteInSessione.getCreditoAccumulato() + random);
			model.addAttribute("esito", "Hai vinto " + random + " euro");
		} else {
			utenteInSessione.setCreditoAccumulato(utenteInSessione.getCreditoAccumulato() - random);
			model.addAttribute("esito", "Hai perso " + random + " euro");
		}

		utenteService.aggiorna(utenteInSessione);

		model.addAttribute("tavolo_gioco_attr", tavoloGioco);
		model.addAttribute("utente_gioco_attr", utenteInSessione);
		return "tavolo/gioco";
	}

	@GetMapping("/lasciatavolo")
	public String lascia(Model model, HttpServletRequest request) {

		Utente utenteSessione = utenteService.findByUsername(request.getUserPrincipal().getName());

		utenteSessione.setTavolo(null);
		utenteSessione.setEsperienzaAccumulata(utenteSessione.getEsperienzaAccumulata() + 1);

		utenteService.aggiorna(utenteSessione);

		return "redirect:/home";
	}
	
	@GetMapping("/lastgame")
	public String gioca(Model model, RedirectAttributes redirectAttrs,Principal principal) {
		
		Utente utenteSessione = utenteService.findByUsernameConTavolo(principal.getName());
		
		if(utenteSessione.getTavolo() == null || utenteSessione.getTavolo().getId() == null) {
			redirectAttrs.addFlashAttribute("errorMessage", "Non stai giocando a nessun tavolo, inizia una partita.");
			return "redirect:/home";
		}
		
		Tavolo tavoloGioco = tavoloService.caricaSingoloTavoloConGiocatori(utenteSessione.getTavolo().getId());
		
		model.addAttribute("tavolo_gioco_attr", tavoloGioco);
		model.addAttribute("utente_gioco_attr", utenteSessione);
		return "tavolo/gioco";
	}
}
