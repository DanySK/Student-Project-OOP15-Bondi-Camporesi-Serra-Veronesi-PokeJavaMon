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
import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;
import model.resources.*;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;
import model.utilities.Pair;

public class LoadController implements LoadControllerInterface {
    private static final String FILE_NAME = System.getProperty("user.home") + File.separator + "save.xml";
    private static final int MIN_MOVES = 1;
    private static SAXBuilder builder;
    private static Document document;
    private static Element root;
    
    private static void setup() {
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
    
    private static int getMoney() {
        return Integer.parseInt(root.getAttributeValue(XMLParameters.MONEY.getName()));
    }
    
    private static Pair<Float, Float> getPosition() {
        final float x = Float.parseFloat(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.X.getName()));
        final float y = Float.parseFloat(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.Y.getName()));
        return new Pair<Float, Float> (x, y);
    }
    
    private static List<Pokemon> getTeam() {
        List<Pokemon> squadra = new ArrayList<Pokemon>();
        for (Element e : root.getChild(XMLParameters.TEAM.getName()).getChildren()) {
            int lv = Integer.parseInt(e.getAttributeValue(XMLParameters.LV.getName()));
            int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            String[] moves = new String[cont];
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves[a-1] = e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a);
            }
            squadra.add(StaticPokemonFactory.createPokemon(e.getName(), lv, hp, exp, moves));
        }
        return squadra;
    }
    
    private static List<Trainer> getTrainers() {
        List<Trainer> trainers = new ArrayList<Trainer>();
        for (Attribute a : root.getChild(XMLParameters.TRAINERS.getName()).getAttributes()) {
            try {
                trainers.add(StaticTrainerFactory.createTrainer(a.getName(),a.getBooleanValue()));
            } catch (DataConversionException e) {
                e.printStackTrace();
            }
        }
        return trainers;
    }
    
    private static Inventory getInventory() {
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
        Inventory inv = InventoryImpl.initializeInventory(potions, boosts, balls);
        return inv;
    }

    private static Box getBox() {
        List<Pokemon> box = new ArrayList<Pokemon>();
        Box retBox = BoxImpl.getBox();
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
        retBox.setPokemons(box);
        return retBox;
    }
    
    public static General load() {
        setup();
        return new General(getTeam(),getBox(),getTrainers(),getInventory(),getMoney(),getPosition());
    }
    
    public static boolean saveExists() {
        return new File(FILE_NAME).exists();
    }
}