package servlets;

import db.DbManager;
import entity.*;
import localization.Lang;
import localization.SiteText;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/controller", name = "controlServlet")
public class Controller  extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            execute(req, resp);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.log(Level.ERROR, exception);
            logout(req, resp, SiteText.getInstance().getText("error_message", (Lang)req.getSession().getAttribute("lg")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            execute(req, resp);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.log(Level.ERROR, exception);
            logout(req, resp, SiteText.getInstance().getText("error_message", (Lang)req.getSession().getAttribute("lg")));
        }
    }

    protected void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, NoSuchMethodException {
        String action = req.getParameter("action");

        if (List.of("showUserOrders", "showUserEdit", "payUserOrder", "cancelUserOrder", "editUser", "giveFeedback", "createOrder", "showReviewCreate", "showUserOrderCreate").contains(action)) {
            Object user = req.getSession().getAttribute("loggedUser");
            if (!(user instanceof User)){
                logout(req, resp, SiteText.getInstance().getText("session_expired", (Lang)req.getSession().getAttribute("lg")));
                return;
            }
        }

        if (List.of("setOrdersFilter", "showManagerOrders", "showManagerOrderEdit", "editOrder", "showMasters", "chooseMaster", "showManagerMaster", "showUsers", "setUsersFilter", "changeUserBalance", "setMaster").contains(action)) {
            Object manager = req.getSession().getAttribute("loggedManager");
            if (!(manager instanceof Manager)) {
                logout(req, resp, SiteText.getInstance().getText("session_expired", (Lang)req.getSession().getAttribute("lg")));
                return;
            }
        }

        if (List.of("editMaster", "showMasterEdit", "showMasterOrders", "startOrder", "finishOrder").contains(action)) {
            Object master = req.getSession().getAttribute("loggedMaster");
            if (!(master instanceof Master)) {
                logout(req, resp, SiteText.getInstance().getText("session_expired", (Lang)req.getSession().getAttribute("lg")));
                return;
            }
        }

        switch (action) {
            case "registerUser" : registerUser(req, resp); break;
            case "registerMaster" : registerMaster(req, resp); break;
            case "login" : login(req, resp); break;
            case "logout" : logout(req, resp); break;
            case "changeLanguage" : changeLanguage(req, resp); break;

            case "showUserOrders" : showUserOrders(req, resp); break;
            case "showUserEdit" : showUserEdit(req, resp); break;
            case "payUserOrder" : payUserOrder(req, resp); break;
            case "cancelUserOrder" : cancelUserOrder(req, resp); break;
            case "editUser" : editUser(req, resp); break;
            case "giveFeedback" : giveFeedback(req, resp); break;
            case "createOrder" : createOrder(req, resp); break;
            case "showReviewCreate" : showReviewCreate(req, resp); break;
            case "showUserOrderCreate" : showUserOrderCreate(req, resp); break;

            case "setOrdersFilter" : setOrdersFilter(req, resp); break;
            case "showManagerOrders" : showManagerOrders(req, resp); break;
            case "showManagerOrderEdit" : showManagerOrderEdit(req, resp); break;
            case "editOrder" : editOrder(req, resp); break;
            case "showMasters" : showMasters(req, resp); break;
            case "chooseMaster" : chooseMaster(req, resp); break;
            case "showManagerMaster" : showManagerMaster(req, resp); break;
            case "showUsers" : showUsers(req, resp); break;
            case "setUsersFilter" : setUsersFilter(req, resp); break;
            case "changeUserBalance" : changeUserBalance(req, resp); break;
            case "setMaster" : setMaster(req, resp); break;

            case "editMaster" : editMaster(req, resp); break;
            case "showMasterEdit" : showMasterEdit(req, resp); break;
            case "showMasterOrders" : showMasterOrders(req, resp); break;
            case "startOrder" : startOrder(req, resp); break;
            case "finishOrder" : finishOrder(req, resp); break;

            default : throw new NoSuchMethodException("Unknown command!");
        }


    }

    protected void showUserOrderCreate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.sendRedirect("pages/user_order_creation_page.jsp");
    }

    protected void changeLanguage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        if ("RU".equals(req.getParameter("lang"))) {
            req.getSession().setAttribute("lg", Lang.RU);
        } else {
            req.getSession().setAttribute("lg", Lang.EN);
        }
        resp.sendRedirect(getServletContext().getContextPath());
    }

    protected void showReviewCreate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.sendRedirect("pages/user_review_page.jsp?id="+req.getParameter("id"));
    }

    protected void registerUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");

        if (DbManager.getInstance().getUserByEmail(email) != null) {
            resp.sendRedirect("pages/user_registration_page.jsp?emailInvalid=true");
            return;
        }

        if (email == null || password == null || firstname == null || surname == null || phone == null) throw new AssertionError("Empty parameters!");

        User user = new User(email, password, firstname, surname, phone, 0);
        DbManager.getInstance().addUser(user);
        req.getSession().setAttribute("loggedUser", user);
        showUserOrders(req, resp);
    }

    protected void editUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");

        if (password == null || firstname == null || surname == null || phone == null) throw new AssertionError("Empty parameters!");

        User oldUser = (User)req.getSession().getAttribute("loggedUser");
        User newUser = new User(oldUser.getEmail(), password, firstname, surname, phone, oldUser.getBalance());

        DbManager.getInstance().updateUser(newUser);
        req.getSession().setAttribute("loggedUser", newUser);
        showUserEdit(req, resp);
    }


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = DbManager.getInstance().getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("loggedUser", user);
            showUserOrders(req, resp);
            return;
        }

        Master master = DbManager.getInstance().getMasterByEmail(email);
        if (master != null && master.getPassword().equals(password)) {
            req.getSession().setAttribute("loggedMaster", master);
            showMasterOrders(req, resp);
            return;
        }

        Manager manager = DbManager.getInstance().getManagerByEmail(email);
        if (manager != null && manager.getPassword().equals(password)) {
            req.getSession().setAttribute("loggedManager", manager);
            showManagerOrders(req, resp);
            return;
        }

        resp.sendRedirect("pages/login_page.jsp?invalid=true");
    }


    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("loggedUser", null);
        req.getSession().setAttribute("loggedMaster", null);
        req.getSession().setAttribute("loggedManager", null);
        resp.sendRedirect(req.getContextPath());
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException, ServletException {
        req.setAttribute("message", message);
        req.getSession().setAttribute("loggedUser", null);
        req.getSession().setAttribute("loggedMaster", null);
        req.getSession().setAttribute("loggedManager", null);
        req.getRequestDispatcher("").forward(req, resp);

        //resp.sendRedirect(req.getContextPath());
    }

    protected void showUserOrders(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        User user = (User)req.getSession().getAttribute("loggedUser");

        ArrayList<Order> orders = DbManager.getInstance().getOrdersByUserEmail(user.getEmail());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("pages/user_orders_page.jsp").forward(req, resp);
    }

    protected void showUserEdit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.sendRedirect("pages/user_edit_page.jsp");
    }


    protected void payUserOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));

        Order oldOrder = DbManager.getInstance().getOrderByEmail(id);
        User oldUser = (User)req.getSession().getAttribute("loggedUser");

        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), OrderState.PAID, oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), oldOrder.getMasterEmail());
        User newUser = new User(oldUser.getEmail(), oldUser.getPassword(), oldUser.getFirstname(), oldUser.getSurname(), oldUser.getPhone(), oldUser.getBalance() - oldOrder.getCost());

        DbManager.getInstance().updateOrder(newOrder);
        DbManager.getInstance().updateUser(newUser);
        req.getSession().setAttribute("loggedUser", newUser);
        showUserOrders(req, resp);
    }

    protected void cancelUserOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Order oldOrder = DbManager.getInstance().getOrderByEmail(id);

        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), OrderState.CANCELED, oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), oldOrder.getMasterEmail());
        DbManager.getInstance().updateOrder(newOrder);
        showUserOrders(req, resp);
    }


    protected void giveFeedback(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        int reviewStars = Integer.parseInt(req.getParameter("reviewStars"));
        String reviewText = req.getParameter("reviewText");


        Order oldOrder = DbManager.getInstance().getOrderByEmail(id);
        Master oldMaster = DbManager.getInstance().getMasterByEmail(oldOrder.getMasterEmail());


        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), oldOrder.getState(), oldOrder.getCreationTime(), reviewText, reviewStars, oldOrder.getUserEmail(), oldOrder.getMasterEmail());
        Master newMaster = new Master(oldMaster.getEmail(), oldMaster.getPassword(), oldMaster.getFirstname(), oldMaster.getSurname(), oldMaster.getPhone(), oldMaster.getInformation(), oldMaster.getCompletedOrdersCount()+1, oldMaster.getStarsCount()+reviewStars);


        DbManager.getInstance().updateOrder(newOrder);
        DbManager.getInstance().updateMaster(newMaster);
        showUserOrders(req, resp);
    }

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        User user = (User)req.getSession().getAttribute("loggedUser");
        Order order = new Order(-1, title, description, -1, OrderState.WAIT_FOR_PAYMENT, Date.valueOf(LocalDate.now()), "", -1, user.getEmail(), null);

        DbManager.getInstance().addOrder(order);
        showUserOrders(req, resp);
    }






    protected void setOrdersFilter(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String orderCriteria = req.getParameter("orderCriteria");
        String orderType = req.getParameter("orderType");
        String filterState = req.getParameter("filterState");
        String searchText = req.getParameter("searchText");

        if (orderCriteria == null || orderType == null || filterState == null) throw new NullPointerException("Empty parameters!");

        OrderState orderState = switch (filterState) {
            case "IN_WORK" -> OrderState.IN_WORK;
            case "WAIT_FOR_PAYMENT" -> OrderState.WAIT_FOR_PAYMENT;
            case "PAID" -> OrderState.PAID;
            case "CANCELED" -> OrderState.CANCELED;
            case "COMPLETED" -> OrderState.COMPLETED;
            default -> null;
        };

        req.getSession().setAttribute("orderCriteria", orderCriteria.equals("cost")?OrderSort.BY_COST:OrderSort.BY_DATE);
        req.getSession().setAttribute("invertedSort", orderType.equals("inverted"));
        req.getSession().setAttribute("filterState", orderState);
        req.getSession().setAttribute("searchText", searchText);

        showManagerOrders(req, resp);
    }


    protected void showManagerOrders(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        OrderSort orderCriteria = (OrderSort) req.getSession().getAttribute("orderCriteria");
        Boolean invertedSort = (Boolean) req.getSession().getAttribute("invertedSort");
        OrderState filterState = (OrderState) req.getSession().getAttribute("filterState");
        String searchText = (String) req.getSession().getAttribute("searchText");

        if (invertedSort == null) invertedSort = false;
        ArrayList<Order> orders = DbManager.getInstance().getOrders(searchText, filterState, orderCriteria, invertedSort);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("pages/manager_orders_page.jsp").forward(req, resp);
    }

    protected void showManagerOrderEdit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String idText = req.getParameter("id");

        if (idText != null) {
            Order order = DbManager.getInstance().getOrderByEmail(Integer.parseInt(idText));
            req.getSession().setAttribute("selectedOrder", order);
        }

        req.getRequestDispatcher("pages/manager_order_edit_page.jsp").forward(req, resp);
    }

    protected void editOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String costText = req.getParameter("cost");
        String stateText = req.getParameter("state");
        Order oldOrder = (Order)req.getSession().getAttribute("selectedOrder");

        int cost = (int)(Double.parseDouble(costText)*100.0);

        OrderState state = switch (stateText) {
            case "WAIT_FOR_PAYMENT" -> OrderState.WAIT_FOR_PAYMENT;
            case "PAID" -> OrderState.PAID;
            case "CANCELED" -> OrderState.CANCELED;
            default -> oldOrder.getState();
        };

        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), cost, state, oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), oldOrder.getMasterEmail());

        DbManager.getInstance().updateOrder(newOrder);
        req.getSession().setAttribute("selectedOrder", newOrder);

        showManagerOrderEdit(req, resp);
    }

    protected void showMasters(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        ArrayList<Master> masters = DbManager.getInstance().getMasters();
        req.setAttribute("masters", masters);
        req.getRequestDispatcher("pages/manager_masters_page.jsp").forward(req, resp);
    }


    protected void chooseMaster(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        Order oldOrder = (Order) req.getSession().getAttribute("selectedOrder");
        String masterEmail = req.getParameter("email");
        Master master = DbManager.getInstance().getMasterByEmail(masterEmail);


        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), oldOrder.getState(), oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), master.getEmail());
        DbManager.getInstance().updateOrder(newOrder);
        req.getSession().setAttribute("selectedOrder", newOrder);

        req.getRequestDispatcher("pages/manager_orders_page.jsp").forward(req, resp);
    }

    protected void showManagerMaster(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String masterEmail = req.getParameter("email");
        Master master = DbManager.getInstance().getMasterByEmail(masterEmail);
        ArrayList<Order> orders = DbManager.getInstance().getOrdersByMasterEmail(master.getEmail());
        orders.removeIf(order -> order.getReviewStars() <= 0);

        req.setAttribute("master", master);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("pages/manager_master_page.jsp").forward(req, resp);
    }

    protected void showUsers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String searchText = (String)req.getSession().getAttribute("searchText");
        ArrayList<User> users = DbManager.getInstance().getUsers(searchText);

        req.setAttribute("users", users);
        req.getRequestDispatcher("pages/manager_users_page.jsp").forward(req, resp);
    }

    protected void setUsersFilter(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String searchString = req.getParameter("searchString");
        req.getSession().setAttribute("searchText", searchString);

        showUsers(req, resp);
    }

    protected void changeUserBalance(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String userEmail = req.getParameter("email");
        String balanceText = req.getParameter("balance");
        User oldUser = DbManager.getInstance().getUserByEmail(userEmail);
        int balance = (int)(Double.parseDouble(balanceText)*100.0) + oldUser.getBalance();

        User newUser = new User(oldUser.getEmail(), oldUser.getPassword(), oldUser.getFirstname(), oldUser.getSurname(), oldUser.getPhone(), balance);

        DbManager.getInstance().updateUser(newUser);

        showUsers(req, resp);
    }

    protected void setMaster(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String masterEmail = req.getParameter("email");
        Master master = DbManager.getInstance().getMasterByEmail(masterEmail);

        Order oldOrder = (Order)req.getSession().getAttribute("selectedOrder");
        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), oldOrder.getState(), oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), master.getEmail());

        DbManager.getInstance().updateOrder(newOrder);
        req.getSession().setAttribute("selectedOrder", newOrder);

        showManagerOrderEdit(req, resp);
    }






    protected void registerMaster(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String information = req.getParameter("information");

        if (DbManager.getInstance().getUserByEmail(email) != null) {
            resp.sendRedirect("pages/user_registration_page.jsp?emailInvalid=true");
            return;
        }

        if (email == null || password == null || firstname == null || surname == null || phone == null || information == null) return;

        Master master = new Master(email, password, firstname, surname, phone, information, 0, 0);

        DbManager.getInstance().addMaster(master);
        req.getSession().setAttribute("loggedMaster", master);
        showMasterOrders(req, resp);
    }

    protected void editMaster(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String information = req.getParameter("information");

        if (password == null || firstname == null || surname == null || phone == null || information == null) throw new NullPointerException("Empty parameters!");

        Master oldMaster = (Master)req.getSession().getAttribute("loggedMaster");
        Master newMaster = new Master(oldMaster.getEmail(), password, firstname, surname, phone, information, oldMaster.getCompletedOrdersCount(), oldMaster.getStarsCount());

        DbManager.getInstance().updateMaster(newMaster);
        req.getSession().setAttribute("loggedMaster", newMaster);
        showMasterEdit(req, resp);
    }

    protected void showMasterEdit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        resp.sendRedirect("pages/master_edit_page.jsp");
    }

    protected void showMasterOrders(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        Master master = (Master)req.getSession().getAttribute("loggedMaster");

        ArrayList<Order> orders = DbManager.getInstance().getOrdersByMasterEmail(master.getEmail());

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("pages/master_orders_page.jsp").forward(req, resp);
    }

    protected void startOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String orderIdText = req.getParameter("id");
        int orderId = Integer.parseInt(orderIdText);
        Order oldOrder = DbManager.getInstance().getOrderByEmail(orderId);

        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), OrderState.IN_WORK, oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), oldOrder.getMasterEmail());

        DbManager.getInstance().updateOrder(newOrder);

        showMasterOrders(req, resp);
    }


    protected void finishOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String orderIdText = req.getParameter("id");
        int orderId = Integer.parseInt(orderIdText);
        Order oldOrder = DbManager.getInstance().getOrderByEmail(orderId);

        Order newOrder = new Order(oldOrder.getId(), oldOrder.getTitle(), oldOrder.getDescription(), oldOrder.getCost(), OrderState.COMPLETED, oldOrder.getCreationTime(), oldOrder.getReviewText(), oldOrder.getReviewStars(), oldOrder.getUserEmail(), oldOrder.getMasterEmail());

        DbManager.getInstance().updateOrder(newOrder);

        showMasterOrders(req, resp);
    }

}