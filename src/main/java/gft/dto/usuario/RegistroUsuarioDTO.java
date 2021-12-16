package gft.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegistroUsuarioDTO {
	
	@Schema(
            description = "Email do Usuario",
            example = "lucasaccorsi@hotmail.com",
            required = true
    )
	private String email;
	
	@Schema(
            description = "Senha do Usuario",
            example = "123",
            required = true
    )
	private String senha;
	
	@Schema(
            description = "Perfil do Usuario",
            example = "1",
            required = true
    )
	private Long perfilId;
	
	public RegistroUsuarioDTO() {}
	
	public RegistroUsuarioDTO(String email, String senha, Long perfilId) {
		this.email = email;
		this.senha = senha;
		this.perfilId = perfilId;
	}
	
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
	
	public Long getPerfilId() {
		return perfilId;
	}
	
	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}
}