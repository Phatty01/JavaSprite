/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.SpriteFacade;
import business.Sprite;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author tgk
 */
@ManagedBean(name = "SpriteView")
@RequestScoped
public class SpriteController {
    @EJB
    private SpriteFacade spriteFacade;
     // Creates a new field
    private Sprite sprite;
    /**
     * Creates a new instance of SpriteView
     */
    public SpriteController() {
       this.sprite = new Sprite();
    }



    // Calls getMessage to retrieve the message
    public Sprite getSprite() {
       return sprite;
    }

    // Returns the total number of messages
    public int getNumberOfSprites(){
       return spriteFacade.findAll().size();
    }

    // Saves the message and then returns the string "theend"
    public String postSprite(){
       this.spriteFacade.create(sprite);
       return "theend";
    }

}
