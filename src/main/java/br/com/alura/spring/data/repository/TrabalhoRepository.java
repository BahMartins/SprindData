package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Trabalho;

@Repository
public interface TrabalhoRepository extends CrudRepository<Trabalho, Integer>{

}
