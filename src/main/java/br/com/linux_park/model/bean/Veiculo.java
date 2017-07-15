package br.com.linux_park.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Veiculo {

    private Long id;
    private String placa;
    private Cor cor;
    private Modelo modelo;
    private Marca marca;
    private Date dataInclusao;

    public Veiculo() {
    }

    public Veiculo(String placa, Cor cor, Modelo modelo) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;
        this.marca = this.modelo.getMarca();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.placa);
        hash = 89 * hash + Objects.hashCode(this.cor);
        hash = 89 * hash + Objects.hashCode(this.modelo);
        hash = 89 * hash + Objects.hashCode(this.marca);
        hash = 89 * hash + Objects.hashCode(this.dataInclusao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "id=" + id + ", placa=" + placa + ", cor=" + cor + ", modelo=" + modelo + ", marca=" + marca + ", dataInclusao=" + dataInclusao + '}';
    }


    
    

}
