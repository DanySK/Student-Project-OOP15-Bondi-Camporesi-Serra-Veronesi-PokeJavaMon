package controller.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import controller.modelResources.*;
import controller.parameters.XMLParameters;
import model.box.Box;
import model.inventory.Inventory;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import model.trainer.Trainer;
import model.utilities.Pair;

public class SaveController implements SaveControllerInterface {
    private static final int MIN_MOVES = 1;
    private Document document;
    private Element root;
    private XMLOutputter outputter;
    private static final String FILE_NAME = "resources/files/save.xml";
    private FileOutputStream fos;
    
    private void setup() {
        root = new Element(XMLParameters.TITLE.getName());
        document = new Document(root);
        try {
            fos = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void setPosition(Pair<Float, Float> pos) {
        Element position = new Element(XMLParameters.POSITION.getName());
        position.setAttribute(XMLParameters.X.getName(),Float.toString(pos.getX()));
        position.setAttribute(XMLParameters.Y.getName(),Float.toString(pos.getY()));
        root.addContent(position);
    }
    
    private void setTeam(List<Pokemon> team) {
        Element squadra = new Element(XMLParameters.TEAM.getName());
        for (final Pokemon x : team) { 
            Element e = new Element(x.getPokemon().getName());
            e.setAttribute(XMLParameters.LV.getName(),Integer.toString(x.getStat(Stat.LVL)));
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getCurrentHP()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getNecessaryExp()));
            int contatore = MIN_MOVES;
            for (final Move m : x.getCurrentMoves()) {
                e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,m.name());
                contatore ++;
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            squadra.addContent(e);
        }
        root.addContent(squadra);
    }
    
    private void setTrainers(List<Trainer> l) {
        Element allenatori = new Element(XMLParameters.TRAINERS.getName());
        for (final Trainer t : l) {
            allenatori.setAttribute(t.getTrainerDB().name(),Boolean.toString(t.isDefeated()));
        }
        root.addContent(allenatori);
    }
    
    private void setBag(Inventory i) {
        Element borsa = new Element(XMLParameters.BAG.getName());
        Element instruments = new Element(XMLParameters.POTIONS.getName());
        for (final Item item : i.getSubInventory(Item.ItemType.POTION).keySet()) {
            instruments.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.POTION).get(item)));
        }
        borsa.addContent(instruments);
        Element boosts = new Element(XMLParameters.BOOSTS.getName());
        for (final Item item : i.getSubInventory(Item.ItemType.BOOST).keySet()) {
            boosts.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.BOOST).get(item)));
        }
        borsa.addContent(boosts);
        Element balls = new Element(XMLParameters.BALLS.getName());
        for (final Item item : i.getSubInventory(Item.ItemType.POKEBALL).keySet()) {
            balls.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.POKEBALL).get(item)));
        }
        borsa.addContent(balls);
        root.addContent(borsa);
    }
    
    private void setMoney(int i) {
        root.setAttribute(XMLParameters.MONEY.getName(),Integer.toString(i));
    }
    
    private void setBox(Box b) {
        Element box = new Element(XMLParameters.BOX.getName());
        for (final Pokemon x : b.getPokemonList()) { 
            Element e = new Element(x.getPokemon().getName());
            e.setAttribute(XMLParameters.LV.getName(),Integer.toString(x.getStat(Stat.LVL)));
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getCurrentHP()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getNecessaryExp()));
            int contatore = MIN_MOVES;
            for (final Move s : x.getCurrentMoves()) {
                e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,s.name());
                contatore ++;
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            box.addContent(e);
        }
        root.addContent(box);
    }
    
    public void save(General g) {
        setup();
        setPosition(g.getPosition());
        setTeam(g.getTeam());
        setTrainers(g.getTrainers());
        setBag(g.getInv());
        setMoney(g.getMoney());
        setBox(g.getBox());
        try {
            outputter = new XMLOutputter(); 
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}