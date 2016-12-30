package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.entities.PlayerEntity;

/**
 * Created by luissancar on 30/12/16.
 */

public class GameScreen extends BaseScreen{
    private Stage stage;
    private World world;
    private PlayerEntity player;

    public GameScreen(MyGdxGame game) {
        super(game);
        stage =new Stage(new FitViewport(640,360));
        world=new World(new Vector2(0,-10), true);

    }

    @Override
    public void show() {
        Texture playerTexture=game.getManager().get("player.png");
        player=new PlayerEntity(world,playerTexture,new Vector2(1,2));
        stage.addActor(player);
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
