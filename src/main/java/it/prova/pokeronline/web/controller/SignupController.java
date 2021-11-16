package it.prova.pokeronline.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.pokeronline.dto.RuoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.utility.UtilityForm;
import it.prova.pokeronline.validation.ValidationNoPassword;
import it.prova.pokeronline.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/formregistrazione")
	public String formRegistrazione(Model model, HttpServletRequest request) {
		
		model.addAttribute("utente_signup_attribute", new UtenteDTO());
		return "signup/registrazione";		
	}
	
	@PostMapping("/registrazione")
	public String eseguiRegistrazione(@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("utente_signup_attribute") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (!result.hasFieldErrors("password") && !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			model.addAttribute("utente_signup_attribute", utenteDTO);
			return "signup/formregistrazione";
		}
		
		utenteDTO.getRuoli().add(new RuoloDTO("User Player", "ROLE_PLAYER"));
		utenteService.inserisciNuovo(utenteDTO.buildUtenteModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/home";
	}
	
}
