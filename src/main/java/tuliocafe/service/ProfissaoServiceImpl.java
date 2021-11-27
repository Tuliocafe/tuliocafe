package tuliocafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuliocafe.dao.ProfissaoDao;
import tuliocafe.domain.Profissao;

@Service @Transactional(readOnly = false)
public class ProfissaoServiceImpl implements ProfissaoService {

	@Autowired
	private ProfissaoDao dao;
	
	@Override
	public void salvar(Profissao profissao) {
		dao.save(profissao);
		
	}

	@Override
	public void editar(Profissao profissao) {
		dao.update(profissao);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override @Transactional(readOnly = true)
	public Profissao buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Profissao> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public boolean profissaoTemCliente(Long id) {
		
		if(buscarPorId(id).getClientes().isEmpty()) {
		return false;
		}
		return true;
	}
	

}
