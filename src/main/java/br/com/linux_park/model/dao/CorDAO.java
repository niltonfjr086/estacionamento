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
        super(BaseDAO.BANCO[0], "tb_cor", "descricao", new CorDB(), new Cor(), new String[]{});
        //MASSA
    }

    @Override
    public CorDB toDB(Cor objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cor fromDB(CorDB o) {
        Cor c = new Cor();
        c.setId(o.getId());
//        e.setNome(o.getNome());
//        e.setUf(o.getUf());
//        e.setPais(new PaisDAO().getPorId(o.getPais()));
//        e.setRegiao(o.getRegiao());
//        e.setDtInclusao(o.getDtInclusao());

        return c;
    }

}
