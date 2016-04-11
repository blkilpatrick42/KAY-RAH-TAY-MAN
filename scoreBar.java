
package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
public class scoreBar {
    private Sprite localSprite;
    
    private Texture localTexture;
    
    public scoreBar(float inX, float inY){
        localTexture = new Texture("sideBar.png");
        localSprite = new Sprite(localTexture);
        localSprite.setX(inX);
        localSprite.setY(inY);
    }
    
    public Sprite getLocalSprite(){
        return localSprite;
    }
}
