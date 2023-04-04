package com.med.api.medico;

import com.med.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAttMedico(
        @NotNull
        Long id, String nome,
        String telefone,
        DadosEndereco endereco) {
}
