package test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import model.map.PokeMap;
import model.map.PokeMapImpl;

public class ImportMap implements Screen {

	@Override
	public void dispose() {
	
		
	}

	@Override
	public void hide() {
	
		
	}

	@Override
	public void pause() {
	
		
	}

	@Override
	public void render(float arg0) {
	
		
	}

	@Override
	public void resize(int arg0, int arg1) {
	
		
	}

	@Override
	public void resume() {
	
		
	}

	@Override
	public void show() {
	
		
	}

	
	public static void main(String[] args) {
		
		String mapURL = ImportMap.class.getResource("/map.tmx").getPath();
		System.out.println(mapURL);
		FileHandleResolver r = new InternalFileHandleResolver();
		TmxMapLoader tml = new TmxMapLoader(r);
		TiledMap map = tml.load("C:/Users/OPeratore/Desktop/map.tmx");
		map.getLayers().forEach(l -> System.out.println(l));
		final PokeMap m = new PokeMapImpl(map);
		System.out.println(m.getCollisions());
		
	}


}
