package gft.controllers;

import java.util.Properties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.email.ConsultaEmailDTO;
import gft.dto.email.EmailMapper;
import gft.dto.email.RegistroEmailDTO;
import gft.entities.Email;
import gft.exception.EmailException;
import gft.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/email")
@Tag(name = "email", description = "API Email")
public class EmailController {
	
	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	
	@Operation(
            summary = "Listar todos emails",
            description = "Listar todos emails",
            tags = "email",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Email.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = EmailException.class)
                            )
                    )
            }
    )
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<ConsultaEmailDTO>> buscarTodasOsEmails(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(emailService.listarTodasAsFiliais(pageable).map(EmailMapper::fromEntity));
	}
	
	@Operation(
            summary = "Enviar um email",
            description = "Enviar um email",
            tags = "email",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Email.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = EmailException.class)
                            )
                    )
            }
    )
	@PostMapping
	public ResponseEntity<ConsultaEmailDTO> salvarEmail(@RequestBody RegistroEmailDTO dto) {
		
		Email email = emailService.salvarEmail(EmailMapper.fromDTO(dto));
		ResponseEntity<ConsultaEmailDTO> responseEntity = ResponseEntity.ok(EmailMapper.fromEntity(email));
		
		if (responseEntity.getStatusCodeValue() == 200) {
			
			JavaMailSenderImpl env = new JavaMailSenderImpl();
			Properties properties = new Properties();
			SimpleMailMessage message = new SimpleMailMessage();
			
			UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String from = principal.getUsername();
			String password = dto.getSenha();
			
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			env.setUsername(from);
			env.setPassword(password);
			env.setJavaMailProperties(properties);
			
			message.setFrom(from);
			message.setTo(dto.getDestinatario());
			message.setSubject(dto.getTitulo());
			message.setText(dto.getCorpo());
			 
			env.send(message);
		}
		return responseEntity;
		
	}
	
	@Operation(
            summary = "Buscar email por id",
            description = "Buscar email por id",
            tags = "email",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Email.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = EmailException.class)
                            )
                    )
            }
    )
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ConsultaEmailDTO> buscarEmail(@PathVariable Long id) {
		
		Email email = emailService.buscarEmail(id);
		return ResponseEntity.ok(EmailMapper.fromEntity(email));
		
	}
	
	@Operation(
            summary = "Editar e reenviar email",
            description = "Editar e reenviar email",
            tags = "email",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Email.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = EmailException.class)
                            )
                    )
            }
    )
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ConsultaEmailDTO> alterarEmail(@RequestBody RegistroEmailDTO dto, @PathVariable Long id) {
		
		try {
			Email email = emailService.atualizarEmail(EmailMapper.fromDTO(dto), id);
			ResponseEntity<ConsultaEmailDTO> responseEntity = ResponseEntity.ok(EmailMapper.fromEntity(email));
			
			if (responseEntity.getStatusCodeValue() == 200) {
				
				JavaMailSenderImpl env = new JavaMailSenderImpl();
				Properties properties = new Properties();
				SimpleMailMessage message = new SimpleMailMessage();
				
				UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String from = principal.getUsername();
				String password = dto.getSenha();
				
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				env.setUsername(from);
				env.setPassword(password);
				env.setJavaMailProperties(properties);
				
				message.setFrom(from);
				message.setTo(dto.getDestinatario());
				message.setSubject("(Edited) " + dto.getTitulo());
				message.setText(dto.getCorpo());
				 
				env.send(message);
			}
			return responseEntity;
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@Operation(
            summary = "deletar um email",
            description = "deletar um email",
            tags = "email",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Email.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = EmailException.class)
                            )
                    )
            }
    )
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ConsultaEmailDTO> excluirEmail(@PathVariable Long id) {
		
		try {
			emailService.excluirEmail(id);
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
}