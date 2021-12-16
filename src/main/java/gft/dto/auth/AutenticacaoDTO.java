package gft.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public class AutenticacaoDTO {
	
	@Schema(
            description = "Email para Autenticacao",
            example = "startergft@gmail.com",
            required = true
    )
	private String email;
	
	@Schema(
            description = "Senha para Autenticacao",
            example = "@startergft123",
            required = true
    )
	private String senha;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}