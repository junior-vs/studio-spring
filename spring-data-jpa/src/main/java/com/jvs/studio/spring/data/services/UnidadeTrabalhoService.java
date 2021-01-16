package com.jvs.studio.spring.data.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;
import com.jvs.studio.spring.data.repositories.UnidadeTrabalhoRepository;


@Service
public class UnidadeTrabalhoService {


  private boolean system = true;

  private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

  private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);

  public UnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
    super();
    this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
  }

  public void inicial() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (system) {
        log.info("Qual acao voce quer executar? ");
        log.info("0 - Sair");
        log.info("1 - Criar Unidade de Trabalho");
        log.info("2 - Atualizar Unidade de Trabalho");
        log.info("3 - Listar Unidade de Trabalho");
        log.info("4 - Buscar por ID UnidadeTrabalho");

        int action = Integer.parseInt(scanner.next());
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
          default:
            sair();
            break;
        }
      }
    } catch (Exception e) {
      log.error("message", e);
    }


  }

  private void buscarPorId() {
    try (Scanner scanner = new Scanner(System.in)) {
      log
          .info(
              "Qual o Unidade de Trabalho gostaria Visualizar, digite o id do Unidade de Trabalho");

      Integer id = scanner.nextInt();
      Optional<UnidadeTrabalho> find = unidadeTrabalhoRepository.findById(id);

      if (find.isEmpty()) {
        log.info("UnidadeTrabalho Não encontrado");

      } else {
        log.info("{}", find.get());
      }
    } catch (Exception e) {
      log.error("message", e);
    }
  }

  private void listar() {
    try (Scanner scanner = new Scanner(System.in)) {
      List<UnidadeTrabalho> findAll = unidadeTrabalhoRepository.findAll();

      for (UnidadeTrabalho f : findAll) {
        log.info("{}", f);
      }
    } catch (Exception e) {
      log.error("message", e);
    }

  }

  private void atualizar() {
    try (Scanner scanner = new Scanner(System.in)) {
      log.info("Qual o Funcionario que gostaria atualizar, digite o id do UnidadeTrabalho");
      List<UnidadeTrabalho> findAll = unidadeTrabalhoRepository.findAll();

      for (UnidadeTrabalho f : findAll) {
        log.info("{}", f);
      }
      Integer id = scanner.nextInt();

      UnidadeTrabalho novoUnidadeTrabalho = null;
      for (UnidadeTrabalho f : findAll) {
        if (f.getId().equals(id)) {
          novoUnidadeTrabalho = f;
          break;
        }
      }

      boolean systemIn = true;
      while (systemIn) {

        log.info("Qual o campo que gostaria de atualizar? ");
        log.info("0 - Finalizar");
        log.info("1 - Descricao, descricao atual: {}", novoUnidadeTrabalho.getDescricao());
        log.info("2 - Endereco, endereco atual: {}", novoUnidadeTrabalho.getEndereco());



        int action = scanner.nextInt();
        switch (action) {
          case 1:
            log.info("Digitar a descricao");
            String novodescricao = scanner.next();
            novoUnidadeTrabalho.setDescricao(novodescricao);
            break;

          case 2:
            log.info("Digitar o endereco");
            String novoendereco = scanner.next();
            novoUnidadeTrabalho.setEndereco(novoendereco);
            break;

          default:
            unidadeTrabalhoRepository.save(novoUnidadeTrabalho);
            log.info("UnidadeTrabalho salvo: {}", novoUnidadeTrabalho);
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
      log.info("Digitar a descricao");
      String nextString = scanner.nextLine();

      log.info("Digitar o endereco");
      String novoendereco = scanner.nextLine();

      UnidadeTrabalho novoFuncionario = new UnidadeTrabalho(nextString, novoendereco);
      unidadeTrabalhoRepository.save(novoFuncionario);

      log.info("UnidadeTrabalho salvo: {}", novoFuncionario);
    } catch (Exception e) {
      log.error("message", e);
    }
  }
}
