package br.com.alura.spring.data.specification;


import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

public class SpecificarionFuncionario {

	public static Specification<Funcionario> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.like(root.get("nome"), "%" + nome + "%");

	}
	
	public static Specification<Funcionario> cpf(Integer cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.equal(root.get("cpf"), cpf);

	}

	
	
	public static Specification<Funcionario> salario(Double salario) {
		return (root, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.greaterThan(root.get("salario"), salario);

	}

	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);

	}

}
