package com.chronno.survival.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chronno.survival.network.client.GameClient;
import com.esotericsoftware.minlog.Log;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import games.rednblack.h2d.extention.spine.SpineItemType;

public class SurvivalGame extends Game {

	private AssetManager assetManager;

	private SceneLoader sceneLoader;
	private AsyncResourceManager asyncResourceManager;

	private Viewport viewport;
	private OrthographicCamera orthographicCamera;

	private PooledEngine engine;

//	private final GameClient client;
	
	public SurvivalGame() {
		super();
//		this.client = new GameClient(new NetworkInterpreter());
//		this.client.connect("181.164.136.246", 25565, 25575);
	}


	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(assetManager.getFileHandleResolver()));
		assetManager.load("project.dt", AsyncResourceManager.class);

		assetManager.finishLoading();

		asyncResourceManager = assetManager.get("project.dt", AsyncResourceManager.class);
		sceneLoader = new SceneLoader(asyncResourceManager);
		sceneLoader.injectExternalItemType(new SpineItemType());
		engine = sceneLoader.getEngine();

		orthographicCamera = new OrthographicCamera();
		viewport = new ExtendViewport(1024,1024, orthographicCamera);
		sceneLoader.loadScene("MainScene", viewport);

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
		ItemWrapper player = root.getChild("animation");
		player.addScript(new Script(engine), engine);
	}

	@Override
	public void render() {
		orthographicCamera.update();

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		viewport.apply();
		engine.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		this.asyncResourceManager.dispose();
		this.sceneLoader.dispose();
		this.assetManager.dispose();
//		this.client.disconnect();
	}


}
