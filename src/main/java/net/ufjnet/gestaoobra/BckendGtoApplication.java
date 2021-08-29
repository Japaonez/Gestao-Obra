package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.models.Lancamento;
import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Permission;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.models.SubItem;
import net.ufjnet.gestaoobra.models.User;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.repositories.LancamentoDAO;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.PermissionDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.repositories.SubItemDAO;
import net.ufjnet.gestaoobra.repositories.UserDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner{

	@Autowired
	private ProprietarioDAO propDAO;
	
	@Autowired
	private ObraDAO obraDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private SubItemDAO subitemDAO;
	
	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	private String r1, r2;
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("Senha criptografada: " + result);		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(16);
		r1 = bCrypt.encode("admin123");
		r2 = bCrypt.encode("user123");
		
		Proprietario p1 = new Proprietario(null, "Nome", "nome@gmail.com", "12345");
		Proprietario p2 = new Proprietario(null, "Larissa", "larissa@gmail.com", "67890");
		Proprietario p3 = new Proprietario(null, "Joao", "joao@gmail.com", "24680");
		
		Obra o1 = new Obra(null, "Descrição", "Localização", "Complemento", p1);
		Obra o2 = new Obra(null, "Sobrado com 4 suítes", "Rua Dona Olimpia, 1414, Vila Fátima", "", p2);
		Obra o3 = new Obra(null, "Casa geminada", "Rua 15, 1515, Setor Hermosa", "", p3);
		Obra o4 = new Obra(null, "Casa com 3 quartos", "Rua 16, 1616, Setor Brisas", "", p3);
		
		Item i1 = new Item(null, "Material Básico", "");
		Item i2 = new Item(null, "Material de Acabamento", "");
		Item i3 = new Item(null, "Material Pintura", "");
		Item i4 = new Item(null, "Mão-de-Obra", "");
		Item i5 = new Item(null, "Locações", "");
		
		SubItem si1 = new SubItem(null, "Fio Elétrico", "", i1);
		SubItem si2 = new SubItem(null, "Cimento", "", i1);
		SubItem si3 = new SubItem(null, "Tijolo", "", i1);
		
		SubItem si4 = new SubItem(null, "Argamassa", "", i2);
		SubItem si5 = new SubItem(null, "Porcelanato", "", i2);
		
		SubItem si6 = new SubItem(null, "Massa Corrida", "", i3);
		SubItem si7 = new SubItem(null, "Tinta", "", i3);
		
		SubItem si8 = new SubItem(null, "Pedreiro", "", i4);
		
		Lancamento l1 = new Lancamento(null, o1, i1, si1, 150.50, "Parte Elétrica", "", "");
		Lancamento l2 = new Lancamento(null, o1, i1, si2, 250.50, "Contra-Piso", "", "");
		Lancamento l3 = new Lancamento(null, o1, i2, si4, 250.50, "Piso", "", "");
		Lancamento l4 = new Lancamento(null, o2, i2, si5, 250.50, "Piso", "", "");
		Lancamento l5 = new Lancamento(null, o2, i3, si6, 250.50, "Contra-Piso", "", "");
		Lancamento l6 = new Lancamento(null, o3, i4, si8, 250.50, "Contra-Piso", "", "");
		Lancamento l7 = new Lancamento(null, o3, i4, si8, 250.50, "Contra-Piso", "", "");
		Lancamento l8 = new Lancamento(null, o4, i3, si7, 250.50, "Contra-Piso", "", "");
		Lancamento l9 = new Lancamento(null, o4, i4, si2, 250.50, "Contra-Piso", "", "");
		Lancamento l10 = new Lancamento(null, o4, i4, si8, 250.50, "Contra-Piso", "", "");
		
		User u1 = new User();
		u1.setUsername("Administrador");
		u1.setFullName("Jhonata");
		u1.setPassword(r1);
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);
		
		User u2 = new User();
		u2.setUsername("Usuario");
		u2.setFullName("Kumaki");
		u2.setPassword(r2);
		u2.setAccountNonExpired(true);
		u2.setAccountNonLocked(true);
		u2.setCredentialsNonExpired(true);
		u2.setEnabled(true);
		
		Permission pm1 = new Permission();
		pm1.setDescricao("ADMIN");
		
		Permission pm2 = new Permission();
		pm2.setDescricao("USUARIO");
		
		u1.getPermission().addAll(Arrays.asList(pm1, pm2));
		u2.getPermission().addAll(Arrays.asList(pm2));
		
		pm1.getUser().addAll(Arrays.asList(u1));
		pm2.getUser().addAll(Arrays.asList(u1, u2));
		
		propDAO.saveAll(Arrays.asList(p1, p2, p3));
		obraDAO.saveAll(Arrays.asList(o1, o2, o3, o4));
		itemDAO.saveAll(Arrays.asList(i1, i2, i3, i4, i5));
		subitemDAO.saveAll(Arrays.asList(si1, si2, si3, si4, si5, si6, si7, si8));
		lancamentoDAO.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10));
		userDAO.saveAll(Arrays.asList(u1, u2));
		permissionDAO.saveAll(Arrays.asList(pm1, pm2));
	}

}
