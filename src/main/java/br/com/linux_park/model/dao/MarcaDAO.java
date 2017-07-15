package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Marca;
import br.com.linux_park.model.db.MarcaDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author main
 */
public class MarcaDAO extends GenericDAO<MarcaDB, Marca> {

    public MarcaDAO() {
        super(BaseDAO.BANCO[0], "tb_marca", "descricao", new MarcaDB(), new Marca());
    }

}
