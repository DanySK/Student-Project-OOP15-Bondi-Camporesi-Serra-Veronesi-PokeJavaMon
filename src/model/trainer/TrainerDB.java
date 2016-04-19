package model.trainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.pokemon.PokemonDB;
import model.utilities.Pair;

public enum TrainerDB {
        DR_GHAIN("LINUX MASTER RACE", "WINDOWS WILL NEVER WIN", "HAHAH I TOLD YOU WINDOWS WOULD NEVER WIN", 
                    Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.CHARIZARD, 45), 
                                  new Pair<PokemonDB, Integer>(PokemonDB.BLASTOISE, 45), 
                                  new Pair<PokemonDB, Integer>(PokemonDB.VENUSAUR, 45)), 2000),
        
        BULLO_1("Pronto? Non ci andro' piano con te!", "Argh", "YEAHHH",
                    Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.PIDGEY, 5),
                                  new Pair<PokemonDB, Integer>(PokemonDB.RATTATA,5)), 1000),
        
        BULLO_2("Non sono come il bullo di prima", "Forse mi sbagliavo...", "Visto, te l'avevo detto",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.SANDSHREW, 6),
                              new Pair<PokemonDB, Integer>(PokemonDB.RATTATA, 7),
                              new Pair<PokemonDB, Integer>(PokemonDB.PIDGEY, 8)), 1000),
        
        BULLO_3("E' arrivato il tuo momento", "...O forse il mio", "Accendero' una candela per te",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.RATTATA, 7),
                              new Pair<PokemonDB, Integer>(PokemonDB.PIDGEY, 8),
                              new Pair<PokemonDB, Integer>(PokemonDB.TENTACOOL, 12)), 1000),
        
        ALLENATORE_1_1("Non sono come gli altri bulletti!", "...", ";)",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.BULBASAUR, 11),
                              new Pair<PokemonDB, Integer>(PokemonDB.PIKACHU, 11)), 2000),
        
        ALLENATORE_1_2(".", "..", "...",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.CHARMANDER, 11),
                              new Pair<PokemonDB, Integer>(PokemonDB.SANDSHREW, 12)), 2000),
        
        ALLENATORE_1_3("Ugh, un altro", "ecco appunto", "a dopo",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.SQUIRTLE, 13),
                              new Pair<PokemonDB, Integer>(PokemonDB.TENTACOOL, 14)), 2000),
        
        PROF_1("Ora verificheremo le sue prime conoscenze di Java.", "Molto bene.", "Non credo che lei sia ancora in grado di passare, consiglio di riguardarsi le basi",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.PIKACHU, 15),
                              new Pair<PokemonDB, Integer>(PokemonDB.RAICHU, 18)), 3000),
                
        PROF_2("Bene, ora vediamo i suoi progressi nella Programmazione ad Oggetti.", "Vedo miglioramenti, puo' passare", "Non ci siamo, dia una ripassata poi provi ancora piu' tardi",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.TENTACOOL, 20),
                              new Pair<PokemonDB, Integer>(PokemonDB.TENTACRUEL, 23),
                              new Pair<PokemonDB, Integer>(PokemonDB.WARTORTLE, 25)), 3000),
        
        //TODO: ALLENATORI 2_*
        
        PROF_3("Siamo quasi a fine corso, vediamo se e' pronto per dare l'esame finale.", "Ci siamo.", "Non va ancora bene",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.SANDSHREW, 27),
                              new Pair<PokemonDB, Integer>(PokemonDB.PIDGEOTTO, 28),
                              new Pair<PokemonDB, Integer>(PokemonDB.VENUSAUR, 32)), 3000),
        
        //TODO: ALLENATORI 3_*
        
        
        PROF_VIROLI("E' arrivato il momento, mostrami il progetto finale!", "Eccellente, veramente eccellente", "Prego riprovi l'anno prossimo",
                Arrays.asList(new Pair<PokemonDB, Integer>(PokemonDB.PIDGEOT,33),
                              new Pair<PokemonDB, Integer>(PokemonDB.RAICHU, 34),
                              new Pair<PokemonDB, Integer>(PokemonDB.SANDSLASH,35),
                              new Pair<PokemonDB, Integer>(PokemonDB.BLASTOISE, 36),
                              new Pair<PokemonDB, Integer>(PokemonDB.CHARIZARD, 36)), 5000),
        ;
        
        private TrainerDB(final String initialMessage, final String beatenMessage, final String winningMessage, final List<Pair<PokemonDB, Integer>> pkmnList, final int money) {
            this.initialMessage = initialMessage;
            this.defeatedMessage = beatenMessage;
            this.winningMessage = winningMessage;
            this.pkmnList = pkmnList;
            this.money = money;
        }
        
        private String initialMessage;
        private String defeatedMessage;
        private String winningMessage;
        private List<Pair<PokemonDB, Integer>> pkmnList;
        private int money;
        
        public String getInitialMessage() {
            return this.initialMessage;
         }
        
        public String getDefeatedMessage() {
            return this.defeatedMessage;
         }
        public String getWinningMessage() {
            return this.winningMessage;
         }
         
        public List<Pair<PokemonDB, Integer>> getSquad() {
            return Collections.unmodifiableList(this.pkmnList);
        }
        
        public int getMoney() {
            return this.money;
        }
        

}
