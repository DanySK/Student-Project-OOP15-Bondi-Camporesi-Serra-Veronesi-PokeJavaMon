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

import controller.parameters.Folder;
import controller.parameters.XMLParameters;
import model.ModelInterface.Save;
import model.box.Box;
import model.inventory.Inventory;
import model.items.Item;
import model.map.tile.EncounterTile;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;

/**
 * This class saves all the requested informations. 
 */
public class MainSaveController implements SaveController {
    private static final String FILE_NAME = Folder.SAVEFOLDER.getAbsolutePath() + File.separator + "save.xml";
    private static final int MIN_MOVES = 1;
    private Document document;
    private Element root;
    private FileOutputStream fos;
    private Save sv;   
    
    @Override
    public void setSave(final Save save) {
        this.sv = save;
    }
    
    /**
     * Prepares the save file
     */
    private void setup() {
        this.root = new Element(XMLParameters.TITLE.getName());
        this.document = new Document(this.root);
        try {
            this.fos = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            final File f = new File(FILE_NAME);
            try {
                f.createNewFile();
                this.fos = new FileOutputStream(new File(FILE_NAME));
            } catch (IOException e1) {
                System.out.println("ERROR PREPARING SAVEFOLDER FILE");
            }
        }
    }
    
    /**
     * Saves player's position
     */
    private void setPosition() {
        final Element position = new Element(XMLParameters.POSITION.getName());
        position.setAttribute(XMLParameters.X.getName(),Integer.toString(this.sv.getPlayerPosition().getX()));
        position.setAttribute(XMLParameters.Y.getName(),Integer.toString(this.sv.getPlayerPosition().getY()));
        this.root.addContent(position);
    }
    
    /**
     * Saves player's badge value
     */
    private void setBadges() {
        this.root.setAttribute(XMLParameters.BADGES.getName(),Integer.toString(this.sv.getPlayerBadges()));
    }
    
    /**
     * Saves player's pokemon team
     */
    private void setTeam() {
        final Element squadra = new Element(XMLParameters.TEAM.getName());
        final List<PokemonInBattle> team = this.sv.getPokemonSquad();
        for (final Pokemon x : team) { 
            final Element e = new Element(x.getPokedexEntry().getName());
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
        this.root.addContent(squadra);
    }
    
    /**
     * Saves trainers
     */
    private void setTrainers() {
        final Element allenatori = new Element(XMLParameters.TRAINERS.getName());
        final Set<Trainer> l = this.sv.getTrainers();
        for (final Trainer t : l) {
            if (t != null) {
                allenatori.setAttribute("N" + t.getID(),Boolean.toString(t.isDefeated()));
            }
        }
        this.root.addContent(allenatori);
    }
    
    /**
     * Saves player's inventory
     */
    private void setBag() {
        final Element borsa = new Element(XMLParameters.BAG.getName());
        final Element instruments = new Element(XMLParameters.POTIONS.getName());
        final Inventory i = this.sv.getInventory();
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
        this.root.addContent(borsa);
    }
    
    /**
     * Saves player's money
     */
    private void setMoney() {
        this.root.setAttribute(XMLParameters.MONEY.getName(),Integer.toString(this.sv.getPlayerMoney()));
    }
    
    /**
     * Saves player's name
     */
    private void setName() {
        this.root.setAttribute(XMLParameters.NAME.getName(),this.sv.getPlayerName());
    }
    
    /**
     * Saves player's box
     */
    private void setBox() {
        final Element box = new Element(XMLParameters.BOX.getName());
        final Box b = this.sv.getBox();
        for (final Pokemon x : b.getPokemonList()) { 
            final Element e = new Element(x.getPokedexEntry().getName());
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
        this.root.addContent(box);
    }
    
    /**
     * Saves the defeated {@link EncounterTile}s
     */
    private void setDefeatedEncounterTiles() {
        final Element e = new Element(XMLParameters.ENCOUNTER.getName());
        int counter = 0;
        for (final EncounterTile et : sv.getEncounterTilesToBeRemoved()) {
            e.setAttribute("N" + Integer.toString(counter), et.getPokemon().getPokedexEntry().name());
            counter ++;
        }
        this.root.addContent(e);
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
        setDefeatedEncounterTiles();
        try {
            XMLOutputter outputter;
            outputter = new XMLOutputter(); 
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(this.document, this.fos);
        } catch (IOException e) {
            System.out.println("ERROR IN SAVEFOLDER");
        }
    }
}