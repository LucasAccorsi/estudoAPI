package gft.dto.email;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegistroEmailDTO {
	
	@Schema(
            description = "Senha do email no gmail",
            example = "@startergft123"
    )
	@NotEmpty(message = "Informe a senha de seu email")
	private String senha;
	
	@Schema(
            description = "Email do destinatario",
            example = "lucasaccorsi@hotmail.com",
            required = true
    )
	@NotEmpty(message = "Informe o email do destinatario")
	private String destinatario;
	
	@Schema(
            description = "Titulo do email",
            example = "Teste",
            required = true
    )
	@NotEmpty(message = "Informe o titulo do email")
	private String titulo;
	
	@Schema(
            description = "Conteudo do email",
            example = "Esse e um email teste"
    )
	private String corpo;
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
}
