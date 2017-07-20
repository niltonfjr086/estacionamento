package br.com.linux_park.model.vo;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.bean.Marca;
import br.com.linux_park.model.bean.Modelo;
import br.com.linux_park.model.bean.TipoVaga;
import br.com.linux_park.model.bean.TipoVeiculo;
import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.dao.CorDAO;
import br.com.linux_park.model.dao.EstacionaDAO;
import br.com.linux_park.model.dao.MarcaDAO;
import br.com.linux_park.model.dao.ModeloDAO;
import br.com.linux_park.model.dao.TipoVagaDAO;
import br.com.linux_park.model.dao.TipoVeiculoDAO;
import br.com.linux_park.model.dao.VeiculoDAO;

/**
 *
 * @author Aluno
 */
public class EntradaVO {

    public final EstacionaDAO estacionaDAO = new EstacionaDAO();
    public final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public final MarcaDAO marcaDAO = new MarcaDAO();
    public final ModeloDAO modeloDAO = new ModeloDAO();
    public final CorDAO corDAO = new CorDAO();
    public final TipoVeiculoDAO tipoVeiculoDAO = new TipoVeiculoDAO();
    public final TipoVagaDAO tipoVagaDAO = new TipoVagaDAO();

    private Estaciona estacionar;
    private Veiculo veiculo; //= new Veiculo();

    public Estaciona getEstacionar() {
        return estacionar;
    }

    public void setEstacionar(Estaciona estacionar) {
        this.estacionar = estacionar;
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

    public void verificaDadosIniciais() {

        if (tipoVagaDAO.listarTodos().size() <= 0) {
            TipoVaga[] tv = {
                new TipoVaga("2 eixos simples", 3.5f, 60), new TipoVaga("2 eixos médio", 5.5f, 40),
                new TipoVaga("2 eixos maior", 7.5f, 20), new TipoVaga("3 eixos", 15.5f, 5)
            };
            for (TipoVaga t : tv) {
                tipoVagaDAO.inserir(t);
            }
        }

        if (tipoVeiculoDAO.listarTodos().size() <= 0) {
            TipoVeiculo[] tv = {
                new TipoVeiculo("Moto", tipoVagaDAO.get("2 eixos simples")), new TipoVeiculo("Carro", tipoVagaDAO.get("2 eixos médio")),
                new TipoVeiculo("Caminhonete", tipoVagaDAO.get("2 eixos maior")), new TipoVeiculo("Caminhão", tipoVagaDAO.get("3 eixos"))
            };
            for (TipoVeiculo t : tv) {
                tipoVeiculoDAO.inserir(t);
            }
        }

        if (corDAO.listarTodos().size() <= 0) {
            Cor[] cc = {
                new Cor("BRANCA"), new Cor("PRETA"), new Cor("PRATA"), new Cor("VERMELHA"),
                new Cor("AMARELA"), new Cor("AZUL"), new Cor("VERDE"), new Cor("MARROM")
            };
            for (Cor c : cc) {
                corDAO.inserir(c);
            }
        }

        if (marcaDAO.listarTodos().size() <= 0) {
            Marca[] m = {
                new Marca("FIAT"), new Marca("Chevrolet"), new Marca("Ford"), new Marca("HONDA"), new Marca("Ferrari")
            };
            for (Marca t : m) {
                marcaDAO.inserir(t);
            }
        }

        if (modeloDAO.listarTodos().size() <= 0) {
            Modelo[] m = {
                new Modelo(tipoVeiculoDAO.get("Carro"), marcaDAO.get("FIAT"), "Uno"),
                new Modelo(tipoVeiculoDAO.get("Carro"), marcaDAO.get("Chevrolet"), "Cruze"),
                new Modelo(tipoVeiculoDAO.get("Carro"), marcaDAO.get("Ford"), "KA SEL 1.0 HA - 2015/2016"),
                new Modelo(tipoVeiculoDAO.get("Moto"), marcaDAO.get("HONDA"), "XR 250 TORNADO - 2003/2003"),
                new Modelo(tipoVeiculoDAO.get("Carro"), marcaDAO.get("Ferrari"), "F40")
            };
            for (Modelo t : m) {
                modeloDAO.inserir(t);
            }
        }

        if (veiculoDAO.listarTodos().size() <= 0) {
            Veiculo[] v = {
                new Veiculo("PSL-4977", corDAO.get("azul"), modeloDAO.get("KA SEL 1.0 HA - 2015/2016")),
                new Veiculo("DFD-4040", corDAO.get("vermelha"), modeloDAO.get("XR 250 TORNADO - 2003/2003"))
            };
            for (Veiculo t : v) {
                veiculoDAO.inserir(t);
            }
        }
        
        

//        System.out.println(estacionaDAO.getToleranciaDefault());
//        estacionaDAO.alterarDefaultTolerancia(80);
        System.out.println();
//        if (estacionaDAO.listarTodos().size() <= 0) {
//
//            Integer tolerancia = estacionaDAO.getToleranciaDefault();
//
//            Estaciona[] v = {
//                new Estaciona(veiculoDAO.get("PSL-4977"), tolerancia),
//                 new Estaciona(veiculoDAO.get("DFD-4040"), tolerancia)
//            };
//            for (Estaciona t : v) {
//                estacionaDAO.inserir(t);
//            }
//        }

    }

}
