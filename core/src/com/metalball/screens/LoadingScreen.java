package com.metalball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;

import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class LoadingScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Label loading;

    public LoadingScreen(MetalBallApp game) {
        super(game);

        stage = new Stage(new FillViewport(WORLD_WIDTH, WORLD_HEIGHT));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        loading = new Label("Loading...",skin);
        loading.setPosition(WORLD_WIDTH - loading.getWidth() /2, WORLD_HEIGHT - loading.getHeight() /2);
        stage.addActor(loading);
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        System.out.println("Loading... "+((int)(game.getManager().getProgress())*100)+"%");
        if(game.getManager().update()){
            System.out.println("Menu");
            game.setScreen(game.menuScreen);
            game.finishLoading();
        } else{
            System.out.println("Loading");
            loading.setText("Loading... "+((int)(game.getManager().getProgress())*100)+"%");
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
