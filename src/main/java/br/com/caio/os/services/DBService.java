package br.com.caio.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.os.domain.Cliente;
import br.com.caio.os.domain.OS;
import br.com.caio.os.domain.Tecnico;
import br.com.caio.os.domain.enuns.Prioridade;
import br.com.caio.os.domain.enuns.Status;
import br.com.caio.os.repositories.ClienteRepository;
import br.com.caio.os.repositories.OSRepository;
import br.com.caio.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Caio Barroncas", "012.369.882-05", "(92) 7777-7777");
		Tecnico t2 = new Tecnico(null, "Cesar Augusto", "001.356.012-31", "(92) 8888-8888");
		Cliente c1 = new Cliente(null, "Betina Campos", "441.237.102-63", "(92) 9999-9999");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste Create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}
}
