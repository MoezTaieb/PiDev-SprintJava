/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.DAO;

import Communication.entity.Commentaire;
import Communication.entity.Commentaire;
import bd.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import bd.Dao;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class CommentaireDao implements Dao<Commentaire> {

    @Override
    public Commentaire get(long id) {
        Commentaire commentaire = null;
        String sql = "SELECT * FROM commentaire where id=" + id;
        Statement stmt;
        try {
            stmt = DataBase.getInstance().createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                commentaire = rowToUser(rs);
                System.err.println(commentaire.toString());
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaire;
    }

    public List<Commentaire> getCommentsByAnnonce(long id) {
        ArrayList commentaireList = new ArrayList<Commentaire>();
        Commentaire commentaire = null;
        String sql = "SELECT * FROM commentaire  where annonce_id =" + id;
        Statement stmt;
        try {
            stmt = DataBase.getInstance().createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Commentaire annonce = rowToUser(rs);
                commentaireList.add(annonce);
                System.err.println(annonce.toString());
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaireList;
    }

    public Commentaire get(Commentaire c) {

        Commentaire commentaire = null;

        if (c == null) {
            return null;
        }
        String a = "";
        String b = "";
        String d = "";

        a = c.getCommentateur() == 0 ? "" + null + "" : "" + c.getCommentateur() + "";

        b = c.getAnnonce() == 0 ? "" + null + "" : "'" + c.getAnnonce() + "'";

        d = c.getDateCommentaire() == null ? "" + null + "" : "'" + c.getDateCommentaire() + "'";

        String sql = "SELECT * FROM commentaire where  `commentateur_id`=" + a + " and`annonce_id`=" + b + " and `dateCommentaire`=" + d;

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                commentaire = rowToUser(rs);

                System.err.println(commentaire.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentaire;
    }

    @Override
    public List<Commentaire> getAll() {
        ArrayList commentaireList = new ArrayList<Commentaire>();

        Commentaire commentaire = null;

        String sql = "SELECT * FROM commentaire ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Commentaire annonce = rowToUser(rs);

                commentaireList.add(annonce);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentaireList;

    }

    @Override
    public void save(Commentaire t) {

        String ch = "";

        ch += t.getCommentateur() == 0 ? "" + null + "" : "" + t.getCommentateur() + "";
        ch += t.getAnnonce() == 0 ? "," + null + "" : ",'" + t.getAnnonce() + "'";
        ch += t.getContenuCommentaire() == null ? "," + null + "" : ",'" + t.getContenuCommentaire() + "'";
        ch += t.getDateCommentaire() == null ? "," + null + "" : ",'" + t.getDateCommentaire() + "'";

        String sql = "INSERT INTO `commentaire`(`commentateur_id`, `annonce_id`, `contenuCommentaire`, `dateCommentaire`) VALUES (" + ch + ")";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Commentaire c) {

        String a = c.getCommentateur() == 0 ? "" + null + "" : "" + c.getCommentateur() + "";

        String b = c.getAnnonce() == 0 ? "" + null + "" : "'" + c.getAnnonce() + "'";

        String d = c.getDateCommentaire() == null ? "" + null + "" : "'" + c.getDateCommentaire() + "'";

        String e = c.getContenuCommentaire() == null ? "" + null + "" : "'" + c.getContenuCommentaire() + "'";

        String sql = "UPDATE `commentaire` SET `commentateur_id`=" + a + ",`annonce_id`=" + b + ",`contenuCommentaire`=" + e + ",`dateCommentaire`=" + d + " WHERE id=" + c.getId();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void delete(Commentaire t) {

        String sql = "DELETE FROM commentaire WHERE id=" + t.getId();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Commentaire rowToUser(ResultSet rs) throws SQLException {
        Commentaire commentaire = new Commentaire();

        commentaire.setId(rs.getInt(1));
        commentaire.setCommentateur(rs.getInt(2));
        commentaire.setAnnonce(rs.getInt(3));
        commentaire.setContenuCommentaire(rs.getString(4));
        commentaire.setDateCommentaire(rs.getString(5));

        return commentaire;
    }

}
