package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner{

	@Autowired
	private ProprietarioDAO propDAO;
	
	@Autowired
	private ObraDAO obraDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Proprietario p1 = new Proprietario(null, "Nome", "nome@gmail.com", "12345");
		Proprietario p2 = new Proprietario(null, "Larissa", "larissa@gmail.com", "67890");
		Proprietario p3 = new Proprietario(null, "Joao", "joao@gmail.com", "24680");
		
//		Obra o1 = new Obra(null, "Descrição", "Localização", "Complemento", p1);
//		Obra o2 = new Obra(null, "Sobrado com 4 suítes", "Rua Dona Olimpia, 1414, Vila Fátima", "", p2);
//		Obra o3 = new Obra(null, "Casa geminada", "Rua 15, 1515, Setor Hermosa", "", p3);
//		Obra o4 = new Obra(null, "Casa com 3 quartos", "Rua 16, 1616, Setor Brisas", "", p3);
		
		propDAO.saveAll(Arrays.asList(p1, p2, p3));
//		obraDAO.saveAll(Arrays.asList(o1, o2, o3, o4));
	}

}
