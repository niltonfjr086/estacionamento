package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.db.VeiculoDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

public class VeiculoDAO extends GenericDAO<VeiculoDB, Veiculo> {

    public VeiculoDAO() {
        super(BaseDAO.BANCO[0], "tb_veiculo", "placa", new VeiculoDB(), new Veiculo());
    }

    @Override
    public VeiculoDB toDB(Veiculo o) {

        VeiculoDB e = new VeiculoDB();
//        e.setId(o.getId());
//        e.setNome(o.getNome());
//        e.setUf(o.getUf());
//        e.setPais(o.getPais().getId());
//        e.setRegiao(o.getRegiao());
//        e.setDtInclusao(o.getDtInclusao());

        return e;
    }

    @Override
    public Veiculo fromDB(VeiculoDB o) {

        Veiculo e = new Veiculo();
//        e.setId(o.getId());
//        e.setNome(o.getNome());
//        e.setUf(o.getUf());
//        e.setPais(new PaisDAO().getPorId(o.getPais()));
//        e.setRegiao(o.getRegiao());
//        e.setDtInclusao(o.getDtInclusao());

        return e;
    }

}
