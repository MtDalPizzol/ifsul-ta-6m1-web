/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Exercicio;
import br.edu.ifsul.modelo.Tecnica;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author dalpizzol
 */
@Stateful
public class ExercicioDAO<TIPO> extends DAOGenerico<Exercicio> implements Serializable {

    private Tecnica filtroTecnica;

    public ExercicioDAO() {
        super();

        classePersistente = Exercicio.class;
        ordem = "nome";
        maximoObjetos = 5;
    }

    @Override
    public List<Exercicio> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");// remover caracteres para proteger de injeção de sql
        if (filtro.length() > 0) {
            if (ordem.equals("id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '%" + filtro.toUpperCase() + "%' ";
            }
        }
        if (filtroTecnica != null) {
            if (!(where.length() > 0)) {
                where = " where ";
            } else {
                where += " and ";
            }
            where += " tecnica = '" + filtroTecnica.getId() + "'";
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }

    public Tecnica getFiltroTecnica() {
        return filtroTecnica;
    }

    public void setFiltroTecnica(Tecnica filtroTecnica) {
        this.filtroTecnica = filtroTecnica;
    }

}
