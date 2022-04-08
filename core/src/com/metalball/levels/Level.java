package com.metalball.levels;

import com.badlogic.gdx.physics.box2d.World;
import com.metalball.MetalBallApp;
import com.metalball.entities.CoinEntity;
import com.metalball.entities.FloorEntity;
import com.metalball.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    protected MetalBallApp game;

    public World world;

    public List<FloorEntity> floorList = new ArrayList<>();

    public  List<SpikeEntity> spikeList = new ArrayList<>();

    public  List<CoinEntity> coinList = new ArrayList<>();

    public Level(MetalBallApp game, World world, List<FloorEntity> floorList, List<SpikeEntity> spikeList, List<CoinEntity> coinList) {
        this.game = game;
        this.world = world;
        this.floorList = floorList;
        this.spikeList = spikeList;
        this.coinList = coinList;
    }

    public abstract void buildLevel();
}
