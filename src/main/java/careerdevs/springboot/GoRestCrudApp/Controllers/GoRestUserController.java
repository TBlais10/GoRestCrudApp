package careerdevs.springboot.GoRestCrudApp.Controllers;

import careerdevs.springboot.GoRestCrudApp.Model.GoRestResponseMulti;
import careerdevs.springboot.GoRestCrudApp.Model.GoRestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api/gorest/user")
public class GoRestUserController {
    @Autowired
    private Environment env;


    @GetMapping("/pageone")
    public GoRestResponseMulti getUsers(RestTemplate restTemplate) {
        String URL = "https://gorest.co.in/public/v1/users";

        //TODO: Add do while loop that grabs all of the users inside their pages.

        return restTemplate.getForObject(URL, GoRestResponseMulti.class);
    }

    @GetMapping("/get")
    public Object getUser(RestTemplate restTemplate, @RequestParam(name = "id", defaultValue = "1") String id) {
        String URL = "https://gorest.co.in/public/v1/users/" + id;

        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(env.getProperty("bearer.token"));

        HttpEntity request = new HttpEntity(header);

        return restTemplate.getForObject(URL, GoRestUser.class, request).getData();

    }

    @GetMapping("/allusers")
    public Object allUsers (RestTemplate restTemplate){

        GoRestResponseMulti res = getUsers(restTemplate);

        int pageNumb = res.getMeta().getPagination().getPages();
        ArrayList<GoRestUser> allUserData = new ArrayList<>();


        for (int i = 2; i <= pageNumb; i++) {
            String URL = "https://gorest.co.in/public/v1/users?page=" + i;
            GoRestUser[] tempUserData = restTemplate.getForObject(URL, GoRestResponseMulti.class).getData(); //TODO: need to split the GoRest class into a user class for user data, Multi for pages.
            Collections.addAll(allUserData, res.getData());
        }
        return allUserData;
    }

    @GetMapping("/someusers")
    public Object someUsers (RestTemplate restTemplate){

        ArrayList<GoRestUser> allUserData = new ArrayList<>();


        for (int i = 1; i <= 3; i++) {
            String URL = "https://gorest.co.in/public/v1/users?page=" + i;
            GoRestUser[] tempUserData = restTemplate.getForObject(URL, GoRestResponseMulti.class).getData(); //TODO: need to split the GoRest class into a user class for user data, Multi for pages.
            Collections.addAll(allUserData, tempUserData);
        }
        return allUserData;
    }

    @GetMapping("/page/{page}")
    public Object getSpecificPage (RestTemplate restTemplate, @PathVariable String page){
        String URL = "https://gorest.co.in/public/v1/users?page=" + page;

        try {
            return restTemplate.getForObject(URL, GoRestUser.class).getData();
        } catch (HttpClientErrorException.NotFound exception){
            return "Cannot find page";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @PostMapping("/new")
    public Object createuser(RestTemplate restTemplate,
                             @RequestParam(name="name") String name,
                             @RequestParam(name="email") String email,
                             @RequestParam(name="gender") String gender,
                             @RequestParam(name="status") String status){
        String URL = "https://gorest.co.in/public/v1/users/";

        try {
            HttpHeaders header = new HttpHeaders();
            header.setBearerAuth(env.getProperty("bearer.token"));
            GoRestUser newUser = new GoRestUser(name, email, gender, status);
            HttpEntity request = new HttpEntity(newUser, header);
            return restTemplate.exchange(URL, HttpMethod.POST, request , GoRestUser.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            return "No bearer token detected. You need authorization";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

    @PutMapping("/put")
    public Object updateUser (RestTemplate restTemplate,
                              @RequestParam(name = "id", defaultValue = "1") String id,
                              @RequestParam(name="name") String name,
                              @RequestParam(name="email") String email,
                              @RequestParam(name="gender") String gender,
                              @RequestParam(name="status") String status){
        String URL = "https://gorest.co.in/public/v1/users/" + id;
        HttpHeaders header = new HttpHeaders();
        GoRestUser newUser = new GoRestUser(name, email, gender, status);
        header.setBearerAuth(env.getProperty("bearer.token"));
        HttpEntity request = new HttpEntity(newUser, header);

        return restTemplate.exchange(URL, HttpMethod.PUT, request, GoRestUser.class);
    }

    @DeleteMapping("/delete")
    public String deleteUser(RestTemplate restTemplate, @RequestParam(name = "id") String id) {

        String URL = "https://gorest.co.in/public/v1/users/" + id;
        try {
           HttpHeaders header = new HttpHeaders();
           header.setBearerAuth(env.getProperty("bearer.token"));

            HttpEntity request = new HttpEntity(header);
            restTemplate.exchange(URL, HttpMethod.DELETE, request , GoRestUser.class);
            return "You have deleted the user: " + id;
        } catch (HttpClientErrorException.Unauthorized e) {
            return "No bearer token detected. You need authorization";
        } catch (HttpClientErrorException.NotFound e) {
            return "ID did not match a user in the database.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}

/*//You need all of them - default way
        //RequestPeram
        //QuiryPeram
        //Quiry - a question
        //Setting our statically
        //make a variable out of the slashes that wea are using
        //Path variable
        //patch requests are for small updates
        //puts are for larger updates
        //No distinction between put and patch requests - GoRest.co.in
        //identical function that works with either.
        //not needing to send a fully completed object (requestparams)
        //set requestparam to request false. Default required

        //for loop that takes in the keys for the profile and if what they fill in matches a key, update that field.*/
