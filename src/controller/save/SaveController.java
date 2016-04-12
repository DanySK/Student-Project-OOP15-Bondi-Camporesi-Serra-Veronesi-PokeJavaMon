package controller.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import controller.modelResources.*;
import controller.parameters.XMLParameters;

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
    
    private void setPosition(float x, float y) {
        Element position = new Element(XMLParameters.POSITION.getName());
        position.setAttribute(XMLParameters.X.getName(),Float.toString(x));
        position.setAttribute(XMLParameters.Y.getName(),Float.toString(y));
        root.addContent(position);
    }
    
    private void setTeam(List<Pokemon> l) {
        Element squadra = new Element(XMLParameters.TEAM.getName());
        List<Pokemon> team = l;
        for (Pokemon x : team) { 
            Element e = new Element(x.getNome());
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getHp()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getExp()));
            int contatore = MIN_MOVES;
            for (String s : x.getMoves()) {
                e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,s);
                contatore ++;
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            squadra.addContent(e);
        }
        root.addContent(squadra);
    }
    
    private void setTrainers(List<Trainers> l) {
        Element allenatori = new Element(XMLParameters.TRAINERS.getName());
        for (Trainers t : l) {
            allenatori.setAttribute(t.getName(),Boolean.toString(t.isBeaten()));
        }
        root.addContent(allenatori);
    }
    
    private void setBag(Map<String,Integer> a, Set<String> b, Map<String,Integer> c) {
        Element borsa = new Element(XMLParameters.BAG.getName());
        Element instruments = new Element(XMLParameters.STRUM.getName());
        for (String o : a.keySet()) {
            instruments.setAttribute(o,Integer.toString(a.get(o)));
        }
        borsa.addContent(instruments);
        Element base = new Element(XMLParameters.BASE.getName());
        for (String s : b) {
            base.setAttribute(s,Boolean.toString(true));
        }
        borsa.addContent(base);
        Element balls = new Element(XMLParameters.BALLS.getName());
        for (String o : c.keySet()) {   
            balls.setAttribute(o,Integer.toString(c.get(o)));
        }
        borsa.addContent(balls);
        root.addContent(borsa);
    }
    
    private void setMoney(int i) {
        root.setAttribute(XMLParameters.MONEY.getName(),Integer.toString(i));
    }
    
    private void setTime(int i) {
        root.setAttribute(XMLParameters.TIME.getName(),Integer.toString(i));
    }
    
    private void setBox(List<Pokemon> l) {
        Element box = new Element(XMLParameters.BOX.getName());
        List<Pokemon> bx = l;
        for (Pokemon x : bx) {
            Element e = new Element(x.getNome());
            e.setAttribute(XMLParameters.HP.getName(),Integer.toString(x.getHp()));
            e.setAttribute(XMLParameters.EXP.getName(),Integer.toString(x.getExp()));
            int contatore = MIN_MOVES;
            for (String s : x.getMoves()) {
                e.setAttribute(XMLParameters.MOVES_ID.getName()+contatore,s);
                contatore ++;
            }
            contatore --;
            e.setAttribute(XMLParameters.NMOVES.getName(),Integer.toString(contatore));
            box.addContent(e);
        }
        root.addContent(box);
    }
    
    private void setReturnPosition(float x, float y) {
        Element retPosition = new Element(XMLParameters.RETURNPOSITION.getName());
        retPosition.setAttribute(XMLParameters.RETX.getName(),Float.toString(x));
        retPosition.setAttribute(XMLParameters.RETY.getName(),Float.toString(y));
        root.addContent(retPosition);
    }
    
    private void setPlace(boolean b) {
        root.setAttribute(XMLParameters.POSITION.getName(),Boolean.toString(b));
    }
    
    public void save(General g) {
        setup();
        setPosition(g.xPos, g.yPos);
        setTeam(g.team);
        setTrainers(g.trainers);
        setBag(g.inv.getOggetti(), g.inv.getBase(), g.inv.getBalls());
        setMoney(g.money);
        setTime(g.time);
        setBox(g.box);
        setReturnPosition(g.exitX, g.exitY);
        setPlace(g.place); 
        try {
            outputter = new XMLOutputter(); 
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}