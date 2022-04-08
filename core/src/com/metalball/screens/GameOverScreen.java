package com.metalball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;

import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class GameOverScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Image gameover;

    private TextButton retry, menu;

    public GameOverScreen(final MetalBallApp game) {
        super(game);

        stage = new Stage(new FillViewport(WORLD_WIDTH, WORLD_HEIGHT));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        gameover = new Image(game.getManager().get("images/gameover.png", Texture.class));
        gameover.setSize(gameover.getWidth()*2,gameover.getHeight()*2);
        gameover.setPosition(WORLD_WIDTH/2 - gameover.getWidth() / 2, WORLD_HEIGHT/2 - gameover.getHeight() / 2);

        //Generate the font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Revamped.otf"));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Set the font size (12 as an example)
        param.size = 120;
        style.font = generator.generateFont(param);
        //Set the new style to the button

        retry = new TextButton("Retry", style);
        retry.setSize(510,240);
        retry.setPosition(20, 150);
        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.gameScreen.getBgMusic().play();
                game.setScreen(game.gameScreen);
            }
        });

        menu = new TextButton("Menu", style);
        menu.setSize(510, 240);
        menu.setPosition(550, 150);
        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });

        stage.addActor(retry);
        stage.addActor(menu);
        stage.addActor(gameover);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
