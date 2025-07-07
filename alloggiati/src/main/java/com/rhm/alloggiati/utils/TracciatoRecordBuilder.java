package com.rhm.alloggiati.utils;

import com.rhm.alloggiati.model.Schedina;

import java.time.format.DateTimeFormatter;

public class TracciatoRecordBuilder {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * Costruisce una riga del tracciato record da una schedina.
     *
     * @param schedina La schedina da convertire
     * @return Una stringa conforme al tracciato record Alloggiati Web
     */
    public String build(Schedina schedina) {
        StringBuilder sb = new StringBuilder();

        sb.append(fixed(schedina.getTipoScheda(), 2));
        sb.append(formatDate(schedina.getDataArrivo(), 8));
        sb.append(fixed(schedina.getCognome(), 35));
        sb.append(fixed(schedina.getNome(), 35));
        sb.append(fixed(schedina.getSesso(), 1));
        sb.append(formatDate(schedina.getDataNascita(), 8));
        sb.append(fixed(schedina.getComuneStatoNascita(), 21));
        sb.append(fixed(schedina.getCittadinanza(), 4));
        sb.append(fixed(schedina.getTipoDocumento(), 2));
        sb.append(fixed(schedina.getNumeroDocumento(), 20));
        sb.append(fixed(schedina.getRilasciatoDa(), 21));
        sb.append(formatDate(schedina.getDataRilascio(), 8));
        sb.append(fixed(schedina.getMotivoViaggio(), 1));
        sb.append(fixed(schedina.getMezzoDiTrasporto(), 1));
        sb.append(leftPad(String.valueOf(schedina.getProgressivoScheda()), 9));
        sb.append(fixed(schedina.getTipoAlloggiato(), 1));
        sb.append(fixed(schedina.getTipoSoggiorno(), 1));
        sb.append(leftPad(String.valueOf(schedina.getDurataSoggiorno()), 3));

        return sb.toString();
    }

    private String fixed(String value, int length) {
        if (value == null) value = "";
        return String.format("%-" + length + "s", value).substring(0, length);
    }

    private String leftPad(String value, int length) {
        if (value == null) value = "";
        return String.format("%" + length + "s", value).replace(' ', '0');
    }

    private String formatDate(java.time.LocalDate date, int length) {
        if (date == null) return "0".repeat(length);
        return date.format(DATE_FORMAT);
    }
}
