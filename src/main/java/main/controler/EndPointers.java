package main.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import main.CalculaHoras;
import main.Tabela;
import main.controler.repository.AdministradorRepository;
import main.controler.repository.CargoRepository;
import main.controler.repository.FuncionarioRepository;
import main.controler.repository.PontoRepository;
import main.controler.repository.Ponto_tipo_pontoRepository;
import main.controler.repository.SetoresRepository;
import main.controler.repository.TipoPontoRepository;
import main.model.Administrador;
import main.model.Cargo;
import main.model.Funcionario;
import main.model.Ponto;
import main.model.Ponto_tipo_ponto;
import main.model.TipoPonto;

@RestController
public class EndPointers {
	@Autowired
	private AdministradorRepository admRepo;

	@Autowired
	private TipoPontoRepository tPontoRepo;

	@Autowired
	private FuncionarioRepository funcRepo;

	@Autowired
	private PontoRepository pontoRepo;

	@Autowired
	private Ponto_tipo_pontoRepository pontoTipoPontoRepo;

	@Autowired
	private CargoRepository cargoRepo;
	
	@Autowired
	private SetoresRepository setoresRepo;

	static Administrador user;
	ModelAndView paginaRetornada;

	@GetMapping("/voltaHome")
	public ModelAndView voltaHome() {
		return new ModelAndView("index.html");
	}

	@GetMapping("/voltaMenu")
	public ModelAndView voltaMenu() {
		return new ModelAndView("menu.html");
	}

	@GetMapping("/baterponto")
	public ModelAndView baterPonto(ModelMap model) {
		paginaRetornada = new ModelAndView("baterPonto.html");
		List<TipoPonto> tipoPontos = new ArrayList<TipoPonto>();
		tipoPontos = tPontoRepo.findAll();
		paginaRetornada.addObject("teste", tipoPontos);

		return paginaRetornada;
	}

	@GetMapping("/irpaginalogin")
	public ModelAndView irpaginaLogin() {
		return new ModelAndView("login.html");
	}

	@GetMapping("/logar")
	public ModelAndView logar(@RequestParam(name = "login") String login, @RequestParam(name = "senha") String senha) {
		Administrador adm = admRepo.findByLogin(login);
		if (adm.getSenha().equals(senha)) {
			user = adm;
			paginaRetornada = new ModelAndView("menu.html");
			paginaRetornada.addObject("user", adm);
		} else
			paginaRetornada = new ModelAndView("index.html");

		return paginaRetornada;
	}

	@PostMapping("/baterponto")
	public ModelAndView baterponto(@RequestParam(name = "cpf") String cpf, @RequestParam List<TipoPonto> tipoponto) {
		Funcionario func;
		paginaRetornada = new ModelAndView("index.html");
		if (funcRepo.existsById(cpf)) {
			String tipoRegistro;
			Optional<Funcionario> opFuncionario = funcRepo.findById(cpf);
			func = opFuncionario.get();
			long qtd = pontoRepo.countByfuncionariocpf(cpf);
			if (qtd % 2 == 0)
				tipoRegistro = "entrada";
			else
				tipoRegistro = "saida";
			Date d = new Date();
			Ponto ponto = new Ponto(tipoRegistro, func.getCpf(), d);
			pontoRepo.save(ponto);
			long max = pontoRepo.findMaxPonto();

			for (TipoPonto p : tipoponto) {
				Ponto_tipo_ponto ptp = new Ponto_tipo_ponto((int) max, p.getId());
				pontoTipoPontoRepo.save(ptp);
			}
			paginaRetornada.addObject("mensagem", "Ponto batido com sucesso");

		} else
			paginaRetornada.addObject("mensagem", "cpf invalido");

		return paginaRetornada;

	}

	@GetMapping("/cadastrarFuncionario")
	public ModelAndView cadastraFuncionario() {
		paginaRetornada = new ModelAndView("cadastroFuncionario.html");
		List<Cargo> cargos = cargoRepo.findAll();
		paginaRetornada.addObject("cargos", cargos);
		return paginaRetornada;
	}

	@PostMapping("/cadastraFuncionarioAcao")
	public ModelAndView cadastraFuncionarioAcao(@RequestParam(name = "cpf") String cpf,
			@RequestParam(name = "cargosSelect") Cargo cargo, @RequestParam String nome, @RequestParam String email,
			@RequestParam String telefone) {
		paginaRetornada = new ModelAndView("menu.html");
		paginaRetornada.addObject("user", user);
		funcRepo.save(new Funcionario(cpf, email, nome, telefone, cargo.getId()));
		return paginaRetornada;
	}

	@GetMapping("/cadastrarCargo")
	public ModelAndView cadastraCargo() {
		paginaRetornada = new ModelAndView("cadastroCargo.html");
		return paginaRetornada;
	}

	@PostMapping("/cadastrarCargoAcao")
	public ModelAndView cadastraCargoAcao(@RequestParam String descricao, @RequestParam String atribuicao) {
		paginaRetornada = new ModelAndView("menu.html");
		paginaRetornada.addObject("user", user);
		if (cargoRepo.findByAtribuicao(atribuicao) == null)
			cargoRepo.save(new Cargo(0, descricao, atribuicao));
		return paginaRetornada;
	}

	@GetMapping("/cadastrarTipoPonto")
	public ModelAndView cadastrarTipoPonto() {
		paginaRetornada = new ModelAndView("cadastroTipoPonto.html");
		return paginaRetornada;
	}

	@PostMapping("/cadastrarTipoPontoAcao")
	public ModelAndView cadastrarTipoPontoAcao(@RequestParam String descricao) {
		paginaRetornada = new ModelAndView("menu.html");
		paginaRetornada.addObject("user", user);

		tPontoRepo.save(new TipoPonto(0, descricao));
		return paginaRetornada;
	}

	@GetMapping("/editarFuncionario")
	public ModelAndView editarFuncionario() {
		paginaRetornada = new ModelAndView("editarFuncionario.html");
		List<Cargo> cargos = cargoRepo.findAll();
		paginaRetornada.addObject("cargos", cargos);
		List<Funcionario> funcionarios = funcRepo.findAll();
		paginaRetornada.addObject("funcionarios", funcionarios);

		return paginaRetornada;
	}

	@PostMapping("/editarFuncionarioAcao")
	public ModelAndView editarFuncionarioAcao(@RequestParam(name = "cpf") String cpf,
			@RequestParam(name = "cargosSelect") Cargo cargo, @RequestParam String nome, @RequestParam String email,
			@RequestParam String telefone, @RequestParam String login, @RequestParam String senha) {
		paginaRetornada = new ModelAndView("menu.html");
		paginaRetornada.addObject("user", user);
		funcRepo.save(new Funcionario(cpf, email, nome, telefone, cargo.getId()));
		admRepo.save(new Administrador(cpf, senha, login));
		return paginaRetornada;
	}

	@GetMapping("/verRelatorioIndividual")
	public ModelAndView verRelatorioIndividual() {
		paginaRetornada = new ModelAndView("verRelatorio.html");
		List<Funcionario> funcionarios = funcRepo.findAll();
		paginaRetornada.addObject("funcionarios", funcionarios);
		return paginaRetornada;
	}

	@GetMapping("/verTabela")
	public String tabela(@RequestParam(name = "cpf") String cpf) {
		Tabela tabela = new Tabela();

		return tabela.tabela(funcRepo.getById(cpf), pontoRepo.findAll());
	}

	@GetMapping("/geraGrafico")
	public String grafico(@RequestParam(name = "cpf") String cpf) {
		CalculaHoras ch = new CalculaHoras();
		return ch.calculahora(funcRepo.getById(cpf), pontoRepo.findAll());

	}

	@GetMapping("/geraDOC")
	public ModelAndView geraDOC(@RequestParam(name = "cpf") String cpf) {
		Tabela tabela = new Tabela();
		String xhtml = tabela.tabela(funcRepo.getById(cpf), pontoRepo.findAll());

		// Load HTML file using Document class
		Document document = null;
		try {

			FileWriter arq = new FileWriter("grafico.html");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(xhtml);
			arq.close();
			document = new Document("grafico.html");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Convert HTML file to Word DOCX format
		try {
			document.save("output.docx", SaveFormat.DOCX);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paginaRetornada = new ModelAndView("menu.html");
		return paginaRetornada;
	}

	@GetMapping("/geraXls")
	public ModelAndView geraXls(@RequestParam(name = "cpf") String cpf) {
		Tabela tabela = new Tabela();
		String xhtml = tabela.tabela(funcRepo.getById(cpf), pontoRepo.findAll());
		Workbook book = null;
		try {
			FileWriter arq = new FileWriter("grafico.html");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(xhtml);
			arq.close();
			book = new Workbook("grafico.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// save HTML as XLS
		try {
			book.save("output.xls", com.aspose.cells.SaveFormat.AUTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		paginaRetornada = new ModelAndView("menu.html");
		return paginaRetornada;
	}
	@GetMapping("/verRelatorioSetor")
	public ModelAndView verRelatorioSetor() {
		
		paginaRetornada = new ModelAndView("verRelatorioSetor.html");
		paginaRetornada.addObject("setores", setoresRepo.findAll());
		return paginaRetornada;
	}
}
