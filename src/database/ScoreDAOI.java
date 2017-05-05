package database;

import java.util.List;

public interface ScoreDAOI {
    
        public ScoreDTO getHighscore(String userID) throws DALException;
        public int getScore(String userID) throws DALException;
        public void createHighscore(ScoreDTO hs) throws DALException;
        public void updateHighscore(ScoreDTO hs) throws DALException;
        public List<ScoreDTO> getHighscoreList()throws DALException;	

} 
    

