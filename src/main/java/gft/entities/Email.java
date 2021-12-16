package gft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_email")
public class Email {
	
	@Schema(
            description = "Id do Usuario",
            example = "1"
    )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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

	public Email() {}
	
	public Email(Long id, @NotEmpty(message = "Informe o email do destinatario") String destinatario,
						  @NotEmpty(message = "Informe o titulo do email") String titulo, String corpo) {
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
