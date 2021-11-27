package tuliocafe.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tuliocafe.domain.Empresa;
import tuliocafe.service.EmpresaService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "/empresa/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("empresas", service.buscarTodos());
		return "/empresa/lista";
	}
	
	@PostMapping("/salvar")
	public String Salvar(Empresa empresa, RedirectAttributes attr) {
		service.salvar(empresa);
		attr.addFlashAttribute("success", "Empresa salvo com sucesso");
		return "redirect:/empresas/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("empresa", service.buscarPorId(id));
		return "/empresa/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(Empresa empresa, RedirectAttributes attr) {
		service.editar(empresa);
		attr.addFlashAttribute("success", "Empresa alterado com sucesso");
		return "redirect:/empresas/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(service.empresaTemCargos(id)) {
			model.addAttribute("fail","Empresa não removido Possui cargo(s) vinculado(s).");
			
		}else {
			service.excluir(id);
			model.addAttribute("success","Empresa excluido com sucesso");
		}
		return listar(model); 
	}
}
