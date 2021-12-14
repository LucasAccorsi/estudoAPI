package gft.dto.email;

import gft.entities.Email;

public class EmailMapper {
	
	public static Email fromDTO(RegistroEmailDTO dto) {
		return new Email(null, dto.getDestinatario(), dto.getTitulo(), dto.getCorpo());	
	}
	
	public static ConsultaEmailDTO fromEntity(Email email) {
		return new ConsultaEmailDTO(email.getId(), email.getDestinatario(), email.getTitulo(), email.getCorpo());
	}
}