package com.rhm.alloggiati.client;

import com.rhm.alloggiati.ws.Service;
import com.rhm.alloggiati.ws.ServiceSoap;
import com.rhm.alloggiati.ws.TokenInfo;
import com.rhm.alloggiati.ws.EsitoOperazioneServizio;
import com.rhm.alloggiati.ws.ArrayOfString;
import com.rhm.alloggiati.ws.ElencoSchedineEsito;
import javax.xml.ws.Holder;

import org.springframework.stereotype.Component;
/**
 * Client per la comunicazione con il Web Service Alloggiati Web (SOAP).
 * Incapsula le chiamate ai metodi esposti dal WSDL della Polizia di Stato.
 */

@Component
public class AlloggiatiWebClient {

    private final ServiceSoap client;

    /**
     * Inizializza il client SOAP utilizzando la configurazione fornita da wsimport.
     */
    public AlloggiatiWebClient() {
        ServiceSoap soap = null;
        try {
            System.out.println(">> Inizializzazione client SOAP AlloggiatiWebClient...");
            soap = new Service().getServiceSoap();
        } catch (Exception e) {
            System.err.println("⚠️ Errore durante la creazione del client SOAP: " + e.getMessage());
            e.printStackTrace();
        }
        this.client = soap;
    }

    /**
     * Genera un token di autenticazione valido da usare per le successive operazioni.
     *
     * @param utente Username della struttura
     * @param password Password della struttura
     * @param wsKey Chiave WSKEY ottenuta dal portale Alloggiati Web
     * @return TokenInfo contenente token, data di scadenza, ecc.
     * @throws RuntimeException se il servizio restituisce errore
     */
    public TokenInfo generateToken(String utente, String password, String wsKey) {
        Holder<EsitoOperazioneServizio> esito = new Holder<>();
        Holder<TokenInfo> token = new Holder<>();

        client.generateToken(utente, password, wsKey, esito, token);

        if (esito.value != null && !esito.value.isEsito()) {
            throw new RuntimeException("Errore generazione token: "
                + esito.value.getErroreDes() + " (Codice: " + esito.value.getErroreCod() + ")");
        }

        return token.value;
    }

    /**
     * Invia una lista di schedine (formattate in tracciato record) al servizio Alloggiati Web.
     *
     * @param righe Lista di stringhe, ciascuna rappresentante una schedina formattata
     * @param token Token di autenticazione valido ottenuto tramite generateToken
     * @param utente Username della struttura
     * @return Esito dell'operazione, contenente stato e dettagli di errore (se presenti)
     */
    public EsitoOperazioneServizio sendSchedine(ArrayOfString righe, String token, String utente) {
        Holder<ElencoSchedineEsito> esitoSchedine = new Holder<>();
        Holder<EsitoOperazioneServizio> esitoOperazione = new Holder<>();

        client.send(utente, token, righe, esitoSchedine, esitoOperazione);

        if (esitoOperazione.value != null && !esitoOperazione.value.isEsito()) {
            throw new RuntimeException("Errore invio schedine: "
                + esitoOperazione.value.getErroreDes() + " (Codice: " + esitoOperazione.value.getErroreCod() + ")");
        }

        return esitoOperazione.value;
    }

    /**
     * Esegue un test sulle schedine formattate, senza inviarle ufficialmente.
     *
     * @param righe Lista di schedine in formato tracciato record
     * @param token Token di autenticazione valido
     * @param utente Username della struttura
     * @return Esito dell'operazione, contenente stato e dettagli di errore (se presenti)
     */
    public EsitoOperazioneServizio testSchedine(ArrayOfString righe, String token, String utente) {
        Holder<ElencoSchedineEsito> esitoSchedine = new Holder<>();
        Holder<EsitoOperazioneServizio> esitoOperazione = new Holder<>();

        client.test(utente, token, righe, esitoSchedine, esitoOperazione);

        if (esitoOperazione.value != null && !esitoOperazione.value.isEsito()) {
            throw new RuntimeException("Errore test schedine: "
                + esitoOperazione.value.getErroreDes() + " (Codice: " + esitoOperazione.value.getErroreCod() + ")");
        }

        return esitoOperazione.value;
    }

    // TODO: downloadRicevuta(LocalDate data)
    // TODO: scaricaTabella(String tipo)
}