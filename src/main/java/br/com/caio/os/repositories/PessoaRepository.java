package br.com.caio.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.caio.os.domain.Pessoa;

// Como se fosse o Model do Yii
@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
	
}
