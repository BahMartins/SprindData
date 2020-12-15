package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Trabalho;
import br.com.alura.spring.data.repository.TrabalhoRepository;

@Service
public class CrudTrabalhosService {

	
	private Boolean system = true;
	private final TrabalhoRepository trabalhoRepository;

	

	public CrudTrabalhosService(TrabalhoRepository trabalhoRepository) {
		this.trabalhoRepository = trabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Digite a ação de trabalho que deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int newAction = scanner.nextInt();

			switch (newAction) {
			case 1:
				salvar(scanner);
				break;

			case 2:
				atualizar(scanner);
				break;

			case 3:
				visualizar();
				break;

			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
	}

	

	private void salvar(Scanner scanner) {
		System.out.println("Insira a descrição do trabalho");
		String descricao = scanner.next();
		
		System.out.println("Insira o endereço");
		String endereco = scanner.next();
		
		Trabalho trabalho = new Trabalho();
		trabalho.setDescicao(descricao);
		trabalho.setEndereco(endereco);
		
		trabalhoRepository.save(trabalho);
		
		System.out.println("Trabalho salvo com sucesso!");
	}
	

	private void atualizar(Scanner scanner) {
		System.out.println("Insira o id que deseja atualizar");
		int id = scanner.nextInt();
		
		System.out.println("Insira a nova descrição");
		String descricao = scanner.next();
		
		System.out.println("Insira o novo endereço");
		String endereco = scanner.next();
		
		Trabalho trabalho = new Trabalho();
		trabalho.setId(id);
		trabalho.setDescicao(descricao);
		trabalho.setEndereco(endereco);
		
		trabalhoRepository.save(trabalho);
		
		System.out.println("Trabalho atualizado com sucesso!");
		
	}
	
	private void visualizar() {
		Iterable<Trabalho> trabalhos = trabalhoRepository.findAll();
		trabalhos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Informe o id do trabalho que deseja deletar");
		int id = scanner.nextInt();
		
		trabalhoRepository.deleteById(id);
		System.out.println("Trabalho deletado com sucesso!");
	}

	

}
