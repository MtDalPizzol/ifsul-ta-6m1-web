/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstudoDAO;
import br.edu.ifsul.dao.ExecucaoDAO;
import br.edu.ifsul.dao.ExercicioDAO;
import br.edu.ifsul.modelo.Estudo;
import br.edu.ifsul.modelo.Execucao;
import br.edu.ifsul.modelo.Exercicio;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dalpizzol
 */
@Named(value = "controleEstudo")
@SessionScoped
public class ControleEstudo implements Serializable {
    
    @EJB
    private EstudoDAO<Estudo> dao;
    private Estudo objeto;
    private Boolean editando;
    
    @EJB
    private ExercicioDAO<Exercicio> daoExercicio;
    
    @EJB
    private ExecucaoDAO<Execucao> daoExecucao;
    private Boolean editandoExecucao;
    private Execucao execucao;
    private Boolean novaExecucao;

    public ControleEstudo() {
        editando = false;
        editandoExecucao = false;
    }
    
    public String listar() {
        editando = false;
        editandoExecucao = false;
        return "/privado/estudo/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        objeto = new Estudo();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoExecucao = false;
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
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
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
            editandoExecucao = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }
   
    public void novaExecucao() {
        execucao = new Execucao();
        editandoExecucao = true;
        novaExecucao = true;
    }
    
    public void salvarExecucao() {
        if (novaExecucao) {
            objeto.adicionarExecucao(execucao);
        }
        editandoExecucao = false;
        novaExecucao = false;
        Util.mensagemInformacao("Execução adicionada com sucesso!");
    }
    
    public void alterarExecucao(int index) {
        execucao = objeto.getExecucoes().get(index);
        editandoExecucao = true;
        novaExecucao = false;
    }
    
    public void excluirExecucao(int index) {
        objeto.removerExecucao(index);
        Util.mensagemInformacao("Execução removida com sucesso!");
    }

    
    public EstudoDAO<Estudo> getDao() {
        return dao;
    }

    public void setDao(EstudoDAO<Estudo> dao) {
        this.dao = dao;
    }

    public Estudo getObjeto() {
        return objeto;
    }

    public void setObjeto(Estudo objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ExercicioDAO<Exercicio> getDaoExercicio() {
        return daoExercicio;
    }

    public void setDaoExercicio(ExercicioDAO<Exercicio> daoExercicio) {
        this.daoExercicio = daoExercicio;
    }

    public ExecucaoDAO<Execucao> getDaoExecucao() {
        return daoExecucao;
    }

    public void setDaoExecucao(ExecucaoDAO<Execucao> daoExecucao) {
        this.daoExecucao = daoExecucao;
    }

    public Boolean getEditandoExecucao() {
        return editandoExecucao;
    }

    public void setEditandoExecucao(Boolean editandoExecucao) {
        this.editandoExecucao = editandoExecucao;
    }

    public Execucao getExecucao() {
        return execucao;
    }

    public void setExecucao(Execucao execucao) {
        this.execucao = execucao;
    }

    public Boolean getNovaExecucao() {
        return novaExecucao;
    }

    public void setNovaExecucao(Boolean novaExecucao) {
        this.novaExecucao = novaExecucao;
    }
    
}
