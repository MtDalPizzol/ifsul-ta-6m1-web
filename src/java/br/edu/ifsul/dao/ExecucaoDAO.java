/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Execucao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author dalpizzol
 */
@Stateful
public class ExecucaoDAO<TIPO> extends DAOGenerico<Execucao> implements Serializable {
    
    public ExecucaoDAO() {
        super();
        
        classePersistente = Execucao.class;
        ordem = "inicio";
        maximoObjetos = 5;
    }
    
}
