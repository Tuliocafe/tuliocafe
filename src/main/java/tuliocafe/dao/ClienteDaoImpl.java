package tuliocafe.dao;

import org.springframework.stereotype.Repository;

import tuliocafe.domain.Cliente;

@Repository
public class ClienteDaoImpl extends AbstractDao<Cliente, Long> implements ClienteDao {

}
