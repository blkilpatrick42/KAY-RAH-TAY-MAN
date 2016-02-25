

package com.gdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KayRahTayMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        BitmapFont font;
        Sprite redSquare;
        Controller controller;
        boolean hasControllers = true;
        
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

	@Override
	public void render () {
                //dunno exactly what this does
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                //POLL EVENTS
                  //Poll for A pressed to move left
                 if (Gdx.input.isKeyPressed(Input.Keys.A)){//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) < -0.2f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setXspd(0.0f);
                        } else {
                             player.setXspd(-5.0f);
                        }
                }
                  //Poll for D pressed to move right
                if (Gdx.input.isKeyPressed(Input.Keys.D)){//||controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setXspd(0.0f);
                        } else {
                             player.setXspd(5.0f);
                        }
                }
                  //Poll for Space pressed to jump
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){//controller.getButton(XBoxOnePad.BUTTON_A)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             player.setYspd(0.0f);
                        } else {
                        
                            player.jump();
                        
                        }
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
