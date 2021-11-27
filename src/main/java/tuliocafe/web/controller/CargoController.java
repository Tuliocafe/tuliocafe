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
import tuliocafe.service.CargoService;
import tuliocafe.service.EmpresaService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "/cargo/lista";
	}

	@PostMapping("/salvar")
	public String Salvar(Cargo cargo, RedirectAttributes attr) {
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo salvo com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("empresas")
	public List<Empresa> listaDeEmpresas(){
		return empresaService.buscarTodos();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "/cargo/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(Cargo cargo, RedirectAttributes attr) {
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo alterado com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(cargoService.cargoTemFuncionario(id)) {
			model.addAttribute("fail","cargo não removido Possui cargo(s) vinculado(s).");
			
		}else {
			cargoService.excluir(id);
			model.addAttribute("success","cargo excluido com sucesso");
		}
		return listar(model); 
	
	}
}
