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
        e.setId(o.getId());
        e.setPlaca(o.getPlaca());
        e.setId_cor(o.getCor().getId());
        e.setId_modelo(o.getModelo().getId());
        e.setData_inclusao(o.getDataInclusao());

        return e;
    }

    @Override
    public Veiculo fromDB(VeiculoDB o) {

        Veiculo e = new Veiculo();
        e.setId(o.getId());
        e.setPlaca(o.getPlaca());
        e.setCor(new CorDAO().getPorId(o.getId_cor()));
        e.setModelo(new ModeloDAO().getPorId(o.getId_modelo()));
        e.setMarca(e.getModelo().getMarca());
        e.setDataInclusao(o.getData_inclusao());

        return e;
    }

}
