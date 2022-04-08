package com.metalball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.metalball.persistence.UserProgress;
import com.metalball.screens.GameOverScreen;
import com.metalball.screens.GameScreen;
import com.metalball.screens.LevelSelectionScreen;
import com.metalball.screens.LoadingScreen;
import com.metalball.screens.MenuScreen;

public class MetalBallApp extends Game {

	private AssetManager manager;

	public GameScreen gameScreen;

    public GameOverScreen gameOverScreen;

	public MenuScreen menuScreen;

	public LevelSelectionScreen levelSelectionScreen;

	public LoadingScreen loadingScreen;

	public UserProgress userProgress;

	public AssetManager getManager() {
		return manager;
	}

	public UserProgress getUserProgress() {
		return userProgress;
	}

	public void setUserProgress(UserProgress userProgress) {
		this.userProgress = userProgress;
	}

	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("images/player/fox.png", Texture.class);
		manager.load("images/player/fox_1.png", Texture.class);
		manager.load("images/player/fox_2.png", Texture.class);
		manager.load("images/player/fox_3.png", Texture.class);
		manager.load("images/player/fox_4.png", Texture.class);
		manager.load("images/player/fox_speed.png", Texture.class);
		manager.load("images/player/fox_speed1.png", Texture.class);
		manager.load("images/player/fox_lr.png", Texture.class);
		manager.load("images/player/fox_lr1.png", Texture.class);
		manager.load("images/player/fox_lr2.png", Texture.class);
		manager.load("images/player/fox_stop.png", Texture.class);
		manager.load("images/player/fox_stop1.png", Texture.class);
		manager.load("images/spike.png", Texture.class);
		manager.load("images/floor/floor.png", Texture.class);
		manager.load("images/floor/floor_side.png", Texture.class);
		manager.load("images/floor/overfloor.png", Texture.class);
		manager.load("images/floor/overfloor_side.png", Texture.class);
		manager.load("images/floor/bottom_side.png", Texture.class);
		manager.load("images/gameover.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0000.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0001.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0002.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0003.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0004.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0005.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0006.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0007.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0008.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0009.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0010.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0011.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0012.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0013.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0014.png", Texture.class);
		manager.load("images/avioneta/Avioneta_Sprits0015.png", Texture.class);
		manager.load("images/clouds/Nuvol_1.png", Texture.class);
		manager.load("images/clouds/Nuvol_2.png", Texture.class);
		manager.load("images/clouds/Nuvol_3.png", Texture.class);
		manager.load("images/clouds/Nuvol_4.png", Texture.class);
		manager.load("images/coin/COIN0000.png", Texture.class);
		manager.load("images/coin/COIN0001.png", Texture.class);
		manager.load("images/coin/COIN0002.png", Texture.class);
		manager.load("images/coin/COIN0003.png", Texture.class);
		manager.load("images/coin/COIN0004.png", Texture.class);
		manager.load("images/coin/COIN0005.png", Texture.class);
		manager.load("images/coin/COIN0006.png", Texture.class);
		manager.load("images/coin/COIN0007.png", Texture.class);
		manager.load("images/coin/COIN0008.png", Texture.class);
		manager.load("images/coin/COIN0009.png", Texture.class);
		manager.load("images/coin/COIN0010.png", Texture.class);
		manager.load("images/background.jpg", Texture.class);

		manager.load("images/world/WorldBG.jpg", Texture.class);
		manager.load("images/world/Button_ON.png", Texture.class);
		manager.load("images/world/Button_OFF.png", Texture.class);

		manager.load("images/MenuAssets/BG/MenuLayout_BG.jpg", Texture.class);
		manager.load("images/MenuAssets/Logo/Logo.png", Texture.class);

		manager.load("audio/die.ogg", Sound.class);
		manager.load("audio/jump.ogg", Sound.class);
		manager.load("audio/warn.ogg", Sound.class);
		manager.load("audio/coin.wav", Sound.class);
		manager.load("audio/click.ogg", Sound.class);
		manager.load("audio/plane.mp3", Music.class);
		manager.load("audio/song.ogg", Music.class);
		manager.load("audio/menu_song.ogg", Music.class);
		manager.load("audio/LevelSelectionSong.ogg", Music.class);

		userProgress = loadUserProgress();

		manager.finishLoading();

		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	public void finishLoading(){
		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);
		menuScreen = new MenuScreen(this);
		levelSelectionScreen = new LevelSelectionScreen(this);
		setScreen(menuScreen);
	}

	private UserProgress loadUserProgress() {
		return new UserProgress();
	}

}
