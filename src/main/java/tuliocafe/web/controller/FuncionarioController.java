package tuliocafe.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tuliocafe.domain.Cargo;
import tuliocafe.domain.Empresa;
import tuliocafe.domain.Funcionario;
import tuliocafe.domain.UF;
import tuliocafe.service.CargoService;
import tuliocafe.service.EmpresaService;
import tuliocafe.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String Salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionario cadastrado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs(){
		return UF.values();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "/funcionario/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "funcionario alterado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success","funcionario excluido com sucesso");
		return "redirect:/funcionarios/listar"; 
	
	}
	
}