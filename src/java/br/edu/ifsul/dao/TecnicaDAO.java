/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Tecnica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author dalpizzol
 */
@Stateful
public class TecnicaDAO<TIPO> extends DAOGenerico<Tecnica> implements Serializable {
    
    public TecnicaDAO() {
        super();
        
        classePersistente = Tecnica.class;
        ordem = "nome";
        maximoObjetos = 5;
    }
    
}
