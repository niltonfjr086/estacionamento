package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.Marca;
import br.com.linux_park.model.bean.Modelo;
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

        Cor cor = new CorDAO().getPorId(o.getId_cor());
        Modelo modelo = new ModeloDAO().getPorId(o.getId_modelo());
        Marca marca = modelo.getMarca();
        Veiculo veiculo = new Veiculo();

        veiculo.setId(o.getId());
        veiculo.setPlaca(o.getPlaca());
        veiculo.setCor(cor);
        veiculo.setModelo(modelo);
        veiculo.setMarca(marca);

        veiculo.setDataInclusao(o.getData_inclusao());
        return veiculo;
    }

}
