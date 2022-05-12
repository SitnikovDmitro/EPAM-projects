package com.ra.model.utils;

import com.ra.model.entity.Master;
import com.ra.model.enums.OrderState;
import com.ra.model.enums.Lang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    public String translate(String text, Lang lang) {
        String result = lang==Lang.RU?ruMap.get(text):enMap.get(text);
        if (result==null) return "Text not found for : "+text;
        return result;
    }

    public String formatPrice(int price) {
        return new DecimalFormat("######.##").format(price/100.0);
    }

    public String formatState(int state, Lang lang) {
        if (lang == Lang.RU) {
            return switch (OrderState.fromInt(state)) {
                case PAID -> "Заплачено";
                case IN_WORK -> "Выполняется";
                case WAIT_FOR_PAYMENT -> "Ждет оплаты";
                case CANCELED -> "Отменено";
                case COMPLETED -> "Завершено";
            };
        } else {
            return switch (OrderState.fromInt(state)) {
                case PAID -> "Paid";
                case IN_WORK -> "In work";
                case WAIT_FOR_PAYMENT -> "Wait for payment";
                case CANCELED -> "Canceled";
                case COMPLETED -> "Completed";
            };
        }
    }

    public OrderState getStateByCode(int state) {
        return  OrderState.fromInt(state);
    }

    public int getCodeByState(OrderState state) {
        if (state == null) throw new NullPointerException("State is null!");
        return state.toInt();
    }

    public String formatMasterAverageScore(Master master) {
        if (master.getCompletedOrdersCount() == 0) return "0";
        return String.valueOf(20*master.getStarsCount()/master.getCompletedOrdersCount());
    }

    public int length(ArrayList list) {
        return list.size();
    }



    private Utils() {
        enMap.put("repair_agency", "Repair agency");
        enMap.put("login", "Log in");
        enMap.put("user_sign", "Sign in as user");
        enMap.put("master_sign", "Sign in as master");

        enMap.put("create_order", "Create order");
        enMap.put("my_orders", "My orders");
        enMap.put("edit_me", "Edit me");
        enMap.put("logout", "Log out");
        enMap.put("orders", "Orders");
        enMap.put("users", "Users");

        enMap.put("feedback", "Feedback");
        enMap.put("message", "Message");

        enMap.put("1_stars", "1 stars (Horrible)");
        enMap.put("2_stars", "2 stars (Bad)");
        enMap.put("3_stars", "3 stars (Medium)");
        enMap.put("4_stars", "4 stars (Good)");
        enMap.put("5_stars", "5 stars (Excellent)");

        enMap.put("submit", "Submit");
        enMap.put("cancel", "Cancel");
        enMap.put("apply", "Apply");
        enMap.put("select", "Select");
        enMap.put("save_changes", "Save changes");
        enMap.put("pay", "Pay");
        enMap.put("start", "Start");
        enMap.put("finish", "Finish");
        enMap.put("search", "Search");
        enMap.put("edit", "Edit");
        enMap.put("filter_options", "Filter options");
        enMap.put("appoint_master", "Appoint master");
        enMap.put("appoint_another_master", "Appoint another master");
        enMap.put("show_details", "Show details");

        enMap.put("user_registration", "User registration");
        enMap.put("master_registration", "Master registration");
        enMap.put("orders_manipulation", "Orders manipulation");
        enMap.put("order_creation", "Order creation");
        enMap.put("user_editing", "User editing");
        enMap.put("master_editing", "Master editing");
        enMap.put("user_selection", "User selection");
        enMap.put("orders_searching", "Orders searching");
        enMap.put("master_selection", "Master selection");
        enMap.put("filtering_and_ordering", "Filtering and ordering");
        enMap.put("master_inspection", "Master inspection");
        enMap.put("reviews", "Reviews");
        enMap.put("average_score", "average score");
        enMap.put("stars", "stars");

        enMap.put("email_address", "Email address");
        enMap.put("password", "Password");
        enMap.put("balance", "Balance");
        enMap.put("name", "Name");
        enMap.put("surname", "Surname");
        enMap.put("phone", "Phone");
        enMap.put("description", "Description");
        enMap.put("title", "Title");
        enMap.put("information", "Information");

        enMap.put("date", "Date");
        enMap.put("cost", "Cost");
        enMap.put("straight", "Straight");
        enMap.put("inverted", "Inverted");
        enMap.put("wait_for_payment", "Wait for payment");
        enMap.put("paid", "Paid");
        enMap.put("in_work", "In work");
        enMap.put("completed", "Completed");
        enMap.put("canceled", "Canceled");
        enMap.put("all", "All");

        enMap.put("order_criteria", "Order criteria");
        enMap.put("order_type", "Order type");
        enMap.put("filter_state", "Filter state");
        enMap.put("master", "Master");

        enMap.put("price_not_specified", "Price not specified");
        enMap.put("no_orders", "No orders");
        enMap.put("no_user", "No user matches search query");
        enMap.put("no_masters", "No master");
        ruMap.put("no_reviews", "No reviews");
        enMap.put("invalid_credentials", "Invalid credentials");
        enMap.put("master_not_specified", "Master not specified");

        enMap.put("error_message", "An unexpected failure has occurred. Please login again to continue the work");
        enMap.put("session_expired", "You have not logged");
        enMap.put("email_already_taken", "This email has already taken");




        ruMap.put("repair_agency", "Ремонтное агенство");
        ruMap.put("login", "Вход");
        ruMap.put("user_sign", "Зарегистрироваться как пользователь");
        ruMap.put("master_sign", "Зарегистрироваться как исполнитель");

        ruMap.put("create_order", "Создать заказ");
        ruMap.put("my_orders", "Мои заказы");
        ruMap.put("edit_me", "Редактировать себя");
        ruMap.put("logout", "Выйти");
        ruMap.put("orders", "Заказы");
        ruMap.put("users", "Пользователи");

        ruMap.put("feedback", "Отзывы");
        ruMap.put("message", "Сообщение");

        ruMap.put("1_stars", "1 звезда (Ужасно)");
        ruMap.put("2_stars", "2 звезды (Плохо)");
        ruMap.put("3_stars", "3 звезды (Средне)");
        ruMap.put("4_stars", "4 звезды (Хорошо)");
        ruMap.put("5_stars", "5 звезд (Превосходно)");

        ruMap.put("submit", "Подтвердить");
        ruMap.put("cancel", "Отменить");
        ruMap.put("apply", "Применить");
        ruMap.put("select", "Выбрать");
        ruMap.put("save_changes", "Сохранить изменения");
        ruMap.put("pay", "Заплатить");
        ruMap.put("start", "Начать");
        ruMap.put("finish", "Закончить");
        ruMap.put("search", "Искать");
        ruMap.put("edit", "Изменить");
        ruMap.put("filter_options", "Параметры фильтрации");
        ruMap.put("appoint_master", "Назначить исполнителя");
        ruMap.put("appoint_another_master", "Назначить другого исполнителя");
        ruMap.put("show_details", "Показать подробности");

        ruMap.put("user_registration", "Регистрация пользователя");
        ruMap.put("master_registration", "Регистрация исполнителя");
        ruMap.put("orders_manipulation", "Управление заказами");
        ruMap.put("order_creation", "Создание заказа");
        ruMap.put("user_editing", "Редактирование пользователя");
        ruMap.put("master_editing", "Редактирование исполнителя");
        ruMap.put("user_selection", "Выбор пользователей");
        ruMap.put("orders_searching", "Поиск заказов");
        ruMap.put("master_selection", "Выбор мастера");
        ruMap.put("filtering_and_ordering", "Фильтрация и сортировка");
        ruMap.put("master_inspection", "Осмотр исполнителя");
        ruMap.put("reviews", "Отзывы");
        ruMap.put("average_score", "средняя оценка");
        ruMap.put("stars", "звезды");

        ruMap.put("email_address", "Адрес электронной почты");
        ruMap.put("password", "Пароль");
        ruMap.put("balance", "Баланс");
        ruMap.put("name", "Имя");
        ruMap.put("surname", "Фамилия");
        ruMap.put("phone", "Телефон");
        ruMap.put("description", "Описание");
        ruMap.put("title", "Заголовок");
        ruMap.put("information", "Информация");

        ruMap.put("date", "Дата");
        ruMap.put("cost", "Цена");
        ruMap.put("straight", "Прямой");
        ruMap.put("inverted", "Обратный");
        ruMap.put("wait_for_payment", "Ждет оплаты");
        ruMap.put("paid", "Заплачено");
        ruMap.put("in_work", "В работе");
        ruMap.put("completed", "Завершено");
        ruMap.put("canceled", "Отменено");
        ruMap.put("all", "Все");

        ruMap.put("order_criteria", "Критерий сортировки");
        ruMap.put("order_type", "Тип сортировки");
        ruMap.put("filter_state", "Состояние фильтрации");
        ruMap.put("master", "Исполнитель");

        ruMap.put("price_not_specified", "Стоимость не определена");
        ruMap.put("no_orders", "Нету заказов");
        ruMap.put("no_user", "Нету пользователей, соответствующих поисковому запросу");
        ruMap.put("no_masters", "Нету исполнителей");
        ruMap.put("no_reviews", "Нету отзывов");
        ruMap.put("invalid_credentials", "Неверные учетные данные");
        ruMap.put("master_not_specified", "Исполнитель еще не назначен");

        ruMap.put("error_message", "Произошел непредвиденный сбой. Чтобы продолжить работу войдите в систему заново");
        ruMap.put("session_expired", "Вы не вошли в систему");
        ruMap.put("email_already_taken", "Этот почтовый адрес уже занят");

    }

    private static Utils instance;
    private final HashMap<String, String> ruMap = new HashMap<>();
    private final HashMap<String, String> enMap = new HashMap<>();

    public static Utils getInstance() {
        if (instance == null) instance = new Utils();
        return instance;
    }
}

