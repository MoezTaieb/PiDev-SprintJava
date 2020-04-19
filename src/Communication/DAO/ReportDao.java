/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.DAO;

import Communication.entity.Report;
import Communication.entity.Report;
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
public class ReportDao implements Dao<Report> {

    public int nbReport(Report t) {

        int nb = 0;

        String sql = "SELECT count( DISTINCT  annonce_id, reporteur_id) FROM report WHERE annonce_id=" + t.getAnnonce();

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

    public List<String> mots() {

        ArrayList al = new ArrayList<String>();

        String sql = "SELECT * FROM `mauvais_mots`";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name

                al.add(rs.getString(2));

            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }

    @Override
    public Report get(long id) {

        return null;

    }

    @Override
    public List<Report> getAll() {

        return null;
    }

    @Override
    public void save(Report t) {

        String sql = "INSERT INTO `report`(`annonce_id`, `reporteur_id`) VALUES (" + t.getAnnonce() + "," + t.getReporteur() + ")";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Report t) {

    }

    @Override
    public void delete(Report t) {

        String sql = "DELETE FROM report WHERE annonce_id=" + t.getAnnonce();

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
