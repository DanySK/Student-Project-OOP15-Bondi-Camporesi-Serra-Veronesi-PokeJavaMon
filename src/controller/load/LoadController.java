package controller.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import controller.parameters.XMLParameters;
import exceptions.SquadFullException;
import model.box.BoxImpl;
import model.inventory.InventoryImpl;
import model.map.PokeMap;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;
import view.resources.Play;

public class LoadController implements LoadControllerInterface {
    private final String FILE_NAME = System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Save" + File.separator + "save.xml";
    private final int MIN_MOVES = 1;
    private SAXBuilder builder;
    private Document document;
    private Element root;
    private static LoadController SINGLETON;
    
    private LoadController() {}
    
    public static LoadController getController() {
        if (SINGLETON == null) {
            synchronized (LoadController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new LoadController();
                }
            }
        }
        return SINGLETON;
    }
    
    private void setup() {
        builder = new SAXBuilder();
        try {
            document = builder.build(new File(FILE_NAME));
            root = document.getRootElement(); 
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void getMoney() {
        PlayerImpl.getPlayer().setMoney(Integer.parseInt(root.getAttributeValue(XMLParameters.MONEY.getName())));
    }
    
    private void getName() {
        PlayerImpl.getPlayer().setName(root.getAttribute(XMLParameters.NAME.getName()).getValue());
    }
    
    private void getBadges() {
        int badges = Integer.parseInt(root.getAttributeValue(XMLParameters.BADGES.getName()));
        for (int x = 0; x < 5; x ++) {
            if (x <= badges) {
                PlayerImpl.getPlayer().addBadge();
            }
        }
    }
    
    private void getPosition() {
        final int x = Integer.parseInt(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.X.getName()));
        final int y = Integer.parseInt(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.Y.getName()));
        PlayerImpl.getPlayer().setPosition(x, y);
    }
    
    private void getTeam() {
        for (Element e : root.getChild(XMLParameters.TEAM.getName()).getChildren()) {
            int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-1] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            try {
                PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
            } catch (SquadFullException e1) {
                e1.printStackTrace();
            }
        }     
    }
    
    private void getTrainers() {
        PokeMap map = Play.getMapImpl();
        Map<Integer, Boolean> trainer_isDefeated = new HashMap<>();
        for (Attribute a : root.getChild(XMLParameters.TRAINERS.getName()).getAttributes()) {
            try {
            	trainer_isDefeated.put(Integer.parseInt(a.getName().substring(1, a.getName().length())), a.getBooleanValue());
            } catch (DataConversionException e) {
                e.printStackTrace();
            }
        }
        map.initTrainers(trainer_isDefeated);
    }
    
    private void getInventory() {
        Map<String, Integer> potions = new HashMap<String, Integer>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.POTIONS.getName()).getAttributes()) {
            potions.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        Map<String, Integer> boosts = new HashMap<String, Integer>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BOOSTS.getName()).getAttributes()) {      
            boosts.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        Map<String, Integer> balls = new HashMap<String, Integer>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BALLS.getName()).getAttributes()) {      
            balls.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        InventoryImpl.initializeInventory(potions, boosts, balls);
    }

    private void getBox() {
        List<Pokemon> box = new ArrayList<Pokemon>();
        for (Element e : root.getChild(XMLParameters.BOX.getName()).getChildren()) {
            int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-1] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            box.add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
        }
        BoxImpl.getBox().setPokemons(box);
    }
    
    @Override
    public void load(final PokeMap map) {
        setup();
        getMoney();
        getPosition();
        getTeam();
        getTrainers();
        getName();
        getBadges();
        getInventory();
        getBox();
    }
    
    @Override
    public boolean saveExists() {
        return new File(FILE_NAME).exists();
    }
}