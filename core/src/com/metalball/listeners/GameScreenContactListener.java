package com.metalball.listeners;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.metalball.screens.GameScreen;

public class GameScreenContactListener implements ContactListener {
    private GameScreen screen;
    public GameScreenContactListener(GameScreen screen){
        this.screen = screen;
    }
    private  boolean areCollided(Contact contact, Object userA, Object userB){
        return  (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB) ||
                contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
    }

    private Fixture getFixtureByKey(Contact contact, Object userData){
        if(contact.getFixtureA().getUserData().equals(userData)){
            return contact.getFixtureA();
        }
        if(contact.getFixtureB().getUserData().equals(userData)){
            return contact.getFixtureB();
        }
        return null;
    }

    @Override
    public void beginContact(Contact contact) {
        if (areCollided(contact, "player", "floor")){
            Fixture floor = getFixtureByKey(contact,"floor");
            PolygonShape floorShape =((PolygonShape)floor.getShape());
            float floorTopVertex=0;
            for(int i=0;i<floorShape.getVertexCount();i++){
                Vector2 vector = new Vector2();
                floorShape.getVertex(i,vector);
                if(vector.y>floorTopVertex){
                    floorTopVertex=vector.y;
                }
            }
            float y = (floorTopVertex*2)+(floor.getBody().getPosition().y)-0.5f-0.04f;
            if(y<screen.player.getBody().getPosition().y) {
                screen.player.getBody().setLinearVelocity(screen.player.getBody().getLinearVelocity().x, -0.5f * screen.player.getBody().getLinearVelocity().y);
            }
        }

        if (areCollided(contact, "player", "coin")){
            Fixture coinFixture = getFixtureByKey(contact,"coin");
            if(coinFixture!=null){
                screen.listToRemove.add(coinFixture.getBody());
                screen.gui.addCoin();
            }
            contact.setEnabled(false);
            screen.getCoinSound().play();
        }

        if (areCollided(contact, "player", "spike")){
            screen.getDieSound().play();
            if(screen.player.isAlive()) {
                screen.player.setAlive(false);
                System.out.println("GAME OVER");
                screen.getBgMusic().stop();
                screen.stage.addAction(Actions.sequence(
                        Actions.delay(1.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                screen.getGame().setScreen(screen.getGame().gameOverScreen);
                            }
                        })));
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
