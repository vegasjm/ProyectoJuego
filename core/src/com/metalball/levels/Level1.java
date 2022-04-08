package com.metalball.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.metalball.MetalBallApp;
import com.metalball.entities.CoinEntity;
import com.metalball.entities.FloorEntity;
import com.metalball.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends Level{
    public Level1(World world, List<FloorEntity> floorList, List<SpikeEntity> spikeList, List<CoinEntity> coinList, MetalBallApp game) {
        super(game, world, floorList, spikeList, coinList);
    }

    @Override
    public void buildLevel() {
        Texture floorTexture = game.getManager().get("images/floor/floor.png");
        Texture floorSideTexture = game.getManager().get("images/floor/floor_side.png");
        Texture overfloorTexture = game.getManager().get("images/floor/overfloor.png");
        Texture overfloorSideTexture = game.getManager().get("images/floor/overfloor_side.png");
        Texture bottomSideTexture = game.getManager().get("images/floor/bottom_side.png");

        Texture spikeTexture = game.getManager().get("images/spike.png");
        ArrayList<Texture> coinTextures=new ArrayList();
        for(int i=0; i<11; i++){
            Texture coinTexture = game.getManager().get("images/coin/COIN00"+String.format("%02d", i)+".png");
            coinTextures.add(coinTexture);
        }
        
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 12, 5, 3, -10));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 5, 1, -15));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 8, 5, 2, -25));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 1, 2, 4, -35));
        spikeList.add(new SpikeEntity(world, spikeTexture, 1.5f, -31f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 13, 5, 7, -45));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 5, 2, 2, -55));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 17, 5, 3, 3 -65));
        spikeList.add(new SpikeEntity(world, spikeTexture, 17.5f, -61f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 12, 5, 4, 4 -75f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 10, 5, 5, -85));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 20, 1, 1,-95));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 4, 5, 1, -105));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 11, 5, 2, -120f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 17, 1, 4, -130f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 14, 3, 3, -135f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 14.5f, -131f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 3, 5,14, -150f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 3, 2, 6,-160f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 15, 4, 2, -180f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 10, 1, 5, -200f));

        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 12, 5, 5, -210f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 10, 5, 5, -220f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 8, 5, 5,-225f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 8.5f, -221f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 1, 5, 4, -240f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 13, 5, 5,-250f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 5, 5, 5,-260f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 17, 5, 2,-270f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 12, 5, 1,-280f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 10, 5, 3,-290f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 20, 5, 4,-300f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 4, 5, 5,-310f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 13, 5, 6,-315f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 11.5f, -311f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 17, 3, 2, -330f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 14, 3, 2, -340f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 2, 3, 2, -350f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 3, 4, 2, -360f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 15, 3, 2, -380f));
        floorList.add(new FloorEntity(world, floorTexture, floorSideTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 10, 3, 2, -400f));

        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -410f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -420f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,8, 3,3, -425f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 8.5f, -421f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,1, 3,3, -440f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,13, 3,3, -450f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,5, 3,3, -460f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,17, 3,3, -470f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -480f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -485f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 10.5f, -481f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,20, 3,3, -500f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,4, 3,3, -510f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,11, 3,3, -520f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,17, 3,3, -530f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,14, 3,3, -540f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,2, 3,3, -545f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 2.5f, -541f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,3, 3,3, -560f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,15, 3,3, -580f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -600f));

        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -610f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -620f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,8, 3,3, -625f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 8.5f, -621f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,1, 3,3, -640f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,13, 3,3, -650f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,5, 3,3, -660f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,17, 3,3, -670f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -680f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -690f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,20, 3,3, -700f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,4, 3,3, -705f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 4.5f, -701f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,11, 3,3, -720f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,17, 3,3, -730f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,14, 3,3, -740f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,2, 3,3, -750f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,3, 3,3, -760f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,15, 3,3, -775f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 15.5f, -771f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -800f));

        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -810f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,10, 3,3, -820f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,15, 3,3, -830f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,6, 3,3, -840f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,5, 3,3, -845f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 5.5f, -841f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,4, 3,3, -860f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 3, 3,3, -870f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,2, 3,3, -880f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,16, 3,3, -890f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,18, 3,3, -900f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,9, 3,3, -910f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,12, 3,3, -915f));
        spikeList.add(new SpikeEntity(world, spikeTexture, 12.5f, -911f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,13, 3,3, -930f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,11, 3,3, -940f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,2, 3,3, -950f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,7, 3,3, -960f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture,3, 3,3, -980f));
        floorList.add(new FloorEntity(world, floorTexture, overfloorTexture, overfloorTexture, overfloorSideTexture, bottomSideTexture, 12, 3,3, -990f));

        coinList.add(new CoinEntity(world, coinTextures, 12, 0));
        coinList.add(new CoinEntity(world, coinTextures, 11, -1));
        coinList.add(new CoinEntity(world, coinTextures, 10, -2));
        coinList.add(new CoinEntity(world, coinTextures, 9, -3));
        coinList.add(new CoinEntity(world, coinTextures, 8, -4));

        coinList.add(new CoinEntity(world, coinTextures, 10, -50));
        coinList.add(new CoinEntity(world, coinTextures, 11, -50.5f));

        coinList.add(new CoinEntity(world, coinTextures, 12, -100));
        coinList.add(new CoinEntity(world, coinTextures, 11, -101));
        coinList.add(new CoinEntity(world, coinTextures, 10, -102));
        coinList.add(new CoinEntity(world, coinTextures, 9, -103));
        coinList.add(new CoinEntity(world, coinTextures, 8, -104));

        coinList.add(new CoinEntity(world, coinTextures, 12, -150));
        coinList.add(new CoinEntity(world, coinTextures, 11, -151));
        coinList.add(new CoinEntity(world, coinTextures, 10, -152));
        coinList.add(new CoinEntity(world, coinTextures, 9, -153));
        coinList.add(new CoinEntity(world, coinTextures, 8, -154));

        coinList.add(new CoinEntity(world, coinTextures, 8, -200));
        coinList.add(new CoinEntity(world, coinTextures, 8, -201));
        coinList.add(new CoinEntity(world, coinTextures, 8, -202));
        coinList.add(new CoinEntity(world, coinTextures, 8, -203));
        coinList.add(new CoinEntity(world, coinTextures, 8, -204));

        coinList.add(new CoinEntity(world, coinTextures, 15, -250));
        coinList.add(new CoinEntity(world, coinTextures, 16, -250));
        coinList.add(new CoinEntity(world, coinTextures, 17, -252));
        coinList.add(new CoinEntity(world, coinTextures, 18, -252));
        coinList.add(new CoinEntity(world, coinTextures, 19, -254));

        coinList.add(new CoinEntity(world, coinTextures, 12, -300));
        coinList.add(new CoinEntity(world, coinTextures, 11, -300.5f));

        coinList.add(new CoinEntity(world, coinTextures, 12, -350));
        coinList.add(new CoinEntity(world, coinTextures, 11, -351));
        coinList.add(new CoinEntity(world, coinTextures, 10, -352));
        coinList.add(new CoinEntity(world, coinTextures, 9, -353));
        coinList.add(new CoinEntity(world, coinTextures, 8, -354));

        coinList.add(new CoinEntity(world, coinTextures, 12, -400));
        coinList.add(new CoinEntity(world, coinTextures, 11, -401));
        coinList.add(new CoinEntity(world, coinTextures, 10, -402));
        coinList.add(new CoinEntity(world, coinTextures, 9, -403));
        coinList.add(new CoinEntity(world, coinTextures, 8, -404));

        coinList.add(new CoinEntity(world, coinTextures, 8, -450));
        coinList.add(new CoinEntity(world, coinTextures, 8, -451));
        coinList.add(new CoinEntity(world, coinTextures, 8, -452));
        coinList.add(new CoinEntity(world, coinTextures, 8, -453));
        coinList.add(new CoinEntity(world, coinTextures, 8, -454));

        coinList.add(new CoinEntity(world, coinTextures, 15, -500));
        coinList.add(new CoinEntity(world, coinTextures, 16, -501));
        coinList.add(new CoinEntity(world, coinTextures, 17, -502));
        coinList.add(new CoinEntity(world, coinTextures, 18, -503));
        coinList.add(new CoinEntity(world, coinTextures, 19, -504));

        coinList.add(new CoinEntity(world, coinTextures, 12, -550));
        coinList.add(new CoinEntity(world, coinTextures, 11, -551));
        coinList.add(new CoinEntity(world, coinTextures, 10, -552));
        coinList.add(new CoinEntity(world, coinTextures, 9, -553));
        coinList.add(new CoinEntity(world, coinTextures, 8, -554));

        coinList.add(new CoinEntity(world, coinTextures, 12, -600));
        coinList.add(new CoinEntity(world, coinTextures, 11, -601.5f));

        coinList.add(new CoinEntity(world, coinTextures, 12, -650));
        coinList.add(new CoinEntity(world, coinTextures, 11, -651));
        coinList.add(new CoinEntity(world, coinTextures, 10, -652));
        coinList.add(new CoinEntity(world, coinTextures, 9, -653));
        coinList.add(new CoinEntity(world, coinTextures, 8, -654));

        coinList.add(new CoinEntity(world, coinTextures, 12, -700));
        coinList.add(new CoinEntity(world, coinTextures, 11, -701));
        coinList.add(new CoinEntity(world, coinTextures, 10, -702));
        coinList.add(new CoinEntity(world, coinTextures, 9, -703));
        coinList.add(new CoinEntity(world, coinTextures, 8, -704));

        coinList.add(new CoinEntity(world, coinTextures, 8, -750));
        coinList.add(new CoinEntity(world, coinTextures, 8, -751));
        coinList.add(new CoinEntity(world, coinTextures, 8, -752));
        coinList.add(new CoinEntity(world, coinTextures, 8, -753));
        coinList.add(new CoinEntity(world, coinTextures, 8, -754));

        coinList.add(new CoinEntity(world, coinTextures, 15, -800));
        coinList.add(new CoinEntity(world, coinTextures, 16, -800));
        coinList.add(new CoinEntity(world, coinTextures, 17, -802));
        coinList.add(new CoinEntity(world, coinTextures, 18, -802));
        coinList.add(new CoinEntity(world, coinTextures, 19, -804));

        coinList.add(new CoinEntity(world, coinTextures, 12, -850));
        coinList.add(new CoinEntity(world, coinTextures, 11, -850));

        coinList.add(new CoinEntity(world, coinTextures, 12, -900));
        coinList.add(new CoinEntity(world, coinTextures, 11, -901));
        coinList.add(new CoinEntity(world, coinTextures, 10, -902));
        coinList.add(new CoinEntity(world, coinTextures, 9, -903));
        coinList.add(new CoinEntity(world, coinTextures, 8, -904));

        coinList.add(new CoinEntity(world, coinTextures, 12, -950));
        coinList.add(new CoinEntity(world, coinTextures, 11, -951));
        coinList.add(new CoinEntity(world, coinTextures, 10, -952));
        coinList.add(new CoinEntity(world, coinTextures, 9, -953));
        coinList.add(new CoinEntity(world, coinTextures, 8, -954));

        coinList.add(new CoinEntity(world, coinTextures, 8, -955));
        coinList.add(new CoinEntity(world, coinTextures, 8, -956));
        coinList.add(new CoinEntity(world, coinTextures, 8, -957));
        coinList.add(new CoinEntity(world, coinTextures, 8, -958));
        coinList.add(new CoinEntity(world, coinTextures, 8, -959));

        coinList.add(new CoinEntity(world, coinTextures, 15, -500));
        coinList.add(new CoinEntity(world, coinTextures, 16, -501));
        coinList.add(new CoinEntity(world, coinTextures, 17, -502));
        coinList.add(new CoinEntity(world, coinTextures, 18, -503));
        coinList.add(new CoinEntity(world, coinTextures, 19, -504));
    }
}
