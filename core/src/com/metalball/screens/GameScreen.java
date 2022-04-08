package com.metalball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.levels.backgrounds.Background;
import com.metalball.components.GameGUI;
import com.metalball.components.InitLevel;
import com.metalball.entities.CoinEntity;
import com.metalball.levels.Level;
import com.metalball.levels.Level1;
import com.metalball.listeners.GameScreenContactListener;
import com.metalball.MetalBallApp;
import com.metalball.entities.FloorEntity;
import com.metalball.entities.PlayerEntity;
import com.metalball.entities.SpikeEntity;
import com.metalball.util.State;

import java.util.ArrayList;
import java.util.List;

import static com.metalball.util.Constants.METERS_X_LEVEL;
import static com.metalball.util.Constants.PIXELS_IN_METER;
import static com.metalball.util.Constants.PLAYER_INIT_Y;
import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class GameScreen extends BaseScreen{

    private Box2DDebugRenderer renderer;

    private Matrix4 cameraBox2D;

    public Stage stage;

    public GameGUI gui;

    public InitLevel init;

    private Background background;

    private World world;

    public PlayerEntity player;

    private List<FloorEntity> floorList = new ArrayList<>();
    private  List<SpikeEntity> spikeList = new ArrayList<>();
    private  List<CoinEntity> coinList = new ArrayList<>();

    private Sound jumpSound, dieSound, coinSound, warnSound,clickSound;
    private Music bgMusic;

    /** Initial position of the camera. Required for reseting the viewport. */
    private Vector3 position;

    public List<Body> listToRemove;

    private InputMultiplexer multiplexer;

    private State state = State.INIT;

     public GameScreen(final MetalBallApp game) {
        super(game);
        stage = new Stage((new FillViewport(WORLD_WIDTH,WORLD_HEIGHT)));
        position = new Vector3(stage.getCamera().position);
        world = new World(new Vector2(0, -10), true);
        world.setContactListener(new GameScreenContactListener(this) {});
        jumpSound = game.getManager().get("audio/jump.ogg");
        dieSound = game.getManager().get("audio/die.ogg");
        coinSound = game.getManager().get("audio/coin.wav");
        bgMusic = game.getManager().get("audio/song.ogg");
        warnSound= game.getManager().get("audio/warn.ogg");
        clickSound = game.getManager().get("audio/click.ogg");
        listToRemove= new ArrayList<>();
        gui = new GameGUI(game,this);
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gui.stage);
        init = new InitLevel(game, this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);

        renderer = new Box2DDebugRenderer();
        cameraBox2D = stage.getCamera().combined.cpy();
        cameraBox2D.scl(PIXELS_IN_METER);

        background = new Background(game,this);
        gui.clearValues();

        Texture playerTexture = game.getManager().get("images/player/fox.png");
        Texture playerTexture1 = game.getManager().get("images/player/fox_1.png");
        Texture playerTexture2 = game.getManager().get("images/player/fox_2.png");
        Texture playerTexture3 = game.getManager().get("images/player/fox_3.png");
        Texture playerTexture4 = game.getManager().get("images/player/fox_4.png");
        Texture playerTextureSpeed = game.getManager().get("images/player/fox_speed.png");
        Texture playerTextureSpeed1 = game.getManager().get("images/player/fox_speed1.png");
        Texture playerTextureLR = game.getManager().get("images/player/fox_lr.png");
        Texture playerTextureLR1 = game.getManager().get("images/player/fox_lr1.png");
        Texture playerTextureLR2 = game.getManager().get("images/player/fox_lr2.png");
        Texture playerTextureStop = game.getManager().get("images/player/fox_stop.png");
        Texture playerTextureStop1 = game.getManager().get("images/player/fox_stop1.png");
        player = new PlayerEntity(world,
                playerTexture,playerTexture1,playerTexture2, playerTexture3, playerTexture4, playerTextureSpeed, playerTextureSpeed1,
                playerTextureLR, playerTextureLR1, playerTextureLR2, playerTextureStop, playerTextureStop1,
                new Vector2(10f,PLAYER_INIT_Y));

        Level level1 = new Level1(world, floorList, spikeList, coinList,  game);
        level1.buildLevel();

        stage.addActor(player);
        for (FloorEntity floor: floorList){
            stage.addActor(floor);
        }
        for (SpikeEntity spike: spikeList){
            stage.addActor(spike);
        }
        for (CoinEntity coin: coinList){
            stage.addActor(coin);
        }

        // Reset the camera to the left. This is required because we have translated the camera
        // during the game. We need to put the camera on the initial position so that you can
        // use it again if you replay the game.
        stage.getCamera().position.set(position);
        stage.getCamera().update();
        cameraBox2D = stage.getCamera().combined.cpy();
        cameraBox2D.scl(PIXELS_IN_METER);
    }

    @Override
    public void render(float delta) {
        switch (state) {
            case INIT:
                Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                init.updatePlane();

                init.stage.act();
                init.stage.draw();
                break;
            case RUN:
                Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                if (player.isAlive()) {
                    if(stage.getCamera().position.y+PLAYER_INIT_Y*PIXELS_IN_METER>-PIXELS_IN_METER*METERS_X_LEVEL+WORLD_HEIGHT) {
                        try {
                            stage.getCamera().translate(0, player.getSpeed().y * delta * PIXELS_IN_METER, 0);
                            background.update(stage.getCamera().position.y );
                            cameraBox2D = stage.getCamera().combined.cpy();
                            cameraBox2D.scl(PIXELS_IN_METER);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }


                if (!listToRemove.isEmpty()) {
                    for (int i = 0; i < coinList.size(); i++) {
                        try {//FIX ME
                            for (int j = 0; j < listToRemove.size(); j++) {
                                if (coinList.get(i).getBody().equals(listToRemove.get(j))) {
                                    listToRemove.remove(listToRemove.get(j));
                                    coinList.get(i).getBody().setActive(false);
                                    coinList.get(i).setVisible(false);
                                    coinList.get(i).detach();
                                    coinList.remove(i);
                                    i--;
                                    if (listToRemove.isEmpty())
                                        break;
                                }
                            }
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
                gui.updateAltitude(player.getY());
                boolean checkWin = gui.checkWin(player.getY());
                if(checkWin){
                    player.setWin(true);
                    setState(State.WIN);
                }

                stage.act();
                world.step(delta, 6, 2);
                //                renderer.render(world, cameraBox2D);
                stage.draw();
                //Set batch to now draw what the Hud camera sees.
                gui.stage.act();
                gui.stage.draw();
            break;
            case PAUSE: //do stuff here
                Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.act();
                stage.draw();
                gui.stage.act();
                gui.stage.draw();
            break;
            case WIN:
                Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.draw();
                gui.stage.act();
                gui.stage.draw();
            break;
            default:
            break;
        }

    }

    @Override
    public void pause() {
        this.state = State.PAUSE;
    }

    @Override public void resume() {
        bgMusic.play();
        this.state = State.RUN;
     }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        renderer.dispose();
        player.detach();
        bgMusic.stop();

        for (FloorEntity floor: floorList){
            floor.detach();
        }

        for (SpikeEntity spike: spikeList){
            spike.detach();
        }

        for (CoinEntity coin: coinList){
            coin.detach();
        }
        floorList.clear();
        spikeList.clear();
        coinList.clear();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        bgMusic.dispose();
        stage.dispose();
        gui.dispose();
        world.dispose();
        this.dispose();
    }

    public Sound getJumpSound() {
        return jumpSound;
    }

    public Sound getDieSound() {
        return dieSound;
    }

    public Sound getCoinSound() {
        return coinSound;
    }

    public Sound getWarnSound() {
        return warnSound;
    }

    public Sound getClickSound() {
        return clickSound;
    }

    public Music getBgMusic() {
        return bgMusic;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
