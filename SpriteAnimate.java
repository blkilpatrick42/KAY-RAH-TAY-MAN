
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
    private boolean isPunching = false;
    private boolean isKicking = false;
    
    public SpriteAnimate(Player player)
    {
      packSprites(player);    
    }
    
    public Sprite getSprite(){
        return currSprite;
    }
    
    private void packSprites(Object entity)
    {
        if( entity instanceof Player) //If the entity is of type Player
        {
          
          spriteArray = new Sprite[17]; //Fills the array with the amount of Sprites Player has
          for(int i = 0; i < spriteArray.length; i++)  
          {
            //Goes through each Sprite on the x plane for the Player
            spriteArray[i] = sheet.getEntitySprite(i*32, 0, 32, 32);
          }
        }
    }
    
    //flips all the sprites in the spritearray
    //horizontally (across the y axis)
    public void flipArrayH(){
        
      for(int i=0;i<8;i++){
          spriteArray[i].flip(true, false);
      }    
    }
    
    //Goes through Sprite array for idle Sprites
    public Sprite idle()
    {
     
     if(isJumping == true||isPunching == true||isKicking == true) //if another animation was running, stop it, reset counter
     {
       isJumping = false;
       isPunching = false;
       isKicking = false;
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
     if(isIdleRunning == true||isPunching==true||isKicking==true)//if another animation was running, stop it, reset counter
     {
       isIdleRunning = false;
       isPunching = false;
       isJumping = true;
       isKicking = false;
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
    
    public Sprite punch()
    {
     if(isJumping == true||isKicking == true)//if another animation was running, stop it, reset counter
     {
       isIdleRunning = false;
       isJumping = false;
       counter = 0;
       f = 0;
     }

       return spriteArray[7];
       
    }
    
    public Sprite kick(int dir)
    {
     if(isJumping == true||isPunching == true)//if another animation was running, stop it, reset counter
     {
       isPunching=false;
       isIdleRunning = false;
       isJumping = false;
       isKicking=true;
       counter = 0;
       f = 0;
     }

       return spriteArray[8+dir];
       
    }
}
