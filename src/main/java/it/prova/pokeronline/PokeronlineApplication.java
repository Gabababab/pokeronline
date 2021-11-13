package it.prova.pokeronline;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.Utente;

@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PokeronlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("User Special Player", "ROLE_SPECIAL_PLAYER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("User Special Player", "ROLE_SPECIAL_PLAYER"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("User Player", "ROLE_PLAYER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("User Player", "ROLE_PLAYER"));
		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin","admin", "Mario", "Rossi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", new Date());
			classicUser.setStato(StatoUtente.ATTIVO);
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("User Special Player", "ROLE_SPECIAL_PLAYER"));
			utenteServiceInstance.inserisciNuovo(classicUser);
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1","user1", "Antonioo", "Verdii", new Date());
			classicUser1.setStato(StatoUtente.ATTIVO);
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("User Player", "ROLE_PLAYER"));
			utenteServiceInstance.inserisciNuovo(classicUser1);
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", new Date());
			classicUser2.setStato(StatoUtente.ATTIVO);
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("User Player", "ROLE_PLAYER"));
			utenteServiceInstance.inserisciNuovo(classicUser2);
		}

	}

}
