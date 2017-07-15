package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Modelo;
import br.com.linux_park.model.db.ModeloDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;

/**
 *
 * @author main
 */
public class ModeloDAO extends GenericDAO<ModeloDB, Modelo> {

    public ModeloDAO() {
        super(BaseDAO.BANCO[0], "tb_modelo", "descricao", "id_marca", "id_tipoveiculo", new ModeloDB(), new Modelo());
    }

    @Override
    public ModeloDB toDB(Modelo o) {

        ModeloDB e = new ModeloDB();
        e.setId(o.getId());
        e.setId_tipoveiculo(o.getTipoVeiculo().getId());
        e.setId_marca(o.getMarca().getId());
        e.setDescricao(o.getDescricao());
        e.setData_inclusao(o.getData_inclusao());

        return e;
    }

    @Override
    public Modelo fromDB(ModeloDB o) {

        Modelo e = new Modelo();
        e.setId(o.getId());
        e.setTipoVeiculo(new TipoVeiculoDAO().getPorId(o.getId_tipoveiculo()));
        e.setMarca(new MarcaDAO().getPorId(o.getId_marca()));
        e.setDescricao(o.getDescricao());
        e.setData_inclusao(o.getData_inclusao());

        return e;
    }

}
