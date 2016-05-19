package controller.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import controller.MainController;
import controller.parameters.Folder;
import controller.parameters.XMLParameters;
import exceptions.SquadFullException;
import model.map.Position;
import model.map.tile.EncounterTile;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;

/**
 * This class loads all the requested informations. 
 */
public class MainLoadController implements LoadController {
    private static final String FILE_NAME = Folder.SAVEFOLDER.getAbsolutePath() + File.separator + "save.xml";
    private static final int MIN_MOVES = 1;
    private static final int FACTOR = 1;
    private static final int STRING_OFFSET = 1;
    private Element root;
    
    /**
     * Loads the save file
     */
    private void setup() {
        final SAXBuilder builder = new SAXBuilder();
        try {
            final Document document = builder.build(new File(FILE_NAME));
            this.root = document.getRootElement(); 
        } catch (JDOMException e) {
            System.out.println("FAILED LOADING SAVEFOLDER");
        } catch (IOException e) {
            System.out.println("FAILED LOADING SAVEFOLDER");
        }
    }
    
    /**
     * Returns the saved money value
     */
    private int getMoney() {
        return Integer.parseInt(this.root.getAttributeValue(XMLParameters.MONEY.getName()));
    }
    
    /**
     * Returns the saved player's name
     */
    private String getName() {
        return this.root.getAttribute(XMLParameters.NAME.getName()).getValue();
    }
    
    /**
     * Returns the saved player's badges
     */
    private int getBadges() {
        return Integer.parseInt(this.root.getAttributeValue(XMLParameters.BADGES.getName()));
    }
    
    /**
     * Returns the saved player's position
     */
    private Position getPosition() {
        final int x = Integer.parseInt(this.root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.X.getName()));
        final int y = Integer.parseInt(this.root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.Y.getName()));
        return new Position(x, y);
    }
    
    /**
     * Returns the saved team
     */
    private List<Pokemon> getTeam() {
        List<Pokemon> team = new ArrayList<>();
        for (final Element e : this.root.getChild(XMLParameters.TEAM.getName()).getChildren()) {
            final int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            final int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            final int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            final int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-FACTOR] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            team.add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
        }     
        return team;
    }
    
    /**
     * Returns the trainers that player defeated
     */
    private Map<Integer, Boolean> getTrainers() {
        final Map<Integer, Boolean> trainer_isDefeated = new HashMap<>();
        for (final Attribute a : this.root.getChild(XMLParameters.TRAINERS.getName()).getAttributes()) {
            try {
            	trainer_isDefeated.put(Integer.parseInt(a.getName().substring(STRING_OFFSET, a.getName().length())), a.getBooleanValue());
            } catch (DataConversionException e) {
                System.out.println("DATA CONVERSION FAILED");
            }
        }
        return trainer_isDefeated;
    }
    
    /**
     * Returns the saved potions
     */
    private Map<String, Integer> getPotions() {
        final Map<String, Integer> obj = new HashMap<String, Integer>();
        for (final Attribute a : this.root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.POTIONS.getName()).getAttributes()) {
            obj.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        return obj;
    }
    
    /**
     * Returns the saved boosts
     */
    private Map<String, Integer> getBoosts() {
        final Map<String, Integer> obj = new HashMap<String, Integer>();
        for (final Attribute a : this.root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BOOSTS.getName()).getAttributes()) {      
            obj.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        return obj;
    }
    
    /**
     * Returns the saved pokeballs
     */
    private Map<String, Integer> getPokeballs() {
        final Map<String, Integer> obj = new HashMap<String, Integer>();
        for (final Attribute a : this.root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BALLS.getName()).getAttributes()) {      
            obj.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        return obj;
    }

    /**
     * Returns the saved pokemons in box
     */
    private List<Pokemon> getBox() {
        final List<Pokemon> box = new ArrayList<Pokemon>();
        for (final Element e : this.root.getChild(XMLParameters.BOX.getName()).getChildren()) {
            final int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            final int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            final int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            final int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-FACTOR] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            box.add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
        }
        return box;
    }
    
    /**
     * @return the {@link Set} of defeated {@link EncounterTile}'s names
     */
    private Set<String> getDefeatedEncounterTiles() {
        final Set<String> set = new HashSet<>();
        int counter = 0;
        if (this.root.getChild(XMLParameters.ENCOUNTER.getName()).hasAttributes()) {
            for (final Element e : this.root.getChild(XMLParameters.ENCOUNTER.name()).getChildren()) {
                set.add(e.getAttributeValue(Integer.toString(counter)));
                counter ++;
            }
        }
        return set;
    }
    
    @Override
    public void load() {
        setup();
        try {
            MainController.getController().getModel().loadSave(getMoney(), getName(), getBadges(), getPosition(), 
                    getTeam(), getTrainers(), getBox(), getPokeballs(), getBoosts(), getPotions(), getDefeatedEncounterTiles());
        } catch (SquadFullException e) {
            System.out.println("TEAM IS FULL");
        }
    }
    
    @Override
    public boolean saveExists() {
        return new File(FILE_NAME).exists();
    }
}