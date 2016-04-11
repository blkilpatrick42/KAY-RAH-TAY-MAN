package com.gdx.game;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

/*
* CLASS DESCRIPTION: The purpose of this class is to create one instance of our Sprite Sheets 
* that we can constantly refer back to and not have to make a new instance
* every single time we want to create a sprite.
*/


public class SpriteSheet {
        private Texture entities; //Sprite sheet for Characters,Enemies, etc.
        private Texture background; //Sprite sheet for Background Elements
        
        public SpriteSheet()
        {
         entities = new Texture("KRTM_Entities_SpriteSheet.png");   
         //background = new Texture("KRTM_Background_SpriteSheet.png");
         //^ Havent made this yet since resolution is still being figured out
        }
         
       /*
        srcX - position of Sprite on the Sprite Sheet in X value (In Pixels, so * 32)
        srcY - position of Sprite on the Sprite Sheet in Y value (In Pixels, so * 32)
        srcWidth - Width of Sprite
        srcHeight - Height of Sprite
        */
        public Sprite getEntitySprite(int srcX, int srcY, int srcWidth, int srcHeight)
        {
          int scale = 2;  //Scale for our Entity Sprites
          Sprite temp = new Sprite(entities, srcX, srcY, srcWidth, srcHeight); //Creates a new Sprite
          temp.setBounds(0, 0, 32*scale, 32*scale); //Scales the Sprite up, along with its boundaries
          return temp;
        }
        /*
        Same as other method, except for Background Sprites/Tiles
        */
        public Sprite getBgSprite(int srcX, int srcY, int srcWidth, int srcHeight)
        {
          return new Sprite(background, srcX, srcY, srcWidth, srcHeight);  
        }
    
}
