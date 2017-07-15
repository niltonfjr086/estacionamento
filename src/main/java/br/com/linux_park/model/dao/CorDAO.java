package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.db.CorDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author n
 */
public class CorDAO extends GenericDAO<CorDB, Cor> {

    public CorDAO() {
        super(BaseDAO.BANCO[0], "tb_cor", "descricao", new CorDB(), new Cor());
    }

}
