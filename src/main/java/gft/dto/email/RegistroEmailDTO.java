package gft.dto.email;

import javax.validation.constraints.NotEmpty;

public class RegistroEmailDTO {
	
	@NotEmpty(message = "Informe a senha de seu email")
	private String senha;
	
	
	@NotEmpty(message = "Informe o email do destinatario")
	private String destinatario;
	
	@NotEmpty(message = "Informe o titulo do email")
	private String titulo;
	
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
