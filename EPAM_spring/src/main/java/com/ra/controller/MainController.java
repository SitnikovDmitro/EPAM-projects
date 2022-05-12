package com.ra.controller;

import com.ra.model.entity.Manager;
import com.ra.model.entity.Master;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.enums.OrderSort;
import com.ra.model.enums.OrderState;
import com.ra.model.enums.Lang;
import com.ra.model.repository.ManagerRepository;
import com.ra.model.repository.MasterRepository;
import com.ra.model.repository.OrderRepository;
import com.ra.model.repository.UserRepository;
import com.ra.model.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("")
    public String start() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String message, Model model, HttpSession session) {
        if (message != null) model.addAttribute("message", message);
        return "home";
    }

    @GetMapping("/changeLang")
    public String changeLang(@RequestParam String lang, Model model, HttpSession session) {
        session.setAttribute("lang", lang.equals("RU") ? Lang.RU : Lang.EN);
        return "redirect:/home";
    }

    @RequestMapping("/showLogin")
    public String showLogin(Model model, HttpSession session){
        return "login";
    }

    @RequestMapping("/master/showEdit")
    public String masterShowEdit(Model model, HttpSession session){
        Master loggedMaster = (Master)session.getAttribute("loggedMaster");
        logger.info(loggedMaster.toString());
        logger.info("id of session = "+session.getId());
        return "master/edit";
    }

    @RequestMapping("/master/showOrders")
    public String masterShowOrders(Model model, HttpSession session) {
        Master loggedMaster = (Master)session.getAttribute("loggedMaster");
        model.addAttribute("orders", orderRepository.getOrdersByMasterEmail(loggedMaster.getEmail()));
        return "master/orders";
    }




    @RequestMapping("/showUserRegistration")
    public String registrationUser(Model model){
        return "registration_user";
    }

    @RequestMapping("/registerMaster")
    public String registerMaster(@RequestParam String email, @RequestParam String password, @RequestParam String firstname, @RequestParam String surname, @RequestParam String phone, @RequestParam String information, Model model, HttpSession session){

        if (masterRepository.existsById(email) || userRepository.existsById(email) || managerRepository.existsById(email)) {
            model.addAttribute("invalid", true);
            return "forward:/showMasterRegistration";
        }

        Master master = new Master(email, password, firstname, surname, phone, information, 0, 0);
        session.setAttribute("loggedMaster", master);
        masterRepository.save(master);
        return "redirect:/master/showOrders";
    }

    @RequestMapping("/registerUser")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String firstname, @RequestParam String surname, @RequestParam String phone, Model model, HttpSession session){

        if (masterRepository.existsById(email) || userRepository.existsById(email) || managerRepository.existsById(email)) {
            model.addAttribute("invalid", true);
            return "forward:/showUserRegistration";
        }

        User user = new User(email, password, firstname, surname, phone, 0);
        session.setAttribute("loggedUser", user);
        userRepository.save(user);
        return "redirect:/user/showOrders";
    }

    @RequestMapping("/showMasterRegistration")
    public String showRegistrationMaster(Model model){
        return "registration_master";
    }

    @RequestMapping("/master/edit")
    public String masterEdit(@RequestParam String password, @RequestParam String firstname, @RequestParam String surname, @RequestParam String phone, @RequestParam String information, Model model, HttpSession session){
        Master loggedMaster = (Master)session.getAttribute("loggedMaster");
        Master master = new Master(loggedMaster.getEmail(), password, firstname, surname, phone, information, loggedMaster.getCompletedOrdersCount(), loggedMaster.getStarsCount());
        session.setAttribute("loggedMaster", master);
        masterRepository.save(master);
        return "redirect:/master/showEdit";
    }

    @PostMapping("/master/startOrder")
    public String masterStartOrder(@RequestParam String id, Model model, HttpSession session){
        Order order = orderRepository.findById(Integer.parseInt(id)).get();
        order.setState(OrderState.IN_WORK.toInt());
        orderRepository.save(order);
        return "redirect:/master/showOrders";
    }

    @PostMapping("/master/finishOrder")
    public String masterFinishOrder(@RequestParam String id, Model model, HttpSession session){
        Order order = orderRepository.findById(Integer.parseInt(id)).get();
        order.setState(OrderState.COMPLETED.toInt());
        orderRepository.save(order);

        return "redirect:/master/showOrders";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("loggedMaster", null);
        session.setAttribute("loggedUser", null);
        session.setAttribute("loggedManager", null);
        session.invalidate();
        return "redirect:/home";
    }





    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session){

        Optional<User> user = userRepository.findById(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            session.setAttribute("loggedUser", user.get());
            return "redirect:/user/showOrders";
        }

        Optional<Master> master =masterRepository.findById(email);
        if (master.isPresent() && master.get().getPassword().equals(password)) {
            session.setAttribute("loggedMaster", master.get());
            return "redirect:/master/showOrders";
        }

        Optional<Manager> manager = managerRepository.findById(email);
        if (manager.isPresent() && manager.get().getPassword().equals(password)) {
            session.setAttribute("loggedManager", manager.get());
            return "redirect:/manager/showOrders";
        }

        model.addAttribute("invalid", true);
        return "forward:/showLogin";
    }






    @RequestMapping("/user/showEdit")
    public String userShowEdit(Model model, HttpSession session){
        User loggedUser = (User)session.getAttribute("loggedUser");
        return "user/edit";
    }

    @RequestMapping("/user/showOrders")
    public String userShowOrders(Model model, HttpSession session) {
        User loggedUser = (User)session.getAttribute("loggedUser");
        model.addAttribute("orders", orderRepository.getOrdersByUserEmail(loggedUser.getEmail()));
        return "user/orders";
    }

    @RequestMapping("/user/showOrderCreate")
    public String userShowOrderCreate(Model model, HttpSession session) {
        return "user/order_create";
    }

    @RequestMapping("/user/showReviewCreate")
    public String userShowReviewCreate(@RequestParam String id, Model model, HttpSession session) {
        model.addAttribute("id", id);
        return "user/review_create";
    }

    @RequestMapping("/user/payOrder")
    public String userPayOrder(@RequestParam String id, Model model, HttpSession session) {
        Order order = orderRepository.findById(Integer.parseInt(id)).get();
        User user = (User)session.getAttribute("loggedUser");

        order.setState(OrderState.PAID.toInt());
        user.setBalance(user.getBalance()-order.getCost());

        orderRepository.save(order);
        userRepository.save(user);

        session.setAttribute("loggedUser", user);
        return "redirect:/user/showOrders";
    }

    @RequestMapping("/user/cancelOrder")
    public String userCancelOrder(@RequestParam String id, Model model, HttpSession session) {
        Order order = orderRepository.findById(Integer.parseInt(id)).get();
        order.setState(OrderState.CANCELED.toInt());
        orderRepository.save(order);
        return "redirect:/user/showOrders";
    }


    @PostMapping("/user/edit")
    public String userEdit(@RequestParam String password, @RequestParam String firstname, @RequestParam String surname, @RequestParam String phone, Model model, HttpSession session){
        User loggedUser = (User)session.getAttribute("loggedUser");
        User user = new User(loggedUser.getEmail(), password, firstname, surname, phone, loggedUser.getBalance());
        session.setAttribute("loggedUser", user);
        userRepository.save(user);
        return "redirect:/user/showEdit";
    }

    @PostMapping("/user/createOrder")
    public String userCreateOrder(@RequestParam String title, @RequestParam String description, Model model, HttpSession session){
        User loggedUser = (User)session.getAttribute("loggedUser");
        Order order = new Order(-1, title, description, -1, OrderState.WAIT_FOR_PAYMENT.toInt(), Date.valueOf(LocalDate.now()), -1, "", loggedUser, null);
        orderRepository.save(order);
        return "redirect:/user/showOrders";
    }

    @PostMapping("/user/createReview")
    public String userCreateReview(@RequestParam String id, @RequestParam String reviewStars, @RequestParam(defaultValue = "") String reviewText){
        Order order = orderRepository.findById(Integer.parseInt(id)).get();

        orderService.makeReview(order, order.getMaster(), reviewText, reviewStars);

        return "redirect:/user/showOrders";
    }






    @RequestMapping("/manager/showMaster")
    public String managerShowMaster(@RequestParam String email, Model model) {
        Master master = masterRepository.findById(email).get();
        List<Order> orders = orderRepository.getOrdersByMasterEmail(master.getEmail());
        orders.removeIf(order -> order.getReviewStars() <= 0);

        model.addAttribute("master", master);
        model.addAttribute("orders", orders);
        return "manager/master";
    }

    @RequestMapping("/manager/showMasters")
    public String managerShowMasters(Model model, HttpSession session) {
        model.addAttribute("masters", masterRepository.findAll());
        return "manager/masters";
    }

    @RequestMapping("/manager/showUsers")
    public String managerShowUsers(Model model, HttpSession session) {

        model.addAttribute("users", userRepository.findUsersBySearchString((String)session.getAttribute("searchString")));
        return "manager/users";
    }

    @RequestMapping("/manager/showOrders")
    public String managerShowOrders(@RequestParam(defaultValue = "1") String page, Model model, HttpSession session) {
        OrderSort orderCriteria = (OrderSort) session.getAttribute("orderCriteria");
        Boolean invertedSort = (Boolean) session.getAttribute("invertedSort");
        OrderState filterState = (OrderState) session.getAttribute("filterState");
        String searchText = (String) session.getAttribute("searchText");
        int pageNum = Integer.parseInt(page);

        List<Order> allOrders = orderRepository.getOrders(searchText, filterState, orderCriteria, invertedSort);
        List<Order> pageOrders = allOrders.subList(Math.max(pageNum*10-10, 0), Math.min(pageNum*10, allOrders.size()));
        model.addAttribute("orders", pageOrders);

        if (allOrders.size() > 10) {
            if (pageNum > 1)
                model.addAttribute("prev", 1);
            if (pageNum*10 < allOrders.size())
                model.addAttribute("next", pageNum + 1);
        }

        return "manager/orders";
    }

    @RequestMapping("/manager/showOrderEdit")
    public String managerShowOrderEdit(@RequestParam(required = false) String id, Model model, HttpSession session) {

        if (id != null) {
            Order order = orderRepository.findById(Integer.parseInt(id)).get();
            session.setAttribute("selectedOrder", order);
        }

        return "manager/order_edit";
    }

    @RequestMapping("/manager/editOrder")
    public String managerEditOrder(@RequestParam String cost, @RequestParam String state, Model model, HttpSession session) {

        Order order = (Order)session.getAttribute("selectedOrder");

        int cst = (int)(Double.parseDouble(cost)*100.0);

        order.setState(switch (state) {
            case "WAIT_FOR_PAYMENT" -> OrderState.WAIT_FOR_PAYMENT.toInt();
            case "PAID" -> OrderState.PAID.toInt();
            case "CANCELED" -> OrderState.CANCELED.toInt();
            default -> order.getState();
        });

        order.setCost(cst);

        orderRepository.save(order);

        session.setAttribute("selectedOrder", order);

        return "redirect:/manager/showOrderEdit";
    }

    @RequestMapping("/manager/setUsersFilter")
    public String managerSetUsersFilter(@RequestParam(required = false) String searchString, HttpSession session) {
        session.setAttribute("searchString", searchString);
        return "redirect:/manager/showUsers";
    }

    @RequestMapping("/manager/setOrdersFilter")
    public String managerSetOrdersFilter(@RequestParam String orderCriteria, @RequestParam String orderType, @RequestParam String filterState, @RequestParam String searchText, HttpSession session) {

        OrderState orderState = switch (filterState) {
            case "IN_WORK" -> OrderState.IN_WORK;
            case "WAIT_FOR_PAYMENT" -> OrderState.WAIT_FOR_PAYMENT;
            case "PAID" -> OrderState.PAID;
            case "CANCELED" -> OrderState.CANCELED;
            case "COMPLETED" -> OrderState.COMPLETED;
            default -> null;
        };

        session.setAttribute("orderCriteria", orderCriteria.equals("cost")?OrderSort.BY_COST:OrderSort.BY_DATE);
        session.setAttribute("invertedSort", orderType.equals("inverted"));
        session.setAttribute("filterState", orderState);
        session.setAttribute("searchText", searchText);

        return "redirect:/manager/showOrders";
    }


    @RequestMapping("/manager/changeUserBalance")
    protected String managerChangeUserBalance(@RequestParam String email, @RequestParam String balance) {
        User user = userRepository.findById(email).get();
        int newBalance = (int)(Double.parseDouble(balance)*100.0) + user.getBalance();
        user.setBalance(newBalance);
        userRepository.save(user);
        return "redirect:/manager/showUsers";
    }

    @RequestMapping("/manager/setMaster")
    protected String managerSetMaster(@RequestParam String email, HttpSession session) {
        Order order = (Order)session.getAttribute("selectedOrder");
        Master master = masterRepository.findById(email).get();
        order.setMaster(master);
        orderRepository.save(order);
        return "redirect:/manager/showOrderEdit";
    }
}


