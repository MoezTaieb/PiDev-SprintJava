/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.DAO;

import Communication.entity.Message;
import bd.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fedi
 */
public class MessageDao {

    Connection con = DataBase.getInstance();
    private Statement stmt;

    public MessageDao() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void AjouterMessage(Message c) {
        String req = "insert into msg(emetteur_id,recepteur_id,subject,body,piece,lu,DateEnvoi)  values (?,?,?,?,?,?,?)";
        PreparedStatement pre;
        try {
            pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);

            pre.setInt(1, c.getEmetteur_id());
            pre.setInt(2, c.getRecepteur_id());
            pre.setString(3, c.getSubject());
            pre.setString(4, c.getBody());
            pre.setString(5, c.getPiece());
            pre.setInt(6, c.getLu());
            pre.setString(7, c.getDateEnvoi());

            pre.executeUpdate();
            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                c.setId(generatedKeys.getInt(1));
            }
            System.out.println("Message ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Message> GetAllMessageByEmeteur(int id) {
        List<Message> listP = new ArrayList<Message>();

        try {
            String req = "select * from msg where emetteur_id=? or  recepteur_id=? order by id ";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setInt(1, id);

            pre.setInt(2, id);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message c = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                listP.add(c);
            }
            return listP;

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;

    }

    public List<Message> GetAllMessageByRecepteur(int id) {
        List<Message> listP = new ArrayList<Message>();

        try {
            String req = "select * from msg where recepteur_id=?";
            PreparedStatement pre;
            pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message c = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                listP.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public Message getMessageById(int id) {
        Message u = null;

        try {
            String req = "SELECT * FROM msg WHERE id = ?";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                u = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));

                System.out.println("Message trouvé !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public Message getMessageByTitre(String c) {
        Message u = null;

        try {
            String req = "SELECT * FROM msg WHERE body = ?";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setString(1, c);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                u = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));

                System.out.println("Message trouvé !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void ModifierMessage(int id, int lu) {
        try {
            String req = "update Message set lu=? where id=?";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setInt(1, lu);
            pre.setInt(2, id);
            pre.executeUpdate();

            System.out.println("Message modifié");

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeMessage(Message c) {
        try {
            String req = "delete from msg where id=?";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setInt(1, c.getId());
            pre.executeUpdate();

            System.out.println("Message modifié");

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int GetCountReceived(int id) {
        int a = 0;
        try {
            List<Message> listP = new ArrayList<Message>();
            String req = "select * from msg where recepteur_id=?";
            PreparedStatement pre;

            pre = con.prepareStatement(req);

            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message c = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                listP.add(c);
            }
            a = listP.size();

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int GetCountSended(int id) {
        int a = 0;
        try {
            List<Message> listP = new ArrayList<Message>();
            String req = "select * from msg where emetteur_id=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message c = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                listP.add(c);
            }
            a = listP.size();

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
