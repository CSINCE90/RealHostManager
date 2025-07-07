package com.rhm.alloggiati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedinaDto {
    private Long id;
    private String nome;
    private String cognome;
    private String dataArrivo;
    private boolean esito;
}