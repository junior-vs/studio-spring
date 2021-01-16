package com.jvs.studio.spring.data.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jvs.studio.spring.data.orm.model.Cargo;
import com.jvs.studio.spring.data.orm.model.Funcionario;
import com.jvs.studio.spring.data.repositories.CargoRepository;
import com.jvs.studio.spring.data.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

  private boolean system = true;

  private FuncionarioRepository funcionarioRepository;
  private CargoRepository cargoRepository;
  private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);


  public FuncionarioService(FuncionarioRepository funcionarioRepository,
      CargoRepository cargoRepository) {
    super();
    this.funcionarioRepository = funcionarioRepository;
    this.cargoRepository = cargoRepository;
  }

  public void inicial() {
    try (Scanner scanner = new Scanner(System.in)) {

      while (system) {
        log.info("Qual acao voce quer executar? ");
        log.info("0 - Sair");
        log.info("1 - Criar Funcionario");
        log.info("2 - Atualizar Funcionario");
        log.info("3 - Listar Funcionario");
        log.info("4 - Buscar por ID Funcionario");
        log.info("5 - Vincular um Funcionario à um Cargo");
        log.info("5 - Vincular um Funcionario à uma Unidade de Trabalho");

        int action = scanner.nextInt();
        switch (action) {
          case 1:
            salvar();
            break;
          case 2:
            atualizar();
            break;
          case 3:
            listar();
            break;
          case 4:
            buscarPorId();
            break;
          case 5:
            vincularCargo();
            break;
          default:
            sair();
            break;
        }
      }
    } catch (Exception e) {
      log.error("{}", e);
    }


  }

  private void vincularCargo() {

    try (Scanner scanner = new Scanner(System.in)) {


      log.info("Qual o Funcionario gostaria Visualizar, digite o id do Funcionario");

      Integer id = scanner.nextInt();
      Optional<Funcionario> find = funcionarioRepository.findById(id);

      System.out
          .println("Qual o Cago que gostaria fosse vinculado ao Funcionario, digite o id do Cargo");
      List<Cargo> findAll = cargoRepository.findAll();

      for (Cargo cargo : findAll) {
        System.out.println(cargo);
      }

      Integer idCargo = scanner.nextInt();
      Optional<Cargo> cargo = cargoRepository.findById(idCargo);

      find.get().setCargo(cargo.get());

      funcionarioRepository.save(find.get());
    } catch (Exception e) {
      log.error("message", e);
    }



  }

  private void buscarPorId() {
    try (Scanner scanner = new Scanner(System.in)) {
      log.info("Qual o Funcionario gostaria Visualizar, digite o id do Funcionario");

      Integer id = scanner.nextInt();
      Optional<Funcionario> find = funcionarioRepository.findById(id);

      if (find.isEmpty()) {
        log.info("Funcionario Não encontrado");

      } else {
        log.info("{}", find.get());
      }
    } catch (Exception e) {
      log.error("message", e);
    }
  }

  private void listar() {
    try (Scanner scanner = new Scanner(System.in)) {
      List<Funcionario> findAll = funcionarioRepository.findAll();

      for (Funcionario f : findAll) {
        log.info("{}", f);
      }
    } catch (Exception e) {
      log.error("message", e);
    }

  }

  private void atualizar() {
    try (Scanner scanner = new Scanner(System.in)) {
      log.info("Qual o Funcionario que gostaria atualizar, digite o id do Cargo");
      List<Funcionario> findAll = funcionarioRepository.findAll();

      for (Funcionario f : findAll) {
        log.info("{}", f);
      }
      Integer id = scanner.nextInt();

      Funcionario novoFuncionario = null;
      for (Funcionario f : findAll) {
        if (f.getId().equals(id)) {
          novoFuncionario = f;
          break;
        }
      }

      boolean systemIn = true;
      while (systemIn) {

        log.info("Qual o campo que gostaria de atualizar? ");
        log.info("0 - Finalizar");
        log.info("1 - Nome, nome atual: {}", novoFuncionario.getNome());
        log.info("2 - Cpf, Cpf atual: {}", novoFuncionario.getCpf());
        log.info("3 - Salario, Salario atual: {}", novoFuncionario.getSalario());
        log
            .info("4 - Data da Contratacao, Data da Contratacao atual: {}",
                novoFuncionario.getDtContratacao());


        int action = scanner.nextInt();
        switch (action) {
          case 1:
            log.info("Digitar o nome");
            String novoNome = scanner.next();
            novoFuncionario.setNome(novoNome);
            break;
          case 2:
            log.info("Digitar o Cpf");
            String novoCpf = scanner.next();
            novoFuncionario.setNome(novoCpf);
            break;
          case 3:
            log.info("Digitar o Salario (formato NN.nn)");
            BigDecimal decimal = scanner.nextBigDecimal();
            novoFuncionario.setSalario(decimal);
            break;
          case 4:
            log.info("Digitar a data (formato dd/mm/yyyy)");
            String data = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            novoFuncionario.setDtContratacao(LocalDate.parse(data, formatter));
            break;
          default:
            funcionarioRepository.save(novoFuncionario);
            log.info("Funcionario salvo: {}", novoFuncionario);
            systemIn = false;
            break;
        }
      }
    } catch (Exception e) {
      log.error("message", e);
    }
  }



  private void sair() {
    this.system = false;
  }

  public void salvar() {

    try (Scanner scanner = new Scanner(System.in)) {
      log.info("Digitar o nome");
      String novoNome = scanner.next();

      log.info("Digitar o Cpf");
      String novoCpf = scanner.next();

      log.info("Digitar o Salario (formato NN.nn)");
      BigDecimal decimal = scanner.nextBigDecimal();

      log.info("Digitar a data (formato dd/mm/yyyy)");
      String data = scanner.next();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate dataContracao = LocalDate.parse(data, formatter);
      
      log.info("Digite o cargoId");
      Integer cargoId = scanner.nextInt();

      Funcionario novoFuncionario = new Funcionario(novoNome, novoCpf, decimal, dataContracao);

      funcionarioRepository.save(novoFuncionario);
    } catch (Exception e) {
      log.error("message", e);
    }
  }

}
