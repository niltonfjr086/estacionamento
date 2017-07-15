package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.TipoVeiculo;
import br.com.linux_park.model.db.TipoVeiculoDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author main
 */
public class TipoVeiculoDAO extends GenericDAO<TipoVeiculoDB, TipoVeiculo> {

    public TipoVeiculoDAO() {
        super(BaseDAO.BANCO[0], "tb_tipoveiculo", "descricao", new TipoVeiculoDB(), new TipoVeiculo());
    }

    @Override
    public TipoVeiculoDB toDB(TipoVeiculo o) {

        TipoVeiculoDB e = new TipoVeiculoDB();
        e.setId(o.getId());
        e.setDescricao(o.getDescricao());
        e.setId_tipo_vaga(o.getTipoVaga().getId());
        e.setData_inclusao(o.getData_inclusao());

        return e;
    }

    @Override
    public TipoVeiculo fromDB(TipoVeiculoDB o) {

        TipoVeiculo e = new TipoVeiculo();
        e.setId(o.getId());
        e.setDescricao(o.getDescricao());
        e.setTipoVaga(new TipoVagaDAO().getPorId(o.getId_tipo_vaga()));
        e.setData_inclusao(o.getData_inclusao());

        return e;
    }

}
