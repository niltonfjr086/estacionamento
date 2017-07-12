package br.com.linux_park.model.db;

import java.util.Date;

/**
 *
 * @author N
 */
public class CorDB {

    private Long id;
    private String descricao;
    private Date data_inclusao;

    public CorDB() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(Date data_inclusao) {
        this.data_inclusao = data_inclusao;
    }

    @Override
    public String toString() {
        return "CorDB{" + "id=" + id + ", descricao=" + descricao + ", data_inclusao=" + data_inclusao + '}';
    }

}
