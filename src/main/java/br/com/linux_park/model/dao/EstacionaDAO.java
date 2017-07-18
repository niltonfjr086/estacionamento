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
        super(BaseDAO.BANCO[0], "tb_estaciona", "id_veiculo", new EstacionaDB(), new Estaciona());
        this.inicio = " data_entrada ";
        this.fim = " data_saida ";
    }

    @Override
    public EstacionaDB toDB(Estaciona o) {

        EstacionaDB e = new EstacionaDB();
        e.setId(o.getId());
        e.setId_veiculo(o.getVeiculo().getId());
        e.setValor_un(o.getValorHora());
        e.setPreco_total(o.getValorTotal());
        e.setData_entrada(o.getDataEntrada());
        e.setData_saida(o.getDataSaida());

        return e;
    }

    @Override
    public Estaciona fromDB(EstacionaDB o) {

        Estaciona e = new Estaciona();
        e.setId(o.getId());
        e.setVeiculo(new VeiculoDAO().getPorId(o.getId_veiculo()));
        e.setValorHora(o.getValor_un());
        e.setValorTotal(o.getPreco_total());
        e.setDataEntrada(o.getData_entrada());
        e.setDataSaida(o.getData_saida());

        return e;
    }

}
