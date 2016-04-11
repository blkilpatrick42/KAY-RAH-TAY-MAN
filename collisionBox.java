
package com.gdx.game;

public class collisionBox {
    //  OY-----------------XY
    //  |                  |
    //  |                  | 
    //  |                  |
    //  |                  |
    //  |                  |
    //  OO-----------------XO
    //4 points making up the box
    //laid out in the above fashion
    //the bottom left corner represents
    //the point (0,0) in relation to
    //the box
    public cPoint OO;
    public cPoint XO;
    public cPoint XY;
    public cPoint OY;
    
    public cPoint pointArray[] = new cPoint[4];
    
    //the two floats representign the
    //bottom left corner of the box
    public float X;
    public float Y;
    
    //constructs a collisionBox that is a square
    //at the point inX and inY, with sides
    //of length inSize
    public collisionBox(){
        X = 0;
        Y = 0;
        OO = new cPoint (0,0);
        pointArray[0] = OO;
        XO = new cPoint (0,0);
        pointArray[1] = XO;
        OY= new cPoint(0,0);
        pointArray[2] = OY;
        XY= new cPoint(0,0);
        pointArray[3] = XY;
    }
    
    //constructs a collisionBox that is a square
    //at the point inX and inY, with sides
    //of length inSize
    public collisionBox(float inX, float inY, float inSize){
        X = inX;
        Y = inY;
        OO = new cPoint (inX,inY);
        pointArray[0] = OO;
        XO = new cPoint (inX+inSize,inY);
        pointArray[1] = XO;
        OY= new cPoint(inX,inY+inSize);
        pointArray[2] = OY;
        XY= new cPoint(inX+inSize,inY+inSize);
        pointArray[3] = XY;
    }
    
    //Resets the collisionBox to the given points
    //and size
    public void set(float inX, float inY, float inSize){
        X = inX;
        Y = inY;
        OO = new cPoint (inX,inY);
        pointArray[0] = OO;
        XO = new cPoint (inX+inSize,inY);
        pointArray[1] = XO;
        OY= new cPoint(inX,inY+inSize);
        pointArray[2] = OY;
        XY= new cPoint(inX+inSize,inY+inSize);
        pointArray[3] = XY;
    }
    
    //clears the collisionBox
    public void clr(){
        X = 0;
        Y = 0;
        OO = new cPoint (0,0);
        pointArray[0] = OO;
        XO = new cPoint (0,0);
        pointArray[1] = XO;
        OY= new cPoint(0,0);
        pointArray[2] = OY;
        XY= new cPoint(0,0);
        pointArray[3] = XY;
    }
    
    //method for determining intersection
    //returns true if it does intersect
    public boolean intersects(collisionBox B){
        boolean flag1 = false;
        boolean flag2 = false;
        for(int i = 0; i < 4; i++){
            //test Y
            if(B.pointArray[i].y>=OO.y&&B.pointArray[i].y<=OY.y){
                flag1 = true;
            }
            if(B.pointArray[i].x>=OO.x&&B.pointArray[i].x<=XO.x){
                flag2 = true;
            }
            if(flag1&&flag2){
                return true;
            }
            else{
                flag1 = false;
                flag2 = false;
            }
        }
        return false;
    }
}
