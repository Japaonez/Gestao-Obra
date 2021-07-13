package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;

@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner{

	@Autowired
	private ProprietarioDAO propDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Proprietario p1 = new Proprietario(null, "Nome", "nome@gmail.com", "12345");
		Proprietario p2 = new Proprietario(null, "Larissa", "larissa@gmail.com", "67890");
		Proprietario p3 = new Proprietario(null, "Joao", "joao@gmail.com", "24680");
		
		propDAO.saveAll(Arrays.asList(p1, p2, p3));
	}

}
