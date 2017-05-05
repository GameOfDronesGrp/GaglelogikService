package database;

import java.io.Serializable;
/**
 *
 * @author mohammad
 */
public interface ScoreDTOI{// implements Serializable {
    //private static final long serialVersionUID = 1L;

    public int getRank(String userID);
    public void setScore(int score) ;
    public String getUserID();
    public int getScore();
    public void setUserID(int id);
    public String getDatetime();
    public void setDatetime(String datetime);
}

