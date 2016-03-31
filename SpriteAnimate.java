
package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * CLASS DESCRIPTION: This class is used to grab a certain sprite from the 
 * SpriteSheet class and send it to be rendered depending on what animation
 * is being done and what frame of the animation it is on
 */
public class SpriteAnimate {
    private Sprite currSprite; //Current Sprite of animation
    private Sprite[] spriteArray; //Array of Sprites needed for specific entity
    private int f = 0; //Current frame of animation
    private int counter = 0; //Amount of frames gone through
    private static SpriteSheet sheet = new SpriteSheet();
    
    private boolean isIdleRunning = true;
    private boolean isJumping = false;
    
    public SpriteAnimate(Player player)
    {
      packSprites(player);    
    }
    
    private void packSprites(Object entity)
    {
        if( entity instanceof Player) //If the entity is of type Player
        {
          
          spriteArray = new Sprite[7]; //Fills the array with the amount of Sprites Player has
          for(int i = 0; i < spriteArray.length; i++)  
          {
            //Goes through each Sprite on the x plane for the Player
            spriteArray[i] = sheet.getEntitySprite(i*32, 0, 32, 32);
          }
        }
    }
    
    //Goes through Sprite array for idle Sprites
    public Sprite idle()
    {
     
     if(isJumping == true) //if another animation was running, stop it, reset counter
     {
       isJumping = false;
       isIdleRunning = true;
       counter = 0;
       f = 1;
       return spriteArray[5]; //This just makes it look like he's coming back down from a jump
     }
     counter++;
     if(counter%3 == 0)//Used to determine how many times it will stay on a certain frame
        f++;
      if(f == 1) 
      {
        return spriteArray[0];  
      }
      else if(f == 2)
      {
       return spriteArray[1];
      }
      else if(f == 3)
      {
       return spriteArray[2];
      }
      else if(f == 4)
      {
       return spriteArray[1];
      }
      else if(f == 5)
      {
       return spriteArray[0];
      }
      else if(f == 6)
      {
       return spriteArray[3];
      }
      else if(f == 7)
      {
       return spriteArray[4];
      }
      else if(f == 8)
      {
       f=1;
       counter = 0;
       return spriteArray[3];
      }

        return spriteArray[0];
    }
    //Goes through Sprite array for jump Sprites
    public Sprite jump()
    {
     if(isIdleRunning == true)//if another animation was running, stop it, reset counter
     {
       isIdleRunning = false;
       isJumping = true;
       counter = 0;
       f = 0;
     }
     counter++;
     if(counter%2 == 0)//Used to determine how many times it will stay on a certain frame
        f++;
     if(f==1)
     {
       return spriteArray[6];   
     }
     else if(f > 1)
     {
       f = 2;
       counter = 0;
       return spriteArray[6];
     }

     
       return spriteArray[0];
       
    }
}
