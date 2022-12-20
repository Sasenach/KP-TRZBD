package com.example.upitog.Controller;

import com.example.upitog.Model.Dish;
import com.example.upitog.Model.Employee;
import com.example.upitog.Model.Stat;
import com.example.upitog.Repository.DishInCheckRepository;
import com.example.upitog.Repository.DishRepository;
import com.example.upitog.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatController {

    @Autowired
    DishRepository dishRepository;
    @Autowired
    DishInCheckRepository dishInCheckRepository;
    @GetMapping("/stat")
    public String getStat(Model model){

        return "stat";
    }

    @GetMapping("/api")
    public ResponseEntity<ArrayList<Stat>> userallapi() {

        ArrayList<Stat> a = new ArrayList<>();
        Iterable<Dish> dishes = dishRepository.findAll();

        for (Dish dish: dishes
        ) {
            Stat stat = new Stat();
            stat.setAmount(dishInCheckRepository.countBydish_id(dish.getId()));
            stat.setName(dish.getName());
            System.out.println(stat.getAmount());
            a.add(stat);
        }
        return ResponseEntity.ok().body(a);
    }

    @GetMapping("/backup-export")
    public String backup()
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s --password=%s --add-drop-table --column-statistics=0 --databases %s -r %s",
                "root", "root", "itog", "D:\\OSPanel\\bec.sql");
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
        if(processComplete==0)
        {
            return("redirect:/stat");}
        else
        { return("stat");
        }
    }
    @GetMapping("/backup-import")
    public String backupImport()
            throws IOException, InterruptedException {
        String[] command = new String[]{
                "mysql",
                "-u" + "root",
                "--password=" + "root",
                "-e",
                " source " + "D:\\OSPanel\\bec.sql",
                "itog"
        };
        Process runtimeProcess = Runtime.getRuntime().exec(command);
        int processComplete = runtimeProcess.waitFor();
        if(processComplete==0)
        {
            return("redirect:/stat");}
        else
        {
            return("Stat");
        }
    }


    @Autowired
    EmployeeRepository userRepos;
    @PostMapping("/api/users")
    public ResponseEntity<Employee> CreateUserApi(@Valid @RequestBody Employee user)
    {
        return ResponseEntity.ok(userRepos.save(user));
    }

    @DeleteMapping("/api/users/del/{id}")
    public ResponseEntity<Employee> DeleteUserApi(@PathVariable long id) throws Exception
    {

        Employee user = userRepos.findById(id)
                .orElseThrow(() -> new Exception(""));
        userRepos.delete(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/users/view")
    public ResponseEntity<List<String>> getAllNotes() {
        List<String> users = new ArrayList<>();
        Iterable<Employee> iu=userRepos.findAll();
        for (Employee us:iu
        ) {
            users.add(us.toString());
        }
        return ResponseEntity.ok(users);
    }
}
