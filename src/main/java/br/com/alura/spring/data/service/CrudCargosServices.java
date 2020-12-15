package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargosServices {

	@Autowired
	private final CargoRepository cargoRepository;

	private Boolean system = true;

	public CrudCargosServices(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Digite a ação de cargo que deseja executar");
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

	private void atualizar(Scanner scanner) {
		System.out.println("Insira o id que deseja atualizar");
		int id = scanner.nextInt();

		System.out.println("Informe a nova descrição");
		String novaDescricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(novaDescricao);
		cargoRepository.save(cargo);

		System.out.println("Arquivo atualizado com sucesso");

	}

	private void salvar(Scanner scanner) {
		System.out.println("Insira a descrição do cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);

		cargoRepository.save(cargo);

		System.out.println("Descrição salva com sucesso");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Insira o id que deseja atualizar");
		int id = scanner.nextInt();
		
		cargoRepository.deleteById(id);
		
		System.out.println("Deletado com sucesso");
		
	}
	
	
}
