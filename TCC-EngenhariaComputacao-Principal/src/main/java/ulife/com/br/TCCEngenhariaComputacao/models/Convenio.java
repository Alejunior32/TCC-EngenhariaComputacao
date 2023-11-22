package ulife.com.br.TCCEngenhariaComputacao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ulife.com.br.TCCEngenhariaComputacao.config.encrypt.EncryptString;

import java.time.LocalDate;

@Table(name = "convenio")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeConvenio;
    private String plano;
    @Convert(converter = EncryptString.class)
    private String numeroIdentificacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
}
