/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ExercicioDAO;
import br.edu.ifsul.dao.TecnicaDAO;
import br.edu.ifsul.modelo.Exercicio;
import br.edu.ifsul.modelo.Tecnica;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dalpizzol
 */
@Named(value = "controleExercicio")
@ViewScoped
public class ControleExercicio implements Serializable {
    
    @EJB
    private ExercicioDAO<Exercicio> dao;
    
    private Exercicio objeto;
    private Boolean editando;
    
    @EJB
    private TecnicaDAO<Tecnica> daoTecnica;

    public ControleExercicio() {
    }

    public String listar(){
        editando = false;
        return "/privado/exercicio/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Exercicio();
    }
    
    public void alterar(Integer id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }
    
    
    public ExercicioDAO<Exercicio> getDao() {
        return dao;
    }

    public void setDao(ExercicioDAO<Exercicio> dao) {
        this.dao = dao;
    }

    public Exercicio getObjeto() {
        return objeto;
    }

    public void setObjeto(Exercicio objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public TecnicaDAO<Tecnica> getDaoTecnica() {
        return daoTecnica;
    }

    public void setDaoTecnica(TecnicaDAO<Tecnica> daoTecnica) {
        this.daoTecnica = daoTecnica;
    }
    
}
