package tuliocafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuliocafe.dao.EmpresaDao;
import tuliocafe.domain.Empresa;

@Service @Transactional(readOnly = false)
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDao dao;
	
	@Override
	public void salvar(Empresa empresa) {
		dao.save(empresa);
		
	}

	@Override
	public void editar(Empresa empresa) {
		dao.update(empresa);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override @Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
	
		return dao.findAll();
	}

	@Override
	public boolean empresaTemCargos(Long id) {
		if (buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
	return true;
	}



}
