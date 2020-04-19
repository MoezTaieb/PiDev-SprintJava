/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.DAO;

import Communication.entity.Annonce;
import Communication.entity.Annonce;
import bd.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.PiDev;
import bd.Dao;
import user.entity.User;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class AnnonceDao implements Dao<Annonce> {

    public List<Annonce> search(String mots) {

        ArrayList annonceList = new ArrayList<Annonce>();
        String sql = "SELECT * FROM annonce where titreAnnonce like '%" + mots + "'  ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Annonce annonce = rowToUser(rs);

                annonceList.add(annonce);

                System.out.println("search" + annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonceList;

    }

    //ORDER BY column1, column2, ... ASC|DESC;
    public List<Annonce> orderByDESC() {

        ArrayList annonceList = new ArrayList<Annonce>();

        String sql = "SELECT * FROM annonce    ORDER BY dateAnnonce DESC  ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Annonce annonce = rowToUser(rs);

                annonceList.add(annonce);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonceList;

    }

    public List<Annonce> orderByASC() {

        ArrayList annonceList = new ArrayList<Annonce>();

        String sql = "SELECT * FROM annonce    ORDER BY dateAnnonce ASC  ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Annonce annonce = rowToUser(rs);

                annonceList.add(annonce);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonceList;

    }

    public void deconseiller(Annonce t) {

        String sql = "DELETE FROM recommandation WHERE annonce_id=" + t.getId() + " and recommandeur_id=" + PiDev.currentUser.getid();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void recommander(Annonce t) {

        String ch = "";

        ch += PiDev.currentUser.getid() == 0 ? "" + null + "" : "" + PiDev.currentUser.getid() + "";
        ch += t.getId() == 0 ? "," + null + "" : "," + t.getId() + "";
        String sql = "INSERT INTO recommandation(recommandeur_id, annonce_id)VALUES(" + ch + ")";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public int listRecommander(Annonce t) {

        int nb = 0;

        String sql = "SELECT count(*) FROM recommandation  where annonce_id=" + t.getId();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name

                nb = rs.getInt(1);

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nb;
    }

    public Annonce getByTitle(String title) {

        Annonce annonce = null;

        String sql = "SELECT * FROM annonce where titreAnnonce='" + title + "'";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                annonce = rowToUser(rs);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonce;
    }

    @Override
    public Annonce get(long id) {

        Annonce annonce = null;

        String sql = "SELECT * FROM annonce where id=" + id;

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                annonce = rowToUser(rs);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonce;
    }

    public Annonce get(Annonce t) {

        Annonce annonce = null;
        //, , descriptionAnnonce,  imageUrlAnnonce ,

        String sql = "SELECT * FROM annonce where  dateAnnonce='" + t.getDateAnnonce() + "' and titreAnnonce='" + t.getTitreAnnonce() + "' and posteur_id=" + t.getPosteur();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                annonce = rowToUser(rs);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonce;
    }

    //SELECT A.* ,COUNT(R.annonce_id) 'nb' FROM `annonce` A LEFT OUTER JOIN recommandation R on A.id=R.annonce_id GROUP BY `id`,`posteur_id`,`titreAnnonce`,`descriptionAnnonce`,`imageUrlAnnonce`,`dateAnnonce` ORDER BY `nb` DESC
    public List<Annonce> orderByRecommondation() {

        ArrayList annonceList = new ArrayList<Annonce>();

        String sql = "SELECT A.* ,COUNT(R.annonce_id) 'nb' FROM `annonce` A LEFT OUTER JOIN recommandation R on A.id=R.annonce_id GROUP BY `id`,`posteur_id`,`titreAnnonce`,`descriptionAnnonce`,`imageUrlAnnonce`,`dateAnnonce` ORDER BY `nb` DESC ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Annonce annonce = rowToUser(rs);

                annonceList.add(annonce);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonceList;

    }

    @Override
    public List<Annonce> getAll() {

        ArrayList annonceList = new ArrayList<Annonce>();

        String sql = "SELECT * FROM annonce ";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                Annonce annonce = rowToUser(rs);

                annonceList.add(annonce);

                System.err.println(annonce.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return annonceList;

    }

    @Override
    public void save(Annonce t) {

        String ch = "";

        ch += t.getPosteur() == 0 ? "" + null + "" : "" + t.getPosteur() + "";
        ch += t.getTitreAnnonce() == null ? "," + null + "" : ",'" + t.getTitreAnnonce() + "'";
        ch += t.getDescriptionAnnonce() == null ? "," + null + "" : ",'" + t.getDescriptionAnnonce() + "'";
        ch += t.getImageUrlAnnonce() == null ? "," + null + "" : ",'" + t.getImageUrlAnnonce() + "'";
        ch += t.getDateAnnonce() == null ? "," + null + "" : ",'" + t.getDateAnnonce() + "'";

        String sql = "INSERT INTO annonce(posteur_id, titreAnnonce, descriptionAnnonce,  imageUrlAnnonce ,  dateAnnonce )VALUES(" + ch + ")";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void update(Annonce t) {

        String sql = "UPDATE annonce SET  ";
        sql += "titreAnnonce =" + (t.getTitreAnnonce() == null ? "" + null + "" : "'" + t.getTitreAnnonce() + "'");

        sql += ",descriptionAnnonce=" + (t.getDescriptionAnnonce() == null ? "" + null + "" : "'" + t.getDescriptionAnnonce() + "'");

        sql += ", imageUrlAnnonce=" + (t.getImageUrlAnnonce() == null ? "" + null + "" : "'" + t.getImageUrlAnnonce() + "'");

        sql += " WHERE id=" + t.getId();

        System.out.println("updata" + sql);

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void delete(Annonce t) {

        String sql = "DELETE FROM annonce WHERE id=" + t.getId();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Annonce rowToUser(ResultSet rs) throws SQLException {
        Annonce annonce = new Annonce();

        annonce.setId(rs.getInt(1));
        annonce.setPosteur(rs.getInt(2));
        annonce.setTitreAnnonce(rs.getString(3));
        annonce.setDescriptionAnnonce(rs.getString(4));
        annonce.setImageUrlAnnonce(rs.getString(5));
        annonce.setDateAnnonce(rs.getString(6));

        return annonce;
    }

}
