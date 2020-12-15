package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargosServices;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudTrabalhosService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	
	private final CrudCargosServices cargoService;	
	private final CrudTrabalhosService trabalhoService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatoriosService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	
	
	public SpringDataApplication (CrudCargosServices cargoService, CrudTrabalhosService trabalhoService, RelatoriosService relatorioService,
			CrudFuncionarioService funcionarioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;	
		this.trabalhoService = trabalhoService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		while(system){
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Trabaho");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio Funcionario Dinamico");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				trabalhoService.inicial(scanner);
				break;
			case 3:
				funcionarioService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamico.inicial(scanner);
				break;

			default:
				system = false;
				break;
			}
			
			
			
		}
		
		
	}
	
}
