package controller.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import controller.parameters.XMLParameters;
import model.box.Box;
import model.inventory.Inventory;
import model.items.Item;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.resources.Play;

public class SaveController implements SaveControllerInterface {
    private final int MIN_MOVES = 1;
    private Document document;
    private Element root;
    private XMLOutputter outputter;
    private final String FILE_NAME = System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Save" + File.separator + "save.xml";
    private FileOutputStream fos;
    private static SaveController SINGLETON;
    
    private SaveController() {}
    
    public static SaveController getController() {
        if (SINGLETON == null) {
            synchronized (SaveController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new SaveController();
                }
            }
        }
        return SINGLETON;
    }
    
    private void setup() {
        root = new Element(XMLParameters.TITLE.getName());
        document = new Document(root);
        try {
            fos = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            File f = new File(FILE_NAME);
            try {
                f.createNewFile();
                fos = new FileOutputStream(new File(FILE_NAME));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void setPosition() {
        Element position = new Element(XMLParameters.POSITION.getName());
        position.setAttribute(XMLParameters.X.getName(),Integer.toString(PlayerImpl.getPlayer().getTileX()));
        position.setAttribute(XMLParameters.Y.getName(),Integer.toString(PlayerImpl.getPlayer().getTileY()));
        root.addContent(position);
    }
    
    private void setBadges() {
        root.setAttribute(XMLParameters.BADGES.getName(),Integer.toString(PlayerImpl.getPlayer().getLastBadge()));
    }
    
    private void setTeam() {
        Element squadra = new Element(XMLParameters.TEAM.getName());
        List<PokemonInBattle> team = PlayerImpl.getPlayer().getSquad().getPokemonList();
        for (final Pokemon x : team) { 
            Element e = new Element(x.getPokemon().getName());
            e.setAttribute(XMLParameters.LV.getName(),Integer.toString(x.getStat(Stat.LVL)));
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getCurrentHP()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getStat(Stat.EXP)));
            int contatore = MIN_MOVES;
            for (final Move m : x.getCurrentMoves()) {
                if (m != null) {
                    e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,m.name());
                    contatore ++;
                }
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            squadra.addContent(e);
        }
        root.addContent(squadra);
    }
    
    private void setTrainers() {
        Element allenatori = new Element(XMLParameters.TRAINERS.getName());
        Set<Trainer> l = Play.getMapImpl().getTrainers();
        for (final Trainer t : l) {
            if (t != null) {
                allenatori.setAttribute("N" + t.getID(),Boolean.toString(t.isDefeated()));
            }
        }
        root.addContent(allenatori);
    }
    
    private void setBag() {
        Element borsa = new Element(XMLParameters.BAG.getName());
        Element instruments = new Element(XMLParameters.POTIONS.getName());
        Inventory i = PlayerImpl.getPlayer().getInventory();
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
    
    private void setMoney() {
        root.setAttribute(XMLParameters.MONEY.getName(),Integer.toString(PlayerImpl.getPlayer().getMoney()));
    }
    
    private void setName() {
        root.setAttribute(XMLParameters.NAME.getName(),PlayerImpl.getPlayer().getName());
    }
    
    private void setBox() {
        Element box = new Element(XMLParameters.BOX.getName());
        Box b = PlayerImpl.getPlayer().getBox();
        for (final Pokemon x : b.getPokemonList()) { 
            Element e = new Element(x.getPokemon().getName());
            e.setAttribute(XMLParameters.LV.getName(),Integer.toString(x.getStat(Stat.LVL)));
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getCurrentHP()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getStat(Stat.EXP)));
            int contatore = MIN_MOVES;
            for (final Move s : x.getCurrentMoves()) {
                if (s != null) {
                    e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,s.name());
                    contatore ++;
                }
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            box.addContent(e);
        }
        root.addContent(box);
    }
    
    @Override
    public void save() {
        setup();
        setPosition();
        setTeam();
        setTrainers();
        setBag();
        setMoney();
        setName();
        setBadges();
        setBox();
        try {
            outputter = new XMLOutputter(); 
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}