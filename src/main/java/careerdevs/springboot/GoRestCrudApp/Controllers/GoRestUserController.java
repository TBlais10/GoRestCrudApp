package careerdevs.springboot.GoRestCrudApp.Controllers;

import careerdevs.springboot.GoRestCrudApp.Model.GoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/gorest/user")
public class GoRestUserController {
    @Autowired
    private Environment env;



    @GetMapping("/pageone")
    public Object getUsers (RestTemplate restTemplate){
        String URL = "https://gorest.co.in/public/v1/users";

        //TODO: Add do while loop that grabs all of the users inside their pages.

        return restTemplate.getForObject(URL, GoRest.class).getData();
    }

    @GetMapping("/get")
    public Object getUser (RestTemplate restTemplate, @RequestParam (name="id", defaultValue = "1")String id){
        String URL = "https://gorest.co.in/public/v1/users" + id;

        return restTemplate.getForObject(URL, GoRest.class).getData();

    }

    @PostMapping("/")
    public String testPostMapping () {
        return "TEST";
    }

    @PutMapping("/")
    public String testPutMapping () {
        return "TEST";
    }

    @DeleteMapping ("/delete")
    public String deleteUser (RestTemplate restTemplate, @RequestParam(name = "id")String id;){

        String URL = "https://gorest.co.in/public/v1/users" + id;

        try {
        restTemplate.delete(URL);
        return "You have deleted the user: " + id;
        } catch (HttpClientErrorException.Unauthorized e){
            return "No bearer token detected. You need authorization";
        }
        catch (HttpClientErrorException.NotFound e){
            return "ID did not match a user in the database.";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
