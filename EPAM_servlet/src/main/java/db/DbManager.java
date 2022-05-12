package db;

import entity.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class for managing database
 * Provides standard operations : create, update, delete, insert and some additional ones
 * @author Sitnikov Dmitriy
 */
public class DbManager {
    /**
     * Returns master by its email
     * If database does not contain such, returns null
     */
    public Master getMasterByEmail(String email) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM masters WHERE email = ?;")) {
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                Master master = new Master(
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("firstname"),
                        set.getString("surname"),
                        set.getString("phone"),
                        set.getString("information"),
                        set.getInt("completedOrdersCount"),
                        set.getInt("starsCount"));
                set.close();
                return master;
            } else {
                set.close();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    /**
     * Adds new master into the database
     * @throws SQLException if master already contains in the database
     */
    public void addMaster(Master master) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO masters (email, password, firstname, surname, phone, information, completedOrdersCount, starsCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?);")) {
            stmt.setString(1, master.getEmail());
            stmt.setString(2, master.getPassword());
            stmt.setString(3, master.getFirstname());
            stmt.setString(4, master.getSurname());
            stmt.setString(5, master.getPhone());
            stmt.setString(6, master.getInformation());
            stmt.setInt(7, master.getCompletedOrdersCount());
            stmt.setInt(8, master.getStarsCount());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    /**
     * Updates (saves) master in database
     * @throws SQLException if master does not contain in database
     */
    public void updateMaster(Master master) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE masters SET password = ?, firstname = ?, surname = ?, phone = ?, information = ?, completedOrdersCount = ?, starsCount = ? WHERE email = ?;")) {
            stmt.setString(1, master.getPassword());
            stmt.setString(2, master.getFirstname());
            stmt.setString(3, master.getSurname());
            stmt.setString(4, master.getPhone());
            stmt.setString(5, master.getInformation());
            stmt.setInt(6, master.getCompletedOrdersCount());
            stmt.setInt(7, master.getStarsCount());
            stmt.setString(8, master.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Deletes master by its email
     */
    public void deleteMasterByEmail(String masterEmail) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM masters WHERE email = ?;")) {
            stmt.setString(1, masterEmail);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns list, containing all masters from the database
     */
    public ArrayList<Master> getMasters() throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM masters;")) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Master> result = new ArrayList<>();

            while (set.next()) {
                result.add(new Master(
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("firstname"),
                        set.getString("surname"),
                        set.getString("phone"),
                        set.getString("information"),
                        set.getInt("completedOrdersCount"),
                        set.getInt("starsCount")));
            }
            set.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns user by its email
     * If database does not contain such, returns null
     */
    public User getUserByEmail(String email) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE email = ?;")) {
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                User user = new User(
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("firstname"),
                        set.getString("surname"),
                        set.getString("phone"),
                        set.getInt("balance"));
                set.close();
                return user;
            } else {
                set.close();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Adds new user into the database
     * @throws SQLException if user already contains in the database
     */
    public void addUser(User user) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users (email, password, firstname, surname, phone, balance) VALUES (?, ?, ?, ?, ?, ?);")) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstname());
            stmt.setString(4, user.getSurname());
            stmt.setString(5, user.getPhone());
            stmt.setInt(6, user.getBalance());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    /**
     * Updates (saves) user in database
     * @throws SQLException if master does not contain in database
     */
    public void updateUser(User user) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE users SET password = ?, firstname = ?, surname = ?, phone = ?, balance = ? WHERE email = ?;")) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getFirstname());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getPhone());
            stmt.setInt(5, user.getBalance());
            stmt.setString(6, user.getEmail());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Deletes user by its email
     */
    public void deleteUserByEmail(String userEmail) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE email = ?;")) {
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns list, containing users from database, whose name, surname or email address matches search string
     * @param searchString - search string, which may contain user data
     */
    public ArrayList<User> getUsers(String searchString) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users;")) {
            ResultSet set = stmt.executeQuery();
            ArrayList<User> result = new ArrayList<>();
            String[] tokens = (searchString==null) ? new String[0] : searchString.trim().split("\\s+");

            while (set.next()) {
                boolean addCurrent = true;
                for (String token : tokens) {
                    if (token.isEmpty()) continue;
                    if (set.getString("email").toLowerCase().contains(token.toLowerCase())) continue;
                    if (set.getString("firstname").toLowerCase().contains(token.toLowerCase())) continue;
                    if (set.getString("surname").toLowerCase().contains(token.toLowerCase())) continue;
                    addCurrent = false;
                    break;
                }
                if (!addCurrent) continue;

                result.add(new User(
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("firstname"),
                        set.getString("surname"),
                        set.getString("phone"),
                        set.getInt("balance")));
            }
            set.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns manager by its email
     * If database does not contain such, returns null
     */
    public Manager getManagerByEmail(String email) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM managers WHERE email = ?;")) {
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                Manager manager = new Manager(
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("firstname"),
                        set.getString("surname"));
                set.close();
                return manager;
            } else {
                set.close();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    /**
     * Adds new manager into the database
     * @throws SQLException if manager already contains in the database
     */
    public void addManager(Manager manager) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO managers (email, password, firstname, surname) VALUES (?, ?, ?, ?);")) {
            stmt.setString(1, manager.getEmail());
            stmt.setString(2, manager.getPassword());
            stmt.setString(3, manager.getFirstname());
            stmt.setString(4, manager.getSurname());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Adds new order into the database
     * @throws SQLException if order already contains in the database
     */
    public void addOrder(Order order) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO orders (title, description, cost, state, creationTime, reviewText, reviewStars, userEmail, masterEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            stmt.setString(1, order.getTitle());
            stmt.setString(2, order.getDescription());
            stmt.setInt(3, order.getCost());
            stmt.setInt(4, order.getState().toInt());
            stmt.setDate(5, order.getCreationTime());
            stmt.setString(6, order.getReviewText());
            stmt.setInt(7, order.getReviewStars());
            stmt.setString(8, order.getUserEmail());
            stmt.setString(9, order.getMasterEmail());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns list of orders, which was booked by given master
     * @param masterEmail - email of master
     */
    public ArrayList<Order> getOrdersByMasterEmail(String masterEmail) throws SQLException {
        String query = "SELECT * FROM orders WHERE masterEmail = ? ORDER BY creationTime;";

        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, masterEmail);
            ResultSet set = stmt.executeQuery();
            ArrayList<Order> result = extractOrders(set);
            set.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns list of orders, created by given user
     * @param userEmail - email of user
     */
    public ArrayList<Order> getOrdersByUserEmail(String userEmail) throws SQLException {
        String query = "SELECT * FROM orders WHERE userEmail = ? ORDER BY creationTime;";

        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, userEmail);
            ResultSet set = stmt.executeQuery();
            ArrayList<Order> result = extractOrders(set);
            set.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns list of orders from database
     * @param searchString - contains master email to filter result list
     * @param orderState - contains state of order to filter result list
     * @param orderSort - contains criteria of ordering the result list
     * @param invertedSort - provides inverted ordering if true
     */
    public ArrayList<Order> getOrders(String searchString, OrderState orderState, OrderSort orderSort, boolean invertedSort) throws SQLException {

        String query = "SELECT * FROM orders ";
        if (orderState != null) query += "WHERE state = "+orderState.toInt();
        query += " ORDER BY "+(orderSort==OrderSort.BY_COST?"cost":"creationTime");
        query += invertedSort?" DESC;":" ASC;";




        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Order> result = extractOrders(set);
            set.close();

            if (searchString != null && !searchString.isEmpty()) {
                result.removeIf(order -> order.getMasterEmail() == null);
                result.removeIf(order -> !order.getMasterEmail().toLowerCase().contains(searchString.trim().toLowerCase()));
            }

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Returns orders by its id
     * If database does not contain such order, returns null
     * @param id - id of order
     */
    public Order getOrderByEmail(int id) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM orders WHERE id = ?;")) {
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();

            ArrayList<Order> orders = extractOrders(set);
            set.close();

            return orders.isEmpty() ? null : orders.get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Updates (saves) order in database
     * @throws SQLException if order does not contain in database
     */
    public void updateOrder(Order order) throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE orders SET title = ?, description = ?, cost = ?, state = ?, creationTime = ?, reviewText = ?, reviewStars = ?, userEmail = ?, masterEmail = ? WHERE id = ?;")) {
            stmt.setString(1, order.getTitle());
            stmt.setString(2, order.getDescription());
            stmt.setInt(3, order.getCost());
            stmt.setInt(4, order.getState().toInt());
            stmt.setDate(5, order.getCreationTime());
            stmt.setString(6, order.getReviewText());
            stmt.setInt(7, order.getReviewStars());
            stmt.setString(8, order.getUserEmail());
            stmt.setString(9, order.getMasterEmail());
            stmt.setInt(10, order.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Deletes all data in database
     */
    public void clear() throws SQLException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt1 = con.prepareStatement("DELETE FROM orders WHERE TRUE;");
            PreparedStatement stmt2 = con.prepareStatement("DELETE FROM masters WHERE TRUE;");
            PreparedStatement stmt3 = con.prepareStatement("DELETE FROM managers WHERE TRUE;");
            PreparedStatement stmt4 = con.prepareStatement("DELETE FROM users WHERE TRUE;")) {

            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt4.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private static DbManager instance = null;

    public static DbManager getInstance() {
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            instance = new DbManager();
        }
        return instance;
    }

    private ArrayList<Order> extractOrders(ResultSet set) throws SQLException {
        ArrayList<Order> result = new ArrayList<>();

        while (set.next()) {
            result.add(new Order(
                    set.getInt("id"),
                    set.getString("title"),
                    set.getString("description"),
                    set.getInt("cost"),
                    OrderState.fromInt(set.getInt("state")),
                    set.getDate("creationTime"),
                    set.getString("reviewText"),
                    set.getInt("reviewStars"),
                    set.getString("userEmail"),
                    set.getString("masterEmail")));
        }

        return result;
    }
}
