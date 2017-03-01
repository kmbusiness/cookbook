/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Ingretype;

/**
 *
 * @author Haba
 */
@Stateless
public class IngretypeFacade extends AbstractFacade<Ingretype> {

    @PersistenceContext(unitName = "CookBook_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngretypeFacade() {
        super(Ingretype.class);
    }
    
}
