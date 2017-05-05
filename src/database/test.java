/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bruger.Bruger;

/**
 *
 * @author mohammad
 */
public class test {
    Bruger b = new Bruger("s154088","hazara");
    ScoreDTO dto = new ScoreDTO(b);
    ScoreDAO dao = new ScoreDAO();
}
