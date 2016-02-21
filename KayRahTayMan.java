

package com.gdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
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
        float redSqrXspd = 0.0f;
        float xFriction = 0.10f;
        float redSqrYspd = 0.0f;
        boolean inAir = false;
        boolean canSpace = true;
        int jumps = 2; 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("redSquare.png");
                redSquare = new Sprite(img);
                font = new BitmapFont();
                font.setColor(Color.BLUE);
	}

	@Override
	public void render () {
                //dunno exactly what this does
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                //POLL EVENTS
                  //Poll for A pressed to move left
                 if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             redSqrXspd = 0.0f;
                        } else {
                             redSqrXspd = -5.0f;
                        }
                }
                  //Poll for D pressed to move right
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             redSqrXspd = 0.0f;
                        } else {
                             redSqrXspd = 5.0f;
                        }
                }
                  //Poll for Space pressed to jump
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    
                    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                             redSqrYspd = 0.0f;
                        } else {
                        
                            if(jumps > 0){
                             System.out.println(jumps);
                             redSqrYspd = 10.0f;
                             
                                jumps--;
                                
                            }
                        
                        }
                }
                
                
                //END POLL EVENTS
                
		batch.begin();
                
                //draw the Sprite 
		redSquare.draw(batch);
                
                //draw text "OK BUB" to the screen
                font.draw(batch, "OK BUB", 400, 200);
                
                //update X speed
                if(redSqrXspd > 0){
                    redSqrXspd -= 0.5;
                } else if (redSqrXspd < 0){
                    redSqrXspd += 0.5f;
                }
                else if (redSqrXspd < 0.5 && redSqrXspd > -0.5){
                    redSqrXspd = 0;
                }
                
                //update Y speed
                if(redSquare.getY() > 0){
                    inAir = true;
                    redSqrYspd -= 0.5f;
                }  
                if (redSquare.getY() <= 0){
                    if(redSquare.getY() < 0){
                        redSquare.setY(0.0f);
                    }
                    if(inAir == true){
                        
                        redSqrYspd = 0.0f;
                        jumps = 2;
                        inAir = false;
                    }
                }
                
                
                
                //update x and y location
                redSquare.translateX(redSqrXspd);
                redSquare.translateY(redSqrYspd);
		batch.end();
	}
}
