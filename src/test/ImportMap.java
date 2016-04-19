package test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


import model.map.PokeMap;
import model.map.PokeMapImpl;
import view.resources.Play;

public class ImportMap implements Screen {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
