package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.TipoVaga;
import br.com.linux_park.model.db.TipoVagaDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author Aluno
 */
public class TipoVagaDAO extends GenericDAO<TipoVagaDB, TipoVaga> {

    public TipoVagaDAO() {
        super(BaseDAO.BANCO[0], "tb_tipovaga", "descricao", new TipoVagaDB(), new TipoVaga());
    }



}
