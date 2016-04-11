
package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;
public class rainDrop {
   
    collisionBox hitBox = new collisionBox();
//    public Vector3 hitBoxA = new Vector3();  //the upper left point
//    public Vector3 hitBoxB = new Vector3();  //the lower right point
    
    Random RAND = new Random();
    private float logicalX;
    private float logicalY;
    
    private Sprite localSprite;
    
    private Texture localTexture;
    
    private float speed = 1;
    
    public rainDrop(float inX, float inY){
        localTexture = new Texture("rainDrop.png");
        localSprite = new Sprite(localTexture);
        logicalX = inX;
        logicalY = inY;
        localSprite.setX(logicalX);
        localSprite.setY(logicalY);
    }
    
    public Sprite getLocalSprite(){
        return localSprite;
    }
    
    public void update(Player PLAYER){
        
        
        logicalY -= speed;
        localSprite.setY(logicalY);
        localSprite.setX(logicalX);
//        hitBoxA.set(logicalX,logicalY,0);
//        hitBoxB.set(logicalX+8,logicalY+8,0);
        hitBox.set(logicalX,logicalY,8);
       if(hitBox.intersects(PLAYER.attackBox)){
            destroy();
        }
        if(logicalY < -8){
            destroy();
        }
    }
    
    public void destroy(){
        logicalX = RAND.nextInt(300)+128;
        logicalY = RAND.nextInt(100)+480;
    }
}
