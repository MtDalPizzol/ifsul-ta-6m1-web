/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estudo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author dalpizzol
 */
@Stateful
public class EstudoDAO<TIPO> extends DAOGenerico<Estudo> implements Serializable {
    
    public EstudoDAO() {
        super();
        
        classePersistente = Estudo.class;
        ordem = "id";
        maximoObjetos = 5;
    }
    
    public Estudo getObjectById(Integer id) throws Exception {
        Estudo obj = (Estudo) em.find(classePersistente, id);
        /*
        A linha abaixo realiza a inicialização da coleção para que 
        quando ela for editada na interface já esteja carregada, evitando assim 
        um erro de LazyInitializationException
        */
        obj.getExecucoes().size();
        return obj;
    }
    
}
