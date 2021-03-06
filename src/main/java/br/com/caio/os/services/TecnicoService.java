package br.com.caio.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.os.domain.Pessoa;
import br.com.caio.os.domain.Tecnico;
import br.com.caio.os.dtos.TecnicoDTO;
import br.com.caio.os.repositories.PessoaRepository;
import br.com.caio.os.repositories.TecnicoRepository;
import br.com.caio.os.services.exceptions.DataIntegratyViolationException;
import br.com.caio.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}

		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	public void delete(Integer id) {

		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui ordens de servico, não pode ser deletado");
		}

		repository.deleteById(id);
	}

	private Pessoa findByCPF(TecnicoDTO objDTO) {

		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;

	}

}
