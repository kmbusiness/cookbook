/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Ingredient;

/**
 *
 * @author Haba
 */
@Stateless
public class IngredientFacade extends AbstractFacade<Ingredient> {

    @PersistenceContext(unitName = "CookBook_v5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientFacade() {
        super(Ingredient.class);
    }
    
}
