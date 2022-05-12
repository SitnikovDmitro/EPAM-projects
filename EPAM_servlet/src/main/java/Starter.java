import db.DbManager;
import entity.*;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class Starter {
    public static void main(String[] args) throws SQLException {
        DbManager.getInstance().clear();

        fillDatabase();



        //for (User user : users) System.out.println(user);

        //DbManager.getInstance().addManager(new Manager("manager@gmail.com", "root", "Dmitriy", "Sitnikov"));
        //DbManager.getInstance().addManager(new Manager("sh11@gmail.com", "root", "Женя", "Сидоров"));
        //DbManager.getInstance().addManager(new Manager("manager1@gmail.com", "root", "Dmitriy", "Sitnikov"));
        ArrayList<Order> orders = DbManager.getInstance().getOrders(null, null, OrderSort.BY_DATE, false);
        for (Order order : orders) System.out.println(order);

        //System.out.println(DbManager.getInstance().getManagerByEmail("sh11@gmail.com"));
    }



    public static void fillDatabase() throws SQLException {
        Master[] masters = new Master[] {
                new Master("elizabeth@gmail.com", "pass", "Elizabeth", "Smith", "0983788767", "info 123", 100, 10),
                new Master("matt@gmail.com", "mama", "Matt", "Parker", "0987787790", "info 444", 100, 10),
                new Master("annbrown@gmail.com", "1234", "Ann", "Brown", "09812312312", "info 876", 100, 10),
                new Master("alex123@gmail.com", "0000", "Alexandr", "Black", "0986774646", "info 109", 100, 10),
                new Master("stsm@gmail.com", "1111", "Steve", "Smith", "098334565", "info aab", 100, 10),
                new Master("m.scott@gmail.com", "down", "Matt", "Scott", "0983788767", "info ccd", 100, 10),
                new Master("rib@gmail.com", "5566", "Richard", "Brown", "0983455757", "info xyz", 100, 10),
                new Master("david@gmail.com", "7891", "David", "Scott", "0983898767", "info xyz", 100, 10),
                new Master("willit@gmail.com", "abcd", "William", "Smith", "0983785678", "info 455", 100, 10),
                new Master("micheal.lee@gmail.com", "xyzt", "Michael", "Lee", "0981234567", "info 122", 100, 10),
                new Master("john2018@gmail.com", "pass", "John", "Lee", "0987654321", "info ttt", 100, 10),
                new Master("blackmail@gmail.com", "8888", "Robert", "Black", "0988766666", "info mmm", 100, 10),
                new Master("smjs@gmail.com", "10000", "James", "Smith", "0980000000", "info abs", 100, 10)
        };

        User[] users = new User[] {
                new User("ss@gmail.com", "pass", "Steve", "Smith", "098334565", 12300),
                new User("mblack007@gmail.com", "eger", "Matt", "Black", "123423423", 56700),
                new User("jabr@gmail.com", "1234", "James", "Brown", "0983455757", 12300),
                new User("mich11@gmail.com", "1111", "Michael", "Black", "0983898767", 44400),
                new User("wwebb@gmail.com", "10000", "William", "Webb", "0981234567", 44400),
                new User("richrich@gmail.com", "passs", "Richard", "Smith", "0981234567", 12300),
                new User("bmw@gmail.com", "abcde", "Matt", "Webb", "123423423", 56700),
                new User("ann.jack@gmail.com", "secret", "Ann", "Jackson", "0987654321", 12300),
                new User("alex112@gmail.com", "passwd", "Alexandr", "Jackson", "0988766666", 44400),
                new User("eliza@gmail.com", "fffaaa", "Lisa", "Jackson", "0980000000", 44400)
        };


        Order[] orders = new Order[] {
                new Order(0, "Order title 0", "Order description 0", 10000, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-03-24"), "", 0, "richrich@gmail.com", null),
                new Order(1, "Order title 1", "Order description 1", 15000, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-05-11"), "", 0, "wwebb@gmail.com", null),
                new Order(2, "Order title 2", "Order description 2", 1250, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-01-25"), "", 0, "alex112@gmail.com", null),
                new Order(3, "Order title 3", "Order description 3", 23000, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-07-11"), "", 0, "mblack007@gmail.com", null),
                new Order(4, "Order title 4", "Order description 4", 4050, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-11-01"), "", 0, "richrich@gmail.com", null),
                new Order(5, "Order title 5", "Order description 5", 15800, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-08-12"), "", 0, "jabr@gmail.com", null),
                new Order(6, "Order title 6", "Order description 6", 1830, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-05-28"), "", 0, "wwebb@gmail.com", null),
                new Order(7, "Order title 7", "Order description 7", 515, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-07-16"), "", 0, "alex112@gmail.com", null),
                new Order(8, "Order title 8", "Order description 8", 50000, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-11-12"), "", 0, "mblack007@gmail.com", null),
                new Order(9, "Order title 9", "Order description 9", 8888, OrderState.WAIT_FOR_PAYMENT, Date.valueOf("2021-01-25"), "", 0, "jabr@gmail.com", null),
                new Order(10, "Order title 10", "Order description 10", 15800, OrderState.PAID, Date.valueOf("2021-02-03"), "", 0, "ss@gmail.com", "john2018@gmail.com"),
                new Order(11, "Order title 11", "Order description 11", 12300, OrderState.PAID, Date.valueOf("2021-06-10"), "", 0, "bmw@gmail.com", "matt@gmail.com"),
                new Order(12, "Order title 12", "Order description 12", 1200, OrderState.PAID, Date.valueOf("2021-04-11"), "", 0, "richrich@gmail.com", "blackmail@gmail.com"),
                new Order(13, "Order title 13", "Order description 13", 15450, OrderState.PAID, Date.valueOf("2021-03-26"), "", 0, "bmw@gmail.com", "rib@gmail.com"),
                new Order(14, "Order title 14", "Order description 14", 150, OrderState.PAID, Date.valueOf("2021-11-14"), "", 0, "ss@gmail.com", "john2018@gmail.com"),
                new Order(15, "Order title 15", "Order description 15", 1300, OrderState.PAID, Date.valueOf("2021-09-23"), "", 0, "alex112@gmail.com", "rib@gmail.com"),
                new Order(16, "Order title 16", "Order description 16", 1700, OrderState.PAID, Date.valueOf("2021-01-17"), "", 0, "mblack007@gmail.com", "rib@gmail.com"),
                new Order(17, "Order title 17", "Order description 17", 2550, OrderState.PAID, Date.valueOf("2021-03-16"), "", 0, "wwebb@gmail.com", "john2018@gmail.com"),
                new Order(18, "Order title 18", "Order description 18", 17000, OrderState.PAID, Date.valueOf("2021-01-14"), "", 0, "richrich@gmail.com", "matt@gmail.com"),
                new Order(19, "Order title 19", "Order description 19", 12000, OrderState.PAID, Date.valueOf("2021-09-23"), "", 0, "jabr@gmail.com", "stsm@gmail.com"),
                new Order(20, "Order title 20", "Order description 20", 8980, OrderState.CANCELED, Date.valueOf("2018-01-28"), "", 0, "bmw@gmail.com", null),
                new Order(21, "Order title 21", "Order description 21", 1200, OrderState.CANCELED, Date.valueOf("2019-02-16"), "", 0, "ss@gmail.com", null),
                new Order(22, "Order title 22", "Order description 22", 3400, OrderState.CANCELED, Date.valueOf("2017-09-23"), "", 0, "richrich@gmail.com", null),
                new Order(23, "Order title 23", "Order description 23", 15000, OrderState.CANCELED, Date.valueOf("2016-03-16"), "", 0, "bmw@gmail.com", null),
                new Order(24, "Order title 24", "Order description 24", 99990, OrderState.CANCELED, Date.valueOf("2017-05-15"), "", 0, "alex112@gmail.com", null),
                new Order(25, "Order title 25", "Order description 25", 23600, OrderState.CANCELED, Date.valueOf("2019-06-17"), "", 0, "mblack007@gmail.com", null),
                new Order(26, "Order title 26", "Order description 26", 23570, OrderState.CANCELED, Date.valueOf("2016-02-26"), "", 0, "ss@gmail.com", null),
                new Order(27, "Order title 27", "Order description 27", 2300, OrderState.CANCELED, Date.valueOf("2019-07-09"), "", 0, "wwebb@gmail.com", null),
                new Order(28, "Order title 28", "Order description 28", 100, OrderState.CANCELED, Date.valueOf("2018-11-12"), "", 0, "richrich@gmail.com", null),
                new Order(29, "Order title 29", "Order description 29", 3488, OrderState.CANCELED, Date.valueOf("2015-09-18"), "", 0, "bmw@gmail.com", null),
                new Order(30, "Order title 30", "Order description 30", 6850, OrderState.IN_WORK, Date.valueOf("2021-11-21"), "", 0, "jabr@gmail.com", "rib@gmail.com"),
                new Order(31, "Order title 31", "Order description 31", 12090, OrderState.IN_WORK, Date.valueOf("2021-09-23"), "", 0, "ss@gmail.com", "rib@gmail.com"),
                new Order(32, "Order title 32", "Order description 32", 13890, OrderState.IN_WORK, Date.valueOf("2021-03-01"), "", 0, "richrich@gmail.com", "john2018@gmail.com"),
                new Order(33, "Order title 33", "Order description 33", 7600, OrderState.IN_WORK, Date.valueOf("2021-08-09"), "", 0, "mblack007@gmail.com", "blackmail@gmail.com"),
                new Order(34, "Order title 34", "Order description 34", 3700, OrderState.IN_WORK, Date.valueOf("2021-03-17"), "", 0, "alex112@gmail.com", "stsm@gmail.com"),
                new Order(35, "Order title 35", "Order description 35", 2850, OrderState.IN_WORK, Date.valueOf("2021-03-14"), "", 0, "wwebb@gmail.com", "matt@gmail.com"),
                new Order(36, "Order title 36", "Order description 36", 7500, OrderState.IN_WORK, Date.valueOf("2021-07-12"), "", 0, "jabr@gmail.com", "john2018@gmail.com"),
                new Order(37, "Order title 37", "Order description 37", 23500, OrderState.IN_WORK, Date.valueOf("2021-03-05"), "", 0, "richrich@gmail.com", "rib@gmail.com"),
                new Order(38, "Order title 38", "Order description 38", 3570, OrderState.IN_WORK, Date.valueOf("2021-02-08"), "", 0, "bmw@gmail.com", "rib@gmail.com"),
                new Order(39, "Order title 39", "Order description 39", 3500, OrderState.IN_WORK, Date.valueOf("2021-03-17"), "", 0, "ss@gmail.com", "matt@gmail.com"),
                new Order(40, "Order title 40", "Order description 30", 9800, OrderState.COMPLETED, Date.valueOf("2017-03-14"), "", -1, "wwebb@gmail.com", "rib@gmail.com"),
                new Order(41, "Order title 41", "Order description 41", 44477, OrderState.COMPLETED, Date.valueOf("2018-09-12"), "", 3, "mblack007@gmail.com", "stsm@gmail.com"),
                new Order(42, "Order title 42", "Order description 42", 23460, OrderState.COMPLETED, Date.valueOf("2019-07-21"), "Terrible job!", 1, "bmw@gmail.com", "rib@gmail.com"),
                new Order(43, "Order title 43", "Order description 43", 230, OrderState.COMPLETED, Date.valueOf("2020-02-09"), "", 2, "wwebb@gmail.com", "matt@gmail.com"),
                new Order(44, "Order title 44", "Order description 44", 600, OrderState.COMPLETED, Date.valueOf("2021-03-15"), "bad", 2, "ss@gmail.com", "blackmail@gmail.com"),
                new Order(45, "Order title 45", "Order description 45", 120, OrderState.COMPLETED, Date.valueOf("2020-01-22"), "good", 5, "richrich@gmail.com", "stsm@gmail.com"),
                new Order(46, "Order title 46", "Order description 46", 45600, OrderState.COMPLETED, Date.valueOf("2020-03-21"), "I am glad", 5, "jabr@gmail.com", "matt@gmail.com"),
                new Order(47, "Order title 47", "Order description 47", 123000, OrderState.COMPLETED, Date.valueOf("2020-03-31"), "excellent", 5, "alex112@gmail.com", "stsm@gmail.com"),
                new Order(48, "Order title 48", "Order description 48", 12345, OrderState.COMPLETED, Date.valueOf("2021-05-24"), "good", 4, "richrich@gmail.com", "blackmail@gmail.com"),
                new Order(49, "Order title 49", "Order description 49", 6688, OrderState.COMPLETED, Date.valueOf("2021-08-11"), "good work", 4, "richrich@gmail.com", "john2018@gmail.com"),
        };

        for (Master master : masters) try {
            DbManager.getInstance().addMaster(master);
        } catch (SQLException ignored) { }
        for (User user : users) try {
            DbManager.getInstance().addUser(user);
        } catch (SQLException ignored) { }
        for (Order order : orders) try {
            DbManager.getInstance().addOrder(order);
        } catch (SQLException ignored) { }
        //db.DbManager.getInstance().add(master);
        //db.DbManager.getInstance().addMaster();
        DbManager.getInstance().addManager(new Manager("manager@gmail.com", "root", "Dmitriy", "Sitnikov"));

        correctMasters();
    }

    public static void correctMasters() throws SQLException {
        ArrayList<Order> orders = DbManager.getInstance().getOrders(null, null, null, false);
        ArrayList<Master> masters = DbManager.getInstance().getMasters();

        for (Master master : masters) {
            int reviewsCount = 0;
            int reviewsStars = 0;
            for (Order order : orders) {
                if (order.getMasterEmail() != null && order.getMasterEmail().equals(master.getEmail()) && order.getReviewStars() > 0) {
                    reviewsCount++;
                    reviewsStars += order.getReviewStars();
                }
            }

            Master newMaster = new Master(master.getEmail(), master.getPassword(), master.getFirstname(), master.getSurname(), master.getPhone(), master.getInformation(), reviewsCount, reviewsStars);
            DbManager.getInstance().updateMaster(newMaster);
        }
    }
}
