package com.chronno.survival.game.services;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chronno.survival.game.model.WorldObject;
import com.esotericsoftware.minlog.Log;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.h2d.extention.spine.SpineItemType;

public class ProjectLoader {

    private final AssetManager assetManager;
    private final Viewport viewport;
    private final OrthographicCamera orthographicCamera;

    private SceneLoader sceneLoader;
    private AsyncResourceManager asyncResourceManager;
    private PooledEngine engine;
    private WorldObject worldObject;

    public ProjectLoader(Integer minWorldWidth, Integer minWorldHeight, String filename) {
        assetManager = new AssetManager();
        orthographicCamera = new OrthographicCamera();
        viewport = new ExtendViewport(minWorldWidth, minWorldHeight, orthographicCamera);
        assetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(assetManager.getFileHandleResolver()));
        assetManager.load(filename, AsyncResourceManager.class);
        assetManager.finishLoading();
        asyncResourceManager = assetManager.get(filename, AsyncResourceManager.class);

        sceneLoader = new SceneLoader(asyncResourceManager);
        engine = sceneLoader.getEngine();
        sceneLoader.injectExternalItemType(new SpineItemType());
    }

    public void init(String sceneName) {
        sceneLoader.loadScene("MainScene", viewport);
        worldObject = new WorldObject(sceneLoader.getRoot(), engine, this);
    }

    public void update() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();
        viewport.apply();
        try {
            engine.update(Gdx.graphics.getDeltaTime());
        } catch (Exception e) {
            e.printStackTrace();
            //engine.update(Gdx.graphics.getDeltaTime());
        }

    }

    public void dispose() {
        this.asyncResourceManager.dispose();
        this.sceneLoader.dispose();
        this.assetManager.dispose();
    }

    public WorldObject getRootItem() {
        return this.worldObject;
    }

    public AsyncResourceManager getResourceManager() {
        return this.asyncResourceManager;
    }
}
