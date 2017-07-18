
package br.com.linux_park.model.bo;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.dao.EstacionaDAO;
import br.com.linux_park.model.dao.VeiculoDAO;

/**
 *
 * @author Nilton
 */
public class EstacionaBO extends EstacionaDAO{
    
    VeiculoDAO vd = new VeiculoDAO();
    
    public Long registrar(Estaciona e){
        
        startTransaction();
        
        if(!existente(e.getVeiculo().getPlaca())){
            vd.inserir(e.getVeiculo());
        }
        
        
        
        
        
        
        validateTransaction();
        finishTransaction();
        return inserir(e);
        
        
    }
    
}
