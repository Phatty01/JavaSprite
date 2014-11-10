/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author tgk
 */
@Stateful
public class SpriteSession implements SpriteSessionRemote {
public static final Random random = new Random();
    Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    @EJB
    private SpriteGame spriteGame;
     // Creates a new field
    private Sprite sprite;

    // Calls getMessage to retrieve the message
    public Sprite getSprite() {
       return sprite;
    }
		public List getSpriteList(){
			return spriteGame.getSpriteList();
		}
		//method for creating a new ball
		@Override
		public void newSprite (MouseEvent event){		
			spriteGame.newSprite(event, color);
		}
		public int getHeight(){
			return spriteGame.HEIGHT;
		}
		public int getWidth(){
			return spriteGame.WIDTH;
		}
}
