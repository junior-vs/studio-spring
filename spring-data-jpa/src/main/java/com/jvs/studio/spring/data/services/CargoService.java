package com.jvs.studio.spring.data.services;

import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jvs.studio.spring.data.orm.model.Cargo;
import com.jvs.studio.spring.data.repositories.CargoRepository;

@Service
public class CargoService {

	private static final Logger log = LoggerFactory.getLogger(CargoService.class);

	private CargoRepository cargoRepository;
	private boolean system = true;

	public CargoService(CargoRepository cargoRepository) {
		super();
		this.cargoRepository = cargoRepository;
	}

	public void inicial() {
		try (Scanner scanner = new Scanner(System.in);) {
			while (system) {
				log.info("Qual acao voce quer executar? ");
				log.info("0 - Sair");
				log.info("1 - Criar Cargo");
				log.info("2 - Atualizar Cargo");
				log.info("3 - Listar Cargo");
				log.info("4 - Buscar por ID Cargo");

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
				default:
					sair();
					break;
				}
			}
		} catch (Exception e) {
			log.error("{}", e);
		}

	}

	private void buscarPorId() {
		try (Scanner scanner = new Scanner(System.in)) {
			log.error("Qual o Cagor que gostaria Visualizar, digite o id do Cargo");

			Integer id = scanner.nextInt();
			Optional<Cargo> find = cargoRepository.findById(id);

			if (find.isEmpty()) {
				log.error("Cargo Não encontrado");

			} else {
				log.info("{}", find.get());
			}
		} catch (Exception e) {
			log.error("message, {}", e.getLocalizedMessage());
		}
	}

	private void listar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> log.info("{}", cargo));
	}

	private void atualizar() {
		try (Scanner scanner = new Scanner(System.in)) {
			log.info("Qual o Cagor que gostaria atualizar, digite o id do Cargo");
			int id = scanner.nextInt();
			log.info("Descricao do Cargo");
			String descricao = scanner.next();

			Cargo cargo = new Cargo();
			cargo.setId(id);
			cargo.setDescricao(descricao);
			cargoRepository.save(cargo);
			log.info("Atualizado");

		} catch (Exception e) {
			log.error("message", e);

		}

	}

	private void sair() {
		this.system = false;
	}

	public void salvar() {
		try (Scanner scanner = new Scanner(System.in)) {

			log.info("Descricao do cargo");
			String descricao = scanner.next();
			Cargo cargo = new Cargo();
			cargo.setDescricao(descricao);
			cargoRepository.save(cargo);
			log.info("Salvo");

		} catch (Exception e) {
			log.error("message", e);

		}

	}

}
