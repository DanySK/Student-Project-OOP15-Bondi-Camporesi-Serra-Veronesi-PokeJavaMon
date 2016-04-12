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

import controller.modelResources.*;
import controller.parameters.XMLParameters;
import model.inventory.Inventory;
import model.pokemon.Pokemon;
import model.trainer.Trainer;

public class LoadController implements LoadControllerInterface {
    private static final String FILE_NAME = "resources/files/save.xml";
    private static final int MIN_MOVES = 1;
    private SAXBuilder builder;
    private Document document;
    private Element root;
    
    public LoadController() {
        setup();
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
    
    private int getMoney() {
        return Integer.parseInt(root.getAttributeValue(XMLParameters.MONEY.getName()));
    }
    
    private int getTime() {
        return Integer.parseInt(root.getAttributeValue(XMLParameters.TIME.getName()));
    }
    
    private boolean getPlace() {
        return Boolean.getBoolean(root.getAttributeValue(XMLParameters.PLACE.getName()));
    }
    
    private float getX() {
        return Float.parseFloat(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.X.getName()));
    }
    
    private float getY() {
        return Float.parseFloat(root.getChild(XMLParameters.POSITION.getName()).getAttributeValue(XMLParameters.Y.getName()));
    }
    
    private List<Pokemon> getTeam() {
        List<Pokemon> squadra = new ArrayList<Pokemon>();
        for (Element e : root.getChild(XMLParameters.TEAM.getName()).getChildren()) {
            int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName()));
            Set<String> moves = new HashSet<String>();
            int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            for (int a = MIN_MOVES; a <= cont; a++) {   
                moves.add(e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a));
            }
            //squadra.add(new Pokemon(e.getName(),hp,exp,moves));
        }
        return squadra;
    }
    
    private List<Trainer> getTrainers() {
        List<Trainer> trainers = new ArrayList<Trainer>();
        for (Attribute a : root.getChild(XMLParameters.TRAINERS.getName()).getAttributes()) {
//            try {
//                trainers.add(new Trainer(a.getName(),a.getBooleanValue()));
//            } catch (DataConversionException e) {
//                e.printStackTrace();
//            }
        }
        return trainers;
    }
    
    private Inventory getInventory() {
        Map<String, Integer> objects = new HashMap<String, Integer>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.POTIONS.getName()).getAttributes()) {
            objects.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        Set<String> base = new HashSet<String>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BOOSTS.getName()).getAttributes()) {       
            base.add(a.getName());
        }
        Map<String, Integer> balls = new HashMap<String, Integer>();
        for (Attribute a : root.getChild(XMLParameters.BAG.getName()).getChild(XMLParameters.BALLS.getName()).getAttributes()) {      
            balls.put(a.getName(), Integer.parseInt(a.getValue()));
        }
        //Inventory inv = new Inventory(objects,base,balls);
        return null;//inv;
    }

    private List<Pokemon> getBox() {
        List<Pokemon> box = new ArrayList<Pokemon>();
        for (Element e : root.getChild(XMLParameters.BOX.getName()).getChildren()) {         
            int hp = Integer.parseInt(e.getAttributeValue(XMLParameters.HP.getName()));
            int exp = Integer.parseInt(e.getAttributeValue(XMLParameters.EXP.getName())); 
            Set<String> moves = new HashSet<String>();
            int cont = Integer.parseInt(e.getAttributeValue(XMLParameters.NMOVES.getName()));
            for (int a = MIN_MOVES; a <= cont; a++) {      
                moves.add(e.getAttributeValue(XMLParameters.MOVES_ID.getName()+a));
            }
            //box.add(new Pokemon(e.getName(),hp,exp,moves));
        }
        return box;
    }
    
    private float getRetX() {
        return Float.parseFloat(root.getChild(XMLParameters.RETURNPOSITION.getName()).getAttributeValue(XMLParameters.RETX.getName()));
    }
    
    private float getRetY() {
        return Float.parseFloat(root.getChild(XMLParameters.RETURNPOSITION.getName()).getAttributeValue(XMLParameters.RETY.getName()));
    }
    
    public General load() {
        //return new General(getTeam(),getTrainers(),getInventory(),getMoney(),getTime(),getBox(),getX(),getY(),getRetX(),getRetY(),getPlace());
    	return null;
    }
}