package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.db.EstacionaDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author Aluno
 */
public class EstacionaDAO extends GenericDAO<EstacionaDB, Estaciona> {

    public EstacionaDAO() {
        super(BaseDAO.BANCO[0], "tb_estaciona", "nome", new EstacionaDB(), new Estaciona());
    }

    @Override
    public EstacionaDB toDB(Estaciona o) {

        EstacionaDB e = new EstacionaDB();
        e.setId(o.getId());
        
//        e.setId(o.getId());
//        e.setNome(o.getNome());
//        e.setUf(o.getUf());
//        e.setPais(o.getPais().getId());
//        e.setRegiao(o.getRegiao());
//        e.setDtInclusao(o.getDtInclusao());
        
        

        return e;
    }

    @Override
    public Estaciona fromDB(EstacionaDB o) {

        Estaciona e = new Estaciona();
//        e.setId(o.getId());
//        e.setNome(o.getNome());
//        e.setUf(o.getUf());
//        e.setPais(new PaisDAO().getPorId(o.getPais()));
//        e.setRegiao(o.getRegiao());
//        e.setDtInclusao(o.getDtInclusao());

        return e;
    }
    
   

}
