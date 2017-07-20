package br.com.linux_park.model.dao;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.db.EstacionaDB;
import br.com.linux_park.util.BaseDAO;
import br.com.linux_park.util.GenericDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class EstacionaDAO extends GenericDAO<EstacionaDB, Estaciona> {

    public EstacionaDAO() {
        super(BaseDAO.BANCO[0], "tb_estaciona", "id_veiculo", new EstacionaDB(), new Estaciona());
        this.inicio = "data_entrada";
        this.fim = "data_saida";
    }

    @Override
    public EstacionaDB toDB(Estaciona o) {

        EstacionaDB e = new EstacionaDB();
        e.setId(o.getId());
        e.setId_veiculo(o.getVeiculo().getId());
        e.setValor_un(o.getValorHora());
        if (o.getTolerancia() != null) {
            e.setTolerancia(o.getTolerancia());
        }
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
        e.setTolerancia(o.getTolerancia());
        e.setValorTotal(o.getPreco_total());
        e.setDataEntrada(o.getData_entrada());
        e.setDataSaida(o.getData_saida());

        return e;
    }

    public Boolean alterarDefaultTolerancia(Integer tolerancia) {

        String sql = " ALTER TABLE linuxpark.tb_estaciona "
                + " MODIFY " + this.banco + "." + this.tabela + "." + "tolerancia" + " int default ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, tolerancia.toString());

            if (stmt.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return false;
    }

    public Integer getToleranciaDefault() {

        String sql = " SELECT DEFAULT(linuxpark.tb_estaciona.tolerancia) "
                + " FROM linuxpark.tb_estaciona "
                + " order by linuxpark.tb_estaciona.tolerancia desc LIMIT 1 ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            if (stmt.execute()) {

                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    Integer i = rs.getInt(1);
                    if (i >= 0) {
                        return i;

                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return 15;
    }

    public Float calculaValorTotal(Estaciona e) {
        Float valorPagar = 0.00f;
        Long tempo;

        if (e.getDataSaida() == null) {
            tempo = new Date().getTime() - e.getDataEntrada().getTime();
        } else {
            tempo = e.getDataSaida().getTime() - e.getDataEntrada().getTime();
        }

        Long emMinutos = tempo / 1000 / 60;
        Long emHoras = emMinutos / 60;

        if (emMinutos >= e.getTolerancia()) {
            ++emHoras;
            valorPagar = Float.parseFloat(emHoras.toString()) * e.getValorHora();
        }
        return valorPagar;
    }

//    @Override
    public Boolean arquivar(Long id) { //excluir
        if (id == null) {
            return false;
        }

        try (PreparedStatement stmt = con.prepareStatement(sqlDelete())) {
            stmt.setObject(1, id);

            if (stmt.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return false;
    }

}
