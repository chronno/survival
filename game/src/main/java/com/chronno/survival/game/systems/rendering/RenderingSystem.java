package com.chronno.survival.game.systems.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.rendering.RendererComponent;
import com.esotericsoftware.minlog.Log;

public class RenderingSystem extends EntitySystem {


    private static final ComponentMapper<RendererComponent> RendererMapper = ComponentMapper.getFor(RendererComponent.class);

    private final OrthographicCamera camera;
    private final SpriteBatch spriteBatch;
        private ImmutableArray<Entity> entities;

    public RenderingSystem(int priority, OrthographicCamera camera) {
        super(priority);
        this.camera = camera;
        this.camera.zoom = 2;
        this.camera.setToOrtho(false);
        this.camera.translate(160,320);
        this.spriteBatch = new SpriteBatch();
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Rendering System was added");
        Family family = Family.all(RendererComponent.class).get();
        entities = engine.getEntitiesFor(family);

    }

    @Override
    public void removedFromEngine(Engine engine) {
        Log.info("Rendering System was removed");
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        entities.forEach(entity -> {
            RendererComponent rendererComponent = RendererMapper.get(entity);
            rendererComponent.draw(entity, spriteBatch, camera, deltaTime);
        });
        spriteBatch.begin();

        spriteBatch.end();
    }

}
