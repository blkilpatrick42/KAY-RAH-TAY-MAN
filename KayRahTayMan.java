package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class KayRahTayMan extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Controller controller;
	BitmapFont font;
	boolean hasControllers = true;
	String message = "Please install a controller";

	@Override
	public void create() {
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture("badlogic.jpg"));
		sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		// checks if a controller is plugged in, if not makes the terminal tell
		// you so
		if (Controllers.getControllers().size == 0) {
			hasControllers = false;
		} else
			controller = Controllers.getControllers().first();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// if program failed controller check, program will tell you so every
		// frame draw.
		if (!hasControllers)
			System.out.println("Must connect a controller");
		else {
			// checks if left analog stick is more than 20% outside of center.
			// if so, the terminal tells you.
			if (controller.getAxis(XBoxOnePad.AXIS_LEFT_X) > 0.2f 
					|| controller.getAxis(XBoxOnePad.AXIS_LEFT_X) < -0.2f
					|| controller.getAxis(XBoxOnePad.AXIS_LEFT_Y) > 0.2f
					|| controller.getAxis(XBoxOnePad.AXIS_LEFT_Y) < -0.2f)
				System.out.println("You moved the left analog stick");
			// checks if right analog stick is more than 20% outside of center.
			// if so, the terminal tells you.
			if (controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) > 0.2f
					|| controller.getAxis(XBoxOnePad.AXIS_RIGHT_X) < -0.2f
					|| controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) > 0.2f
					|| controller.getAxis(XBoxOnePad.AXIS_RIGHT_Y) < -0.2f)
				System.out.println("You moved the right analog stick");

			// Poll if face button or start has been pressed. If so, terminal
			// tells you
			if (controller.getButton(XBoxOnePad.BUTTON_X))
				System.out.println("you pressed X!");
			if (controller.getButton(XBoxOnePad.BUTTON_Y))
				System.out.println("you pressed Y!");
			if (controller.getButton(XBoxOnePad.BUTTON_B))
				System.out.println("you pressed B!");
			if (controller.getButton(XBoxOnePad.BUTTON_A))
				System.out.println("you pressed A!");
			if (controller.getButton(XBoxOnePad.BUTTON_START))
				System.out.println("you pressed start!");
		}
		batch.end();
	}
}