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

public class KayRahTayMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        BitmapFont font;
        Controller controller;
        boolean hasControllers = true;
        boolean aJustPressed = false;
        
        int fps = 60;//Frames per Second
           
        //Declaration of the player object
        Player player;
        
        boolean canSpace = true;
        
        String concat;
	
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
                player = new Player(240,240);
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
                  //Poll for A pressed to move left
                 if (Gdx.input.isKeyPressed(Input.Keys.A)||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) < -0.2f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setXspd(0.0f);
                        } else {
                             player.setXspd(-10.0f);
                        }
                }
                  //Poll for D pressed to move right
                if (Gdx.input.isKeyPressed(Input.Keys.D)||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setXspd(0.0f);
                        } else {    
                             player.setXspd(10.0f);
                        }
                }
                  //Poll for Space pressed to jump
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setYspd(0.0f);
                        } else {
                        
                            player.jump();
                        
                        }
                }
                
                if (controller.getButton(XBoxOnePad.BUTTON_A))
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
                
                if (controller.getButton(XBoxOnePad.BUTTON_A) == false)
                {
                aJustPressed = false;
                }
                
                
                //END POLL EVENTS
                
		batch.begin();
                
                //draw the Player Sprite 
		player.getLocalSprite().draw(batch);
                
                
                
                //draw text "OK BUB" to the screen
                concat = "OK BUB"+player.getJumps();
                font.draw(batch, concat, 32, 440);
                
                player.update();
                
		batch.end();
	}
}