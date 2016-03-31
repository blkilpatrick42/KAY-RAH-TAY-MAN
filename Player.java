package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.Vector3;

public class Player {
    
//    //boolean which determines
//    //if the player is facing right
    boolean isFacingRight; 

    //bounding box for 
    //all player attacks
    public BoundingBox attackBox;

    // sprite kept locally
    //to represent the player
    private Sprite localSprite;

    //The logical X and Y locations of the Player
    private float logicalX;
    private float logicalY;

    //The players total pixel shift in the y axis 
    //next frame
    private float ySpd = 0.0f;

    //The players total pixel shift in the X axis 
    //next frame
    private float xSpd = 0.0f;

    //Boolean to keep track if the player is
    //in the air
    private boolean inAir = false;

    //the number of successive jumps the player
    //may take after leaving the ground
    private int jumps = 4;

    //The rate at which friction is applied to the player.
    //ie how quickly xSpd is decremented
    float xFriction = 2.0f;
    
    private SpriteAnimate playerAnimate  = new SpriteAnimate(this);

    //The CONTSTRUCTOR for the Player object
    //@param the value to set logicalX upon
    //instantiation (int), the value to set
    //logicalY upon instantiation (int)
    public Player(int startX, int startY) {
        setLogicalX(startX);
        setLogicalY(startY);
        localSprite = new Sprite(playerAnimate.idle());
    }

    //accsessor for xSpd
    public float getXspd() {
        return xSpd;
    }

    //accsessor for ySpd
    public float getYspd() {
        return ySpd;
    }

    //accsessor for logicalX
    public float getX() {
        return logicalX;
    }

    //accsessor for logicalY
    public float getY() {
        return logicalY;
    }
    
    //accsessor for jumps
    public int getJumps(){
        return jumps;
    }
    
    //accsessor for localSprite
    public Sprite getLocalSprite(){
        return localSprite;
    }
    
    //MUTATOR for the local value logicalX
    //@param the value to set logicalX to (float)
    public void setLogicalX(float inX) {
        logicalX = inX;
    }

    //MUTATOR for the local value logicalY
    //@param the value to set logicalY to (float)
    public void setLogicalY(float inY) {
        logicalY = inY;
    }

    //MUTATOR for the local value xSpd
    //@param the value to set xSpd to (float)
    public void setXspd(float inXspd) {
        xSpd = inXspd;
    }

    //MUTATOR adds a value to local variable xSpd
    //@param the value to add to xSpd (float)
    public void addToXspd(float inXspd) {
        xSpd += inXspd;
    }

    //MUTATOR for the local value ySpd
    //@param the value to set ySpd to (float)
    public void setYspd(float inYspd) {
        ySpd = inYspd;
    }

    //MUTATOR adds a value to local variable ySpd
    //@param the value to add to ySpd (float)
    public void addToYspd(float inYspd) {
        ySpd += inYspd;
    }

    public void jump() {
        if (getJumps() > 0) {
            System.out.println(jumps);
            setYspd(10.0f);

            jumps--;

        }
    }
    
    public void punch(){
        
    }

    //MUTATOR updates the player location
    //and status
    public void update() {
        //update X speed
        if (getXspd() > 0) {
            addToXspd(-xFriction);
        } else if (getXspd() < 0) {
            addToXspd(xFriction);
        } else if (getXspd() < 0.10 && getXspd() > -0.10) {
            setXspd(0);
        }

        //update Y speed
        if (getY() > 0) {
            inAir = true;
            addToYspd(-0.5f);
            localSprite = playerAnimate.jump();
        }
        if (getY() <= 0) {
            if (getY() < 0) {
                setLogicalY(0);
            }
            if (inAir == true) {

                setYspd(0.0f);
                jumps = 4;
                inAir = false;
            }
            else
              localSprite = playerAnimate.idle();
            
        }
        
        //update x and y location
        setLogicalX(getX() + getXspd());
        setLogicalY(getY() + getYspd());
        localSprite.setX(getX());
        localSprite.setY(getY());
        
    }
    
    
    

}