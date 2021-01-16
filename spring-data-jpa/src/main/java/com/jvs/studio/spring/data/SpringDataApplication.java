package com.jvs.studio.spring.data;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jvs.studio.spring.data.services.CargoService;
import com.jvs.studio.spring.data.services.FuncionarioService;
import com.jvs.studio.spring.data.services.UnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

  private boolean system = true;
  private CargoService cargoservice;
  private FuncionarioService funcionarioService;
  private UnidadeTrabalhoService unidadeTrabalhoService;


  private static final Logger log = LoggerFactory.getLogger(SpringDataApplication.class);


  public SpringDataApplication(CargoService cargoservice,
      FuncionarioService unidadeTrabalhorService, UnidadeTrabalhoService unidadeTrabalhoService) {
    super();
    this.cargoservice = cargoservice;
    this.funcionarioService = unidadeTrabalhorService;
    this.unidadeTrabalhoService = unidadeTrabalhoService;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringDataApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    try (Scanner scanner = new Scanner(System.in)) {

      while (system) {
        log.info("Qual acao voce quer executar? ");
        log.info("0 - Sair");
        log.info("1 - Cargo");
        log.info("2 - Funcionario");
        log.info("3 - Unidade de Trabalho");

        int action = Integer.parseInt(scanner.next());
        switch (action) {
          case 1:
            cargoservice.inicial();
            break;
          case 2:
            funcionarioService.inicial();
            break;
          case 3:
            unidadeTrabalhoService.inicial();
            break;
          default:
            system = false;
            break;
        }
      }
    } catch (Exception e) {
      log.error("message", e);
    }
  }
}
