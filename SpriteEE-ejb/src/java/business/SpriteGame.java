/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author tgk
 */
@Stateless
@LocalBean
public class SpriteGame {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;

    List<Sprite> sprites;
    @EJB
    private SpriteFacade spriteFacade;

    public SpriteGame (){
        new Thread(new Runnable(){
            public void run(){
                go();
            }
        }).start();
    }
    public List getSpriteList() {
        if (sprites != null) {
            return sprites;
        }else{
            return null;
        }
    }
    //method for creating a new sprite
    public void newSprite(MouseEvent event, Color color) {
        Sprite sprite = new Sprite(HEIGHT, WIDTH, color);
        //Sprite sprite = new Sprite(HEIGHT, WIDTH);
        if (sprites != null) {
        //    synchronized (sprites) {
                sprites.add(sprite);
                spriteFacade.create(sprite);
        //    }
        }
        System.out.println("New sprite created");
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }
    //@PostConstruct
    public void go() {

		//retrieve the sprites from the database
        sprites = spriteFacade.findAll();

        while (true) {
            //move all the sprites and update them in the database
            //synchronized (sprites) {
                for (Sprite sprite : sprites) {
                    sprite.move();
                    spriteFacade.edit(sprite);
                }
            //}

            //sleep while waiting to display the next frame of the animation
            try {
                Thread.sleep(400);  // wake up roughly 25 frames per second
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
