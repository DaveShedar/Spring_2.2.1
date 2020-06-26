package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User888", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("Kalina", 222);
      Car car2 = new Car("Moskvich", 890);
      Car car3 = new Car("Volga", 432);
      Car car4 = new Car("Pobeda", 787);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId_user());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listCars();
      for (Car car : cars){
         System.out.println("Id = " + car.getId());
         System.out.println("Name = " + car.getName());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      @SuppressWarnings("unchecked")
      List<User> list = userService.getUserBySeriesCar(890);
      for (User user : list) {
         System.out.println("_______________________________________");
         System.out.println("Get User name by Series of Car:");
         System.out.println("_______________________________________");
         System.out.println("Id = " + user.getId_user());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("_______________________________________");
         System.out.println();
      }
      context.close();
   }
}
