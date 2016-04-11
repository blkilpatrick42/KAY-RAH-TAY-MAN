package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;

public class Player {
    
    //boolean which determines
    //if the player is facing right
    //starts at false because the game begins
    //with him facing right
    public boolean isFacingRight = false; 

    
    public boolean isPunching = false;
    public boolean isKicking = false;
    public int kickingDirection = 0;
    
    //bounding box for 
    //all player attacks
    float boxOffset;
    public collisionBox attackBox = new collisionBox();
  // public Vector3 attackBoxA = new Vector3();  //the upper left point
   // public Vector3 attackBoxB = new Vector3();  //the lower right point
    public int attackBoxEx = 0;

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
    public boolean inAir = false;

    //the number of successive jumps the player
    //may take after leaving the ground
    private int jumps = 3;

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
    
    //flips the sprite horizontally (along the y axis)
    //from its current facing direction
    public void flipSprite(){
        playerAnimate.flipArrayH();
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
    
    //Method which preforms the
    //local computations for
    //a punch. This includes
    //moving the attackBox bounding
    //box
    //
    public void punch(ArrayList<rainDrop> dropArray){
       isPunching = true;
       isKicking = false;
        if(isFacingRight){
            boxOffset = 32.0f;
        }
        else{
            boxOffset = -10.0f;
        }
//        attackBoxA.set(logicalX, logicalY, 0);
//        attackBoxB.set(logicalX+50, logicalY+50, 0);
        attackBox.set(logicalX,logicalY,50);

        attackBoxEx++;
    }
    
    //Method which preforms the
    //local computations for
    //a punch. This includes
    //moving the attackBox bounding
    //box
    public void kick(int dir){
       isKicking = true;
       isPunching = false;
       kickingDirection = dir;
//        if(isFacingRight){
//            boxOffset = 64.0f;
//        }
//        else{
//            boxOffset = -32.0f;
//        }
//        attackBoxA.set(logicalX+boxOffset,logicalY,0);
//        attackBoxA.set(logicalX,logicalY-32.0f,0);
//        
//        
//        attackBox.set(attackBoxA,attackBoxB);
        
        //attackBox.
    }
    
    //method for resetting the local bounding
    //box attackBox
    public void resetAttackBox(){
        attackBox.clr();
    }
    
    //MUTATOR for isFacingRight
    //controls the logical
    //concept of facing direction
    public void faceRight(){
        isFacingRight = true;
    }
    
    //MUTATOR for isFacingRight
    //controls the logical
    //concept of facing direction
    public void faceLeft(){
        isFacingRight = false;
    }

    //MUTATOR updates the player location
    //and status
    public void update() {
        //code for removing the attackbox
        //after 1 frame
        if(attackBoxEx>4){
            attackBoxEx=0;
            resetAttackBox();
        }     
        else if(attackBoxEx>0){
            attackBoxEx++;
        }   


        
        if (getX() < 128){
            setLogicalX(128);
        }
        else if(getX()>582){
            setLogicalX(582);
        }
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
            if(isPunching==false&&isKicking==false){
            localSprite = playerAnimate.jump();
            }
            else if(isPunching == true){
                 localSprite = playerAnimate.punch();
                 isPunching=false;
            }
            else if(isKicking == true){
                 localSprite = playerAnimate.kick(kickingDirection);
                 isKicking=false;
            }
        }
        if (getY() <= 0) {
            if (getY() < 0) {
                setLogicalY(0);
            }
            if (inAir == true) {

                setYspd(0.0f);
                jumps = 3;
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