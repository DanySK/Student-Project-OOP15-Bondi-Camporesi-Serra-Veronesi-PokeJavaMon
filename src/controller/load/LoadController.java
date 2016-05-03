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

import controller.parameters.FilePath;
import controller.parameters.XMLParameters;
import exceptions.SquadFullException;
import model.box.BoxImpl;
import model.inventory.InventoryImpl;
import model.map.PokeMap;
import model.map.Position;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;
import view.resources.Play;
import view.sprite.PlayerSprite;

/**
 * This class loads all the requested informations. 
 */
public final class LoadController implements LoadControllerInterface {
    private final String FILE_NAME = FilePath.SAVE.getAbsolutePath() + File.separator + "save.xml";
    private static final int MIN_MOVES = 1;
    private Element root;
    
    /**
     * Loads the save file
     */
    private void setup() {
        final SAXBuilder builder = new SAXBuilder();
        try {
            final Document document = builder.build(new File(FILE_NAME));
            root = document.getRootElement(); 
        } catch (JDOMException e) {
            System.out.println("FAILED LOADING SAVE");
        } catch (IOException e) {
            System.out.println("FAILED LOADING SAVE");
        }
    }
    
    /**
     * Loads the money value from the save file
     */
    private void getMoney() {
        PlayerImpl.getPlayer().setMoney(Integer.parseInt(root.getAttributeValue(XMLParameters.MONEY.getName())));
    }
    
    /**
     * Loads player's name value from the save file
     */
    private void getName() {
        PlayerImpl.getPlayer().setName(root.getAttribute(XMLParameters.NAME.getName()).getValue());
    }
    
    /**
     * Loads the badges value from the save file
     */
    private void getBadges() {
        final int badges = Integer.parseInt(root.getAttributeValue(XMLParameters.BADGES.getName()));
        for (int x = 0; x < 5; x ++) {
            if (x < badges) {
                PlayerImpl.getPlayer().addBadge();
            }
        }
    }
    
    /**
     * Loads player's position value from the save file
     */
    private void getPosition() {
        final int x = Integer.parseInt(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.X.getName()));
        final int y = Integer.parseInt(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.Y.getName()));
        PlayerImpl.getPlayer().setPosition(x, y);
        PlayerSprite.getSprite().setBounds(x * 16, (299-y) * 16, 15.9f, 15.9f);
        System.out.println("savePos : " + new Position(PlayerImpl.getPlayer().getTileX(),PlayerImpl.getPlayer().getTileY()));
        
    }
    
    /**
     * Loads the pokemon's team from the save file
     */
    private void getTeam() {
        for (final Element e : root.getChild(XMLParameters.TEAM.getName()).getChildren()) {
            final int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            final int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            final int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            final int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-1] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            try {
                PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
            } catch (SquadFullException e1) {
                System.out.println("TEAM IS FULL");
            }
        }     
    }
    
    /**
     * Loads the trainers from the save file
     */
    private void getTrainers() {
        final PokeMap map = Play.getMapImpl();
        final Map<Integer, Boolean> trainer_isDefeated = new HashMap<>();
        for (final Attribute a : root.getChild(XMLParameters.TRAINERS.getName()).getAttributes()) {
            try {
            	trainer_isDefeated.put(Integer.parseInt(a.getName().substring(1, a.getName().length())), a.getBooleanValue());
            } catch (DataConversionException e) {
                System.out.println("DATA CONVERSION FAILED");
            }
        }
        map.initTrainers(trainer_isDefeated);
    }
    
    /**
     * Loads the inventory from the save file
     */
    private void getInventory() {
        final Map<String, Integer> potions = new HashMap<String, Integer>();
        for (final Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.POTIONS.getName()).getAttributes()) {
            potions.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        final Map<String, Integer> boosts = new HashMap<String, Integer>();
        for (final Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BOOSTS.getName()).getAttributes()) {      
            boosts.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        final Map<String, Integer> balls = new HashMap<String, Integer>();
        for (final Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BALLS.getName()).getAttributes()) {      
            balls.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        InventoryImpl.initializeInventory(potions, boosts, balls);
    }

    /**
     * Loads the box from the save file
     */
    private void getBox() {
        final List<Pokemon> box = new ArrayList<Pokemon>();
        for (final Element e : root.getChild(XMLParameters.BOX.getName()).getChildren()) {
            final int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            final int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            final int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            final int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-1] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            box.add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
        }
        BoxImpl.getBox().setPokemons(box);
    }
    
    @Override
    public void load() {
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