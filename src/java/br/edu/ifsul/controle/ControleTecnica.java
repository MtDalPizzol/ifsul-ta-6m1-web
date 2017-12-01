/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.TecnicaDAO;
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
@Named(value = "controleTecnica")
@ViewScoped
public class ControleTecnica implements Serializable {
    
    @EJB
    private TecnicaDAO<Tecnica> dao;
    
    private Tecnica objeto;
    private Boolean editando;

    public ControleTecnica() {
    }

    public String listar(){
        editando = false;
        return "/privado/tecnica/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Tecnica();
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
    
    
    public TecnicaDAO<Tecnica> getDao() {
        return dao;
    }

    public void setDao(TecnicaDAO<Tecnica> dao) {
        this.dao = dao;
    }

    public Tecnica getObjeto() {
        return objeto;
    }

    public void setObjeto(Tecnica objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
}
