package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Sofia", "Chernishkova", "makurokurosuke@mail.ru", new Car("Daewoo", 43)));
      userService.add(new User("Egor", "Tapochkin", "slippery1989@gmail.com", new Car("Ford", 17)));
      userService.add(new User("Yaroslav", "Ptichkin", "morningsong12@yahoo.com", new Car("Land Rover", 23)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + ", series = " + user.getCar().getSeries() + "\n");
      }


      System.out.println("Поиск по модели \"Ford\" и серии \"17\": ");

      List<User> userByModelAndSeries = userService.findByModelAndSeries("Ford", 17);
      for (User user: userByModelAndSeries) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + ", series = " + user.getCar().getSeries());
      }
      context.close();
   }
}