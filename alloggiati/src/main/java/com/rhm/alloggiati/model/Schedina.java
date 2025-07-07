package com.rhm.alloggiati.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Schedina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean esito;

    private String userId;
    private String tipoScheda;              // 2 caratteri
    private LocalDate dataArrivo;           // yyyyMMdd
    private String cognome;                 // max 35
    private String nome;                    // max 35
    private String sesso;                   // M/F
    private LocalDate dataNascita;          // yyyyMMdd
    private String comuneStatoNascita;      // ISTAT codice (se italiano) o stato estero
    private String cittadinanza;            // ISTAT codice stato
    private String tipoDocumento;           // da tabella tipo documento
    private String numeroDocumento;         // max 20
    private String rilasciatoDa;            // luogo o autorità
    private LocalDate dataRilascio;         // yyyyMMdd
    private String motivoViaggio;           // codice
    private String mezzoDiTrasporto;        // codice
    private int progressivoScheda;          // numerico
    private String tipoAlloggiato;          // codice tipo alloggiato
    private String tipoSoggiorno;           // es. 1, 2
    private int durataSoggiorno;            // in giorni

    // Getters e Setters

    public String getTipoScheda() {
        return tipoScheda;
    }

    public void setTipoScheda(String tipoScheda) {
        this.tipoScheda = tipoScheda;
    }

    public LocalDate getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(LocalDate dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getComuneStatoNascita() {
        return comuneStatoNascita;
    }

    public void setComuneStatoNascita(String comuneStatoNascita) {
        this.comuneStatoNascita = comuneStatoNascita;
    }

    public String getCittadinanza() {
        return cittadinanza;
    }

    public void setCittadinanza(String cittadinanza) {
        this.cittadinanza = cittadinanza;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRilasciatoDa() {
        return rilasciatoDa;
    }

    public void setRilasciatoDa(String rilasciatoDa) {
        this.rilasciatoDa = rilasciatoDa;
    }

    public LocalDate getDataRilascio() {
        return dataRilascio;
    }

    public void setDataRilascio(LocalDate dataRilascio) {
        this.dataRilascio = dataRilascio;
    }

    public String getMotivoViaggio() {
        return motivoViaggio;
    }

    public void setMotivoViaggio(String motivoViaggio) {
        this.motivoViaggio = motivoViaggio;
    }

    public String getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(String mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public int getProgressivoScheda() {
        return progressivoScheda;
    }

    public void setProgressivoScheda(int progressivoScheda) {
        this.progressivoScheda = progressivoScheda;
    }

    public String getTipoAlloggiato() {
        return tipoAlloggiato;
    }

    public void setTipoAlloggiato(String tipoAlloggiato) {
        this.tipoAlloggiato = tipoAlloggiato;
    }

    public String getTipoSoggiorno() {
        return tipoSoggiorno;
    }

    public void setTipoSoggiorno(String tipoSoggiorno) {
        this.tipoSoggiorno = tipoSoggiorno;
    }

    public int getDurataSoggiorno() {
        return durataSoggiorno;
    }

    public void setDurataSoggiorno(int durataSoggiorno) {
        this.durataSoggiorno = durataSoggiorno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }
}
