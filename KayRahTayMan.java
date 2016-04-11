package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class KayRahTayMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        BitmapFont font;
        Controller controller;
        boolean hasControllers = true;
        boolean aJustPressed = false;
        boolean punchJustPressed = false;
        
        //arraylist of raindrops to be on the screen
        ArrayList<rainDrop> dropArray = new ArrayList();
        
        int fps = 50;//Frames per Second
           
        //Declaration of the player object
        Player player;
        
        //Declaration of the scoreBoard obj.
        scoreBar scoreBoard;
        
        boolean canSpace = true;
        
        String concat;
        String concat2;
        String concat3;
	
	@Override
	public void create () {
            
		batch = new SpriteBatch();
                font = new BitmapFont();
                font.setColor(Color.BLUE);
                // checks if a controller is plugged in, if not makes the terminal tell
		// you so
		if (Controllers.getControllers().size == 0) {
			hasControllers = false;
		} else {
			controller = Controllers.getControllers().first();
                }
                player = new Player(240,640);
                scoreBoard = new scoreBar(0,0);
                rainDrop drop1 = new rainDrop(240,240);
                rainDrop drop2 = new rainDrop(240,240);
                rainDrop drop3 = new rainDrop(240,240);
                rainDrop drop4 = new rainDrop(240,240);
                rainDrop drop5 = new rainDrop(240,240);
                
                dropArray.add(drop1);
                dropArray.add(drop2);
                dropArray.add(drop3);
                dropArray.add(drop4);
                dropArray.add(drop5);
	}
        
    private long diff, start = System.currentTimeMillis(); //gets current system time in Millisecs
    
    //Tick function, limits the program to the fps
    public void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                    } catch (InterruptedException e) {}
                }   
        start = System.currentTimeMillis();
        }
    }
       

	@Override
	public void render () {
                sleep(fps);
                //Inputs in this must be divided by /255f for red, blue, green, and alpha
		Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1); //sets background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
                
                //POLL EVENTS
                if(controller==null){
                    //Poll for A pressed to move left
                   if (Gdx.input.isKeyPressed(Input.Keys.A))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) < -0.2f) 
                   {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setXspd(0.0f);
                          } else {
                          if(player.isFacingRight){
                               player.flipSprite();
                               player.faceLeft();
                          }
                               player.setXspd(-10.0f);
                          }
                  }
                    //Poll for D pressed to move right

                  if (Gdx.input.isKeyPressed(Input.Keys.D))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setXspd(0.0f);
                          } else {
                          if(!player.isFacingRight){
                               player.flipSprite();
                               player.faceRight();
                          }
                               player.setXspd(10.0f);
                          }
                  }
                  
                   //Poll for p pressed to punch

                  if (Gdx.input.isKeyJustPressed(Input.Keys.P))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setXspd(0.0f);
                          } 
                      else {
                          
                               player.punch(dropArray);
                               
                              
                          }
                  }
                  
                  //KICKING POLLS/
 
                  
                  if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!Gdx.input.isKeyPressed(Input.Keys.UP)&&!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(0);
                  }
                  if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&Gdx.input.isKeyPressed(Input.Keys.UP)&&!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(1);
                  }
                  if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)&&Gdx.input.isKeyPressed(Input.Keys.UP)&&!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(2);
                  }
                  if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)&&Gdx.input.isKeyPressed(Input.Keys.UP)&&Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(3);
                  }
                  if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!Gdx.input.isKeyPressed(Input.Keys.UP)&&Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(4);
                  }
                  if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!Gdx.input.isKeyPressed(Input.Keys.UP)&&Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(5);
                  }
                  if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!Gdx.input.isKeyPressed(Input.Keys.UP)&&!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(6);
                  }
                  if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!Gdx.input.isKeyPressed(Input.Keys.UP)&&!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&Gdx.input.isKeyPressed(Input.Keys.DOWN))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(7);
                  }
                  
                    //Poll for Space pressed to jump
                  if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setYspd(0.0f);
                          } else {

                              player.jump();

                          }
                  }
                   
                }
                else{
                    //Poll for A pressed to move left
                   if (controller.getAxis(XBoxOnePad.AXIS_LEFT_X) < -0.2f) 
                   {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setXspd(0.0f);
                          } else {
                          if(player.isFacingRight){
                               player.flipSprite();
                               player.faceLeft();
                          }
                               player.setXspd(-10.0f);
                          }
                  }
                    //Poll for D pressed to move right

                  if (controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                               player.setXspd(0.0f);
                          } else {
                          if(!player.isFacingRight){
                               player.flipSprite();
                               player.faceRight();
                          }
                               player.setXspd(10.0f);
                          }
                  }
                  
                  //Poll for A pressed to punch

                  if (controller.getButton(XBoxOnePad.BUTTON_A))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {
                               
                                    player.punch(dropArray);
                                                                
                  }
                  
                  //KICK POLLS//
                  
                   if ((controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(0);
                  }
                  if ((controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(1);
                  }
                  if (!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(2);
                  }
                  if (!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(3);
                  }
                  if (!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(4);
                  }
                  if (!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(5);
                  }
                  if (!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(6);
                  }
                  if ((controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)&&!(controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f)&&(controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f))//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) 
                  {   
                               player.kick(7);
                  }
                  
                    //Poll for Controller trigger pressed to jump
                  if (controller.getButton(XBoxOnePad.AXIS_LEFT_TRIGGER))
                  {
                      if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) 
                      {
                               player.setYspd(0.0f);
                      } 
                      else 
                      {
                      if(aJustPressed == false)
                      {
                      player.jump();
                              aJustPressed = true;
                      }
                      }

                  }

                  if (controller.getButton(XBoxOnePad.AXIS_LEFT_TRIGGER) == false)
                  {
                  aJustPressed = false;
                  }
                }
                
                
                //END POLL EVENTS
                
		batch.begin();
                
                //draw the Player Sprite 
		player.getLocalSprite().draw(batch);
                
                //draw the raindrops
                for(int i =0; i<dropArray.size(); i++){
                    dropArray.get(i).getLocalSprite().draw(batch);
                }
               
                //draw the scoreboard
                scoreBoard.getLocalSprite().draw(batch);
                
                
                //draw text "OK BUB" to the screen
                concat = "JUMPS:  "+player.getJumps();
                concat2= "SCORE:  0";
                concat3="KAY-RAH-RAY: \nUNCHARGED";
                
                //TESTING GROUND COMMENT OUT EVERYTHING HERE
                System.out.println("X = "+player.attackBox.OO.x+"Y = "+player.attackBox.OO.y);
                System.out.println("AttackBoxEX= "+player.attackBoxEx);
                //TESTING END
                 
                font.draw(batch, concat, 28, 440);
                font.draw(batch, concat2, 28, 390);
                font.draw(batch, concat3, 16, 340);
                
                
                player.update();
                for(int i =0; i<dropArray.size(); i++){
                    dropArray.get(i).update(player);
                }
                
		batch.end();
	
        }
}