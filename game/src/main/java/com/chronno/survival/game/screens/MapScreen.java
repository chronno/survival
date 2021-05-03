package com.chronno.survival.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.chronno.survival.game.components.character.ActionComponent;
import com.chronno.survival.game.components.character.DirectionComponent;
import com.chronno.survival.game.components.drawable.AnimationComponent;
import com.chronno.survival.game.components.drawable.PositionComponent;
import com.chronno.survival.game.components.drawable.SkeletonComponent;
import com.chronno.survival.game.components.drawable.TilemapComponent;
import com.chronno.survival.game.components.physics.PolygonComponent;
import com.chronno.survival.game.components.rendering.RendererComponent;
import com.chronno.survival.game.components.rendering.SpineAnimationRenderer;
import com.chronno.survival.game.components.rendering.TileMapRenderer;
import com.chronno.survival.game.components.type.EntityTypeComponent;
import com.chronno.survival.game.components.type.PlayerEntity;
import com.chronno.survival.game.systems.PhysicsSystem;
import com.chronno.survival.game.systems.player.Input;
import com.chronno.survival.game.systems.player.PlayerSystem;
import com.chronno.survival.game.systems.rendering.RenderingSystem;
import com.esotericsoftware.spine.SkeletonBounds;

public class MapScreen extends ScreenAdapter {

    private final PooledEngine engine;

    public MapScreen(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void show() {
        OrthographicCamera orthographicCamera = new OrthographicCamera(12, 8);
        World world = new World(new Vector2(0,0), true);

        Gdx.input.setInputProcessor(new Input(orthographicCamera));
        engine.addSystem(new RenderingSystem(0, orthographicCamera, world));
        engine.addSystem(new PlayerSystem(5));
        engine.addSystem(new PhysicsSystem(4, world));
        createMapEntity(world);
        createPlayerEntity(world);
    }

    private void createMapEntity(World world) {
        Entity mapEntity = engine.createEntity();
        TilemapComponent tilemapComponent = engine.createComponent(TilemapComponent.class);
        tilemapComponent.set("example.tmx");
        mapEntity.add(tilemapComponent);
        RendererComponent rendererComponent = engine.createComponent(RendererComponent.class);
        rendererComponent.set(new TileMapRenderer());
        mapEntity.add(rendererComponent);
        PolygonComponent polygonComponent = engine.createComponent(PolygonComponent.class);
        buildMapPolygons(tilemapComponent, polygonComponent, world);
        mapEntity.add(polygonComponent);
        engine.addEntity(mapEntity);
    }

    private void buildMapPolygons(TilemapComponent tilemapComponent, PolygonComponent polygonComponent, World world) {
        MapLayer objectsLayer = tilemapComponent.getMap().getLayers().get("col");
        objectsLayer.getObjects().forEach(mapObject -> {
            RectangleMapObject object  = (RectangleMapObject) mapObject;
            Rectangle rectangle = object.getRectangle();
            PolygonShape polygon = new PolygonShape();
            Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f), (rectangle.y + rectangle.height * 0.5f ));
            polygon.setAsBox(rectangle.width * 0.5f,rectangle.height * 0.5f, size, 0.0f);
            polygonComponent.addShape(polygon);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bd);
            body.createFixture(polygon, 1);
        });

    }

    private void createPlayerEntity(World world) {
        Entity characterEntity = engine.createEntity();
        SkeletonComponent skeletonComponent = engine.createComponent(SkeletonComponent.class);
        skeletonComponent.set("Male.atlas", "Male Character Down.json");
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.set(skeletonComponent.getSkeleton(), "Down Idle");
        characterEntity.add(animationComponent);
        characterEntity.add(skeletonComponent);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        characterEntity.add(positionComponent);
        skeletonComponent.getSkeleton().setX(positionComponent.getX());
        skeletonComponent.getSkeleton().setY(positionComponent.getY());
        characterEntity.add(engine.createComponent(ActionComponent.class));
        characterEntity.add(engine.createComponent(DirectionComponent.class));
        RendererComponent rendererComponent = engine.createComponent(RendererComponent.class);
        rendererComponent.set(new SpineAnimationRenderer());
        characterEntity.add(rendererComponent);
        EntityTypeComponent entityTypeComponent = engine.createComponent(EntityTypeComponent.class);
        entityTypeComponent.set(new PlayerEntity());
        characterEntity.add(entityTypeComponent);

        PolygonComponent polygonComponent = engine.createComponent(PolygonComponent.class);

        skeletonComponent.getSkeleton().updateWorldTransform();
        SkeletonBounds bounds = new SkeletonBounds();
        bounds.update(skeletonComponent.getSkeleton(), false);

        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((skeletonComponent.getSkeleton().getX() + bounds.getWidth() * 0.5f), (skeletonComponent.getSkeleton().getY() + bounds.getHeight() * 0.5f ));
        polygon.setAsBox(bounds.getWidth() * 0.5f,bounds.getHeight() * 0.5f, size, 0.0f);
        polygonComponent.addShape(polygon);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bd);
        body.createFixture(polygon, 1);

        engine.addEntity(characterEntity);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void dispose() {
        engine.removeAllEntities();
        engine.removeAllSystems();
        engine.clearPools();
    }
}
