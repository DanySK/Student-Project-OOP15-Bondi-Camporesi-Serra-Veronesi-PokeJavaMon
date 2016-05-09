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

import controller.Controller;
import controller.parameters.FilePath;
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

/**
 * This class saves all the requested informations. 
 */
public class SaveController implements SaveControllerInterface {
    private static final int MIN_MOVES = 1;
    private Document document;
    private Element root;
    private final String FILE_NAME = FilePath.SAVE.getAbsolutePath() + File.separator + "save.xml";
    private FileOutputStream fos;
    
    /**
     * Prepares the save file
     */
    private void setup() {
        root = new Element(XMLParameters.TITLE.getName());
        document = new Document(root);
        try {
            fos = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            final File f = new File(FILE_NAME);
            try {
                f.createNewFile();
                fos = new FileOutputStream(new File(FILE_NAME));
            } catch (IOException e1) {
                System.out.println("ERROR PREPARING SAVE FILE");
            }
        }
    }
    
    /**
     * Saves player's position
     */
    private void setPosition() {
        final Element position = new Element(XMLParameters.POSITION.getName());
        position.setAttribute(XMLParameters.X.getName(),Integer.toString(PlayerImpl.getPlayer().getTileX()));
        position.setAttribute(XMLParameters.Y.getName(),Integer.toString(PlayerImpl.getPlayer().getTileY()));
        root.addContent(position);
    }
    
    /**
     * Saves player's badge value
     */
    private void setBadges() {
        root.setAttribute(XMLParameters.BADGES.getName(),Integer.toString(PlayerImpl.getPlayer().getLastBadge()));
    }
    
    /**
     * Saves player's pokemon team
     */
    private void setTeam() {
        final Element squadra = new Element(XMLParameters.TEAM.getName());
        final List<PokemonInBattle> team = PlayerImpl.getPlayer().getSquad().getPokemonList();
        for (final Pokemon x : team) { 
            final Element e = new Element(x.getPokemon().getName());
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
    
    /**
     * Saves trainers
     */
    private void setTrainers() {
        final Element allenatori = new Element(XMLParameters.TRAINERS.getName());
        final Set<Trainer> l = Controller.getController().getPokeMap().getTrainers();
        for (final Trainer t : l) {
            if (t != null) {
                allenatori.setAttribute("N" + t.getID(),Boolean.toString(t.isDefeated()));
            }
        }
        root.addContent(allenatori);
    }
    
    /**
     * Saves player's inventory
     */
    private void setBag() {
        final Element borsa = new Element(XMLParameters.BAG.getName());
        final Element instruments = new Element(XMLParameters.POTIONS.getName());
        final Inventory i = PlayerImpl.getPlayer().getInventory();
        for (final Item item : i.getSubInventory(Item.ItemType.POTION).keySet()) {
            instruments.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.POTION).get(item)));
        }
        borsa.addContent(instruments);
        final Element boosts = new Element(XMLParameters.BOOSTS.getName());
        for (final Item item : i.getSubInventory(Item.ItemType.BOOST).keySet()) {
            boosts.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.BOOST).get(item)));
        }
        borsa.addContent(boosts);
        final Element balls = new Element(XMLParameters.BALLS.getName());
        for (final Item item : i.getSubInventory(Item.ItemType.POKEBALL).keySet()) {
            balls.setAttribute(item.toString(),Integer.toString(i.getSubInventory(Item.ItemType.POKEBALL).get(item)));
        }
        borsa.addContent(balls);
        root.addContent(borsa);
    }
    
    /**
     * Saves player's money
     */
    private void setMoney() {
        root.setAttribute(XMLParameters.MONEY.getName(),Integer.toString(PlayerImpl.getPlayer().getMoney()));
    }
    
    /**
     * Saves player's name
     */
    private void setName() {
        root.setAttribute(XMLParameters.NAME.getName(),PlayerImpl.getPlayer().getName());
    }
    
    /**
     * Saves player's box
     */
    private void setBox() {
        final Element box = new Element(XMLParameters.BOX.getName());
        final Box b = PlayerImpl.getPlayer().getBox();
        for (final Pokemon x : b.getPokemonList()) { 
            final Element e = new Element(x.getPokemon().getName());
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
            XMLOutputter outputter;
            outputter = new XMLOutputter(); 
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fos);
        } catch (IOException e) {
            System.out.println("ERROR IN SAVE");
        }
    }
}