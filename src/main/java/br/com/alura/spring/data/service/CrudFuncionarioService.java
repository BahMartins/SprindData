package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.Trabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.TrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final TrabalhoRepository trabalhoRepository;

	public CrudFuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository,
			TrabalhoRepository trabalhoRepository) {
		super();
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
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
				visualizar(scanner);
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
		System.out.println("Digite o nome do funcionario");
		String nome = scanner.next();

		System.out.println("Digite o CPF do funcionario");
		Integer cpf = scanner.nextInt();

		System.out.println("Digite o salario do funcionario");
		Double salario = scanner.nextDouble();

		System.out.println("Digite a data de contratação");
		String dataContratacao = scanner.next();

		System.out.println("Digite o ID do cargo");
		Integer cargoId = scanner.nextInt();

		List<Trabalho> unidades = unidade(scanner);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setTrabalho(unidades);

		funcionarioRepository.save(funcionario);
		System.out.println("Funcionario salvo com sucesso!");

	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
		Integer id = scanner.nextInt();

		System.out.println("Digite o nome");
		String nome = scanner.next();

		System.out.println("Digite o cpf");
		Integer cpf = scanner.nextInt();

		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();

		System.out.println("Digite a data de contracao");
		String dataContratacao = scanner.next();

		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado");
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual pagina voce deseja visualizar");
		Integer page = scanner.nextInt();

		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

		System.out.println("Quantidade de páginas" + funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total elemento " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

	private List<Trabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<Trabalho> unidades = new ArrayList<Trabalho>();

		while (isTrue) {
			System.out.println("Digite o ID do trabalho (Para sair digite 0)");
			Integer unidadeid = scanner.nextInt();

			if (unidadeid != 0) {
				Optional<Trabalho> unidade = trabalhoRepository.findById(unidadeid);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}

		}
		return unidades;
	}

}
