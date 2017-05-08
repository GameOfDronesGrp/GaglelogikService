/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author haydar
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ScoreDAO implements ScoreDAOI{
        
        public ScoreDTO getHighscore(String userID) throws DALException{
            try {
                Connector connector = new Connector();
                ResultSet rs = connector.doQuery("SELECT * FROM Highscore WHERE user_id = '" + userID+"';");
                if(rs.next())
                    return new ScoreDTO(rs.getString("user_id"), rs.getInt("score"), rs.getString("time"));
                else {
                    System.out.println("Ingen værdier fundet. Opretter nyt");
                    createHighscore(userID);
                    getScore(userID);
                }
            } catch (SQLException ex) {
                System.out.println("Kunne ikke hente highscore");
                Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch(ClassNotFoundException e){
                System.err.print(e);
                e.printStackTrace();
            }
           return null;
        }
        
        public int getScore(String userID) throws DALException{
            try {
                Connector connector = new Connector();
                ResultSet rs = connector.doQuery("SELECT score FROM Highscore WHERE user_id = '"+ userID+"';");
                int score = 0;
                if(rs.next()) score = rs.getInt("score");
                else {
                    System.out.println("Ingen værdier fundet. Opretter nyt");
                    createHighscore(userID);
                    getScore(userID);
                }
                return score;
            } catch (SQLException ex) {
                Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Kunne ikke hente score");
            } catch(ClassNotFoundException e){
                System.err.print(e);
            }
          return -1;  
        }
        
        private void createHighscore(String user_id) throws DALException{
            try {
                Connector connector = new Connector();
                connector.doUpdate(
                "INSERT INTO Highscore(user_id, score) VALUES " +
                "('" + user_id+ "', "+ 0 +");");
            } catch (SQLException exception) {
                Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, exception);
            } catch ( ClassNotFoundException e){
                
            }
        }
        
        @Override
	public void createHighscore(ScoreDTO hs) throws DALException{
            try {
                Connector connector = new Connector();
                connector.doUpdate(
                "INSERT INTO Highscore(user_id, score) VALUES " +
                "('" + hs.getUserID()+ "', " + hs.getScore() + ");");
            } catch (SQLException exception) {
                exception.printStackTrace();
                Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, exception);
            } catch ( ClassNotFoundException e){
                e.printStackTrace();
                
            }
	}
        
        @Override
	public void updateHighscore(ScoreDTO hs) throws DALException{
            
            try {
                Connector connector = new Connector();
                connector.doUpdate( "UPDATE Highscore SET  score = " + hs.getScore() + 
                        ", time =  NOW() WHERE user_id = '"+hs.getUserID()+"';");
            } catch (SQLException e) {
                throw new DALException(e);
            } catch(ClassNotFoundException e){
                System.err.print(e);
            }
	}

        @Override
	public List<ScoreDTO> getHighscoreList() throws DALException{
            
            List<ScoreDTO> list = new ArrayList<ScoreDTO>();
            try {
                Connector connector = new Connector();
                ResultSet rs = connector.doQuery("SELECT  @curRank := @curRank + 1  AS rank, user_id,"+
                        " score, time  FROM ( SELECT @curRank := 0) r,  Highscore h  ORDER BY  score DESC;");
                while (rs.next()){
                    list.add(new ScoreDTO(rs.getInt("rank"), rs.getString("user_id"), rs.getInt("score"), rs.getString("time")));
                }
            }catch (SQLException e){
                e.printStackTrace();
                throw new DALException(e);
            } catch(ClassNotFoundException e){
                System.err.print(e);
                e.printStackTrace();
            }
            return list;
        }
}
