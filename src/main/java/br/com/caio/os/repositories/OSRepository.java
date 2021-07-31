package br.com.caio.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caio.os.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}
