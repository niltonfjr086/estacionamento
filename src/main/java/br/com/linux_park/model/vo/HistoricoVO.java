package br.com.linux_park.model.vo;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.bean.TipoVaga;
import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.dao.EstacionaDAO;
import br.com.linux_park.model.dao.TipoVagaDAO;
import br.com.linux_park.model.dao.VeiculoDAO;

/**
 *
 * @author Aluno
 */
public class HistoricoVO {

    public final EstacionaDAO eDAO = new EstacionaDAO();
    public final VeiculoDAO vDAO = new VeiculoDAO();
    public final TipoVagaDAO tDAO = new TipoVagaDAO();

    private Estaciona estaciona;
    private Veiculo veiculo;
    private TipoVaga tipoVaga;

    public Estaciona getEstaciona() {
        return estaciona;
    }

    public void setEstaciona(Estaciona estaciona) {
        this.estaciona = estaciona;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setVeiculo(String chaveCandidata) {
        this.veiculo = veiculo;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(TipoVaga tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

}
