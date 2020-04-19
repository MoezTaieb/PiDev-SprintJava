/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.DAO;

import bd.Dao;
import bd.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.entity.User;

/**
 *
 * @author fedi
 */
public class UserDao implements Dao<User> {

    @Override
    public User get(long id) {

        User user = null;

        String sql = "SELECT * FROM fos_user where id=" + id;

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                user = rowToUser(rs);

                System.err.println(user.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;

    }

    public User getByEmail(String email) {

        User user = null;

        String sql = "SELECT * FROM fos_user where email='" + email + "'";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                user = rowToUser(rs);
                System.err.println(user.toString());

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;

    }

    public User getByUsername(String username) {

        User user = null;

        String sql = "SELECT * FROM fos_user where username='" + username + "'";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                //Retrieve by column name

                user = rowToUser(rs);
            }
            rs.close();
        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    public User rowToUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setid(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setSalt(rs.getString("salt"));
        user.setPassword(rs.getString("password"));
        user.setLastLogin(rs.getString("last_login"));
        user.setRoles(rs.getString("roles"));
        user.setCinUser(rs.getString("cinUser"));
        user.setNomUser(rs.getString("nomUser"));
        user.setPrenomUser(rs.getString("prenomUser"));
        user.setAdresseUser(rs.getString("adresseUser"));
        user.setTelUser(rs.getString("telUser"));
        user.setImageUrlUser(rs.getString("imageUrlUser"));
        user.setTypeUser(rs.getString("typeUser"));
        user.setResetCode(rs.getInt("resetCode"));
        user.setResetNumAttempts(rs.getInt("resetNumAttempts"));

        return user;
    }

    @Override
    public List<User> getAll() {
        ArrayList list = new ArrayList<User>();

        String sql = "SELECT * FROM fos_user";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            ResultSet rs;

            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                User user = rowToUser(rs);

                System.err.println(user.toString());

                list.add(user);

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override

    public void save(User t) {

        String ch = "";

        ch += t.getUsername() == null ? "" + null + "" : "'" + t.getUsername() + "'";
        ch += t.getUsername() == null ? "," + null + "" : ",'" + t.getUsername() + "'";
        ch += t.getEmail() == null ? "," + null + "" : ",'" + t.getEmail() + "'";
        ch += t.getEmail() == null ? "," + null + "" : ",'" + t.getEmail() + "'";
        ch += t.isEnabled() == true ? "," + 1 + "" : "" + 0 + "";
        ch += t.getPassword() == null ? "," + null + "" : ",'" + t.getPassword() + "'";
        ch += t.getLastLogin() == null ? "," + null + "" : ",'" + t.getLastLogin() + "'";
        ch += t.getRoles() == null ? "," + null + "" : ",'" + t.getRoles() + "'";
        ch += t.getCinUser() == null ? "," + null + "" : ",'" + t.getCinUser() + "'";
        ch += t.getNomUser() == null ? "," + null + "" : ",'" + t.getNomUser() + "'";
        ch += t.getPrenomUser() == null ? "," + null + "" : ",'" + t.getPrenomUser() + "'";
        ch += t.getAdresseUser() == null ? "," + null + "" : ",'" + t.getAdresseUser() + "'";
        ch += t.getTelUser() == null ? "," + null + "" : ",'" + t.getTelUser() + "'";
        String sql = "INSERT INTO fos_user(username,username_canonical,email,email_canonical,enabled,	password,last_login,roles,cinUser,nomUser,prenomUser,adresseUser,telUser)VALUES(" + ch + ")";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void update(User t) {
        System.out.println("update" + t);
        String ch = "";
        ch += "id=" + t.getid() + "";
        ch += t.getUsername() == null ? ",username=" + null + "" : ",username='" + t.getUsername() + "'";
        ch += t.getUsername() == null ? ",username_canonical=" + null + "" : ",username_canonical='" + t.getUsername() + "'";
        ch += t.getEmail() == null ? ",email=" + null + "" : ",email='" + t.getEmail() + "'";
        ch += t.getEmail() == null ? ",email_canonical=" + null + "" : ",email_canonical='" + t.getEmail() + "'";
        ch += t.isEnabled() == true ? ",enabled=" + 1 + "" : ",enabled=" + 0 + "";
        ch += t.getSalt() == null ? ",salt=" + null + "" : ",salt='" + t.getSalt() + "'";
        ch += t.getPassword() == null ? ",password=" + null + "" : ",password='" + t.getPassword() + "'";
        ch += t.getLastLogin() == null ? ",last_login=" + null + "" : ",last_login='" + t.getLastLogin() + "'";
        ch += t.getConfirmationToken() == null ? ",confirmation_token=" + null + "" : ",confirmation_token='" + t.getConfirmationToken() + "'";
        ch += t.getPasswordRequestedAt() == null ? ",password_requested_at=" + null + "" : ",password_requested_at='" + t.getPasswordRequestedAt() + "'";
        ch += t.getRoles() == null ? ",roles=" + null + "" : ",roles='" + t.getRoles() + "'";
        ch += t.getCinUser() == null ? ",cinUser=" + null + "" : ",cinUser='" + t.getCinUser() + "'";
        ch += t.getNomUser() == null ? ",nomUser=" + null + "" : ",nomUser='" + t.getNomUser() + "'";
        ch += t.getPrenomUser() == null ? ",prenomUser=" + null + "" : ",prenomUser='" + t.getPrenomUser() + "'";
        ch += t.getAdresseUser() == null ? ",adresseUser=" + null + "" : ",adresseUser='" + t.getAdresseUser() + "'";
        ch += t.getTelUser() == null ? ",telUser=" + null + "" : ",telUser='" + t.getTelUser() + "'";
        ch += t.getImageUrlUser() == null ? ",imageUrlUser=" + null + "" : ",imageUrlUser='" + t.getImageUrlUser() + "'";
        ch += t.getTypeUser() == null ? ",typeUser=" + null + "" : ",typeUser='" + t.getTypeUser() + "'";
        ch += ",resetCode=" + t.getResetCode();
        ch += ",resetNumAttempts=" + t.getResetNumAttempts();

        String sql = "UPDATE fos_user set " + ch + " WHERE id=" + t.getid() + " or email='" + t.getEmail() + "'";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void delete(User t) {

        String sql = "DELETE FROM fos_user WHERE id=" + t.getid() + " or email='" + t.getEmail() + "'";

        Statement stmt;

        try {
            stmt = DataBase.getInstance().createStatement();

            System.out.println(stmt.executeUpdate(sql));

        } catch (SQLException ex) {

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
