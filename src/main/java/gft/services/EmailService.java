package gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gft.entities.Email;
import gft.exception.EntityNotFoundException;
import gft.repositories.EmailRepository;

@Service
public class EmailService {
	
	private final EmailRepository emailRepository;
	
	public EmailService(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}
	
	public Email salvarEmail(Email email) {
		return emailRepository.save(email);
	}
	
	public Page<Email> listarTodasAsFiliais(Pageable pageable) {
		return emailRepository.findAll(pageable);
	}

	public Email buscarEmail(Long id) {
		
		Optional<Email> optional = emailRepository.findById(id);
		return optional.orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
		
	}
	
	public Email atualizarEmail(Email email, Long id) {
		
		Email emailOriginal = this.buscarEmail(id);
		email.setId(emailOriginal.getId());
		return emailRepository.save(email);
	
	}

	public void excluirEmail(Long id) {
		
		Email emailOriginal = this.buscarEmail(id);
		emailRepository.delete(emailOriginal);
		
	}
}