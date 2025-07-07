package com.rhm.alloggiati.controller;

import com.rhm.alloggiati.client.AlloggiatiWebClient;
import com.rhm.alloggiati.service.SchedinaService;
import com.rhm.alloggiati.ws.TokenInfo;
import com.rhm.alloggiati.ws.EsitoOperazioneServizio;
import com.rhm.alloggiati.ws.ArrayOfString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alloggiati")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class AlloggiatiWebController {

    private final AlloggiatiWebClient client;

    @Autowired
    private SchedinaService schedinaService;

    public AlloggiatiWebController(AlloggiatiWebClient client) {
        this.client = client;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generaToken(
            @RequestParam String utente,
            @RequestParam String password,
            @RequestParam String wsKey
    ) {
        try {
            TokenInfo token = client.generateToken(utente, password, wsKey);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore generazione token: " + e.getMessage());
        }
    }

    @PostMapping("/test-schedine")
    public ResponseEntity<?> testSchedine(
            @RequestParam List<String> righe,
            @RequestParam String token,
            @RequestParam String utente
    ) {
        try {
            // Converti la List<String> in ArrayOfString
            ArrayOfString arrayOfString = new ArrayOfString();
            arrayOfString.getString().addAll(righe);

            // Chiamata al metodo corretto con ArrayOfString
            EsitoOperazioneServizio esito = client.testSchedine(arrayOfString, token, utente);
            return ResponseEntity.ok(esito);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore invio schedine: " + e.getMessage());
        }
    }

    @PostMapping("/genera-xml")
    public ResponseEntity<?> generaXml(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String sesso,
            @RequestParam String dataNascita,
            @RequestParam String luogoNascita,
            @RequestParam String cittadinanza,
            @RequestParam String tipoDocumento,
            @RequestParam String numeroDocumento,
            @RequestParam String rilasciatoDaStato,
            @RequestParam String dataArrivo,
            @RequestParam String comuneAlloggio,
            @RequestParam boolean capoFamiglia
    ) {
        try {
            // Costruzione XML semplificato per anteprima
            String xml = String.format(
                "<schedina>" +
                "<nome>%s</nome>" +
                "<cognome>%s</cognome>" +
                "<sesso>%s</sesso>" +
                "<dataNascita>%s</dataNascita>" +
                "<luogoNascita>%s</luogoNascita>" +
                "<cittadinanza>%s</cittadinanza>" +
                "<tipoDocumento>%s</tipoDocumento>" +
                "<numeroDocumento>%s</numeroDocumento>" +
                "<rilasciatoDaStato>%s</rilasciatoDaStato>" +
                "<dataArrivo>%s</dataArrivo>" +
                "<comuneAlloggio>%s</comuneAlloggio>" +
                "<capoFamiglia>%s</capoFamiglia>" +
                "</schedina>",
                nome, cognome, sesso, dataNascita, luogoNascita, cittadinanza,
                tipoDocumento, numeroDocumento, rilasciatoDaStato, dataArrivo, comuneAlloggio,
                capoFamiglia ? "true" : "false"
            );
            return ResponseEntity.ok(xml);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore generazione XML: " + e.getMessage());
        }
    }

    @GetMapping("/storico")
    public ResponseEntity<?> storico(@RequestHeader("X-User-Id") String userId) {
        try {
            return ResponseEntity.ok(schedinaService.getStoricoByUser(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore nel recupero dello storico: " + e.getMessage());
        }
    }
}