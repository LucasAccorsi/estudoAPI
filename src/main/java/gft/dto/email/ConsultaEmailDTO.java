package gft.dto.email;

import javax.validation.constraints.NotEmpty;

public class ConsultaEmailDTO {

	private Long id;

	@NotEmpty(message = "Informe o email do destinatario")
	private String destinatario;
	
	@NotEmpty(message = "Informe o titulo do email")
	private String titulo;
	
	private String corpo;

	public ConsultaEmailDTO() {}

	public ConsultaEmailDTO(Long id, @NotEmpty(message = "Informe o email do destinatario") String destinatario, @NotEmpty(message = "Informe o titulo do email") String titulo, String corpo) {
		this.id = id;
		this.destinatario = destinatario;
		this.titulo = titulo;
		this.corpo = corpo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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