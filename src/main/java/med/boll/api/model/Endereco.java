package med.boll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.boll.api.dto.endereco.DadosEndereco;
import med.boll.api.dto.medico.DadosAtualizacaoMedico;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public void atualizarEndereco(DadosEndereco end) {
        if(end.logradouro() != null){
            this.logradouro = end.logradouro();
        }
        if(end.bairro() != null){
            this.bairro = end.bairro();
        }
        if(end.cep() != null){
            this.cep = end.cep();
        }
        if(end.numero() != null){
            this.numero = end.numero();
        }
        if(end.complemento() != null){
            this.complemento = end.complemento();
        }
        if(end.cidade() != null){
            this.cidade = end.cidade();
        }
        if(end.uf() != null){
            this.uf = end.uf();
        }
    }
}
