/*
 * PlayerBean.java, 1/05/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */
package uppa.project.bean;

import jakarta.persistence.EntityManager;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Player;
import uppa.project.web.translation.Translator;

public class PlayerBean {
  private Player player;

  public PlayerBean(Player player) {
    this.player = player;
  }


  /**
   * Gestion des joueurs de la parties
   *
   * @return true si tous les joueurs sont gérés, false sinon
   */
  public boolean validate() {
    EntityManager em = EntityManagerProvider.getInstance();
    em.getTransaction().begin();
    try {
      Player dbPlayer = new Player(player.getGame(), player.getUser());
      dbPlayer.setScore(player.getScore());
      if (player.isWinner()) dbPlayer.setWinner();
      dbPlayer.setClickCount(player.getClickCount());
      dbPlayer.setRapidClickCount(player.getRapidClickCount());
      dbPlayer.setRightClickCount(player.getRightClickCount());
      player.getGame().addPlayer(dbPlayer);
      player.getUser().addPlayedGame(dbPlayer);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      return false;
    }
  }
}
