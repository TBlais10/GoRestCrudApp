package careerdevs.springboot.GoRestCrudApp.Controllers;

import careerdevs.springboot.GoRestCrudApp.Model.GoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gorest")
public class GoRestController {
    @Autowired
    private Environment env;


    @GetMapping("/")
    public GoRest getUsers (RestTemplate restTemplate){
        String URL = "https://gorest.co.in/public/v1/posts" + env.getProperty("gorest.key");

        return restTemplate.getForObject(URL, GoRest.class);
    }

    @PostMapping(
            path = "/post",
            consumes = "application/json",
            produces =  "application/json"
    )
    public ResponseEntity<Object> addUser(@RequestBody GoRest user){

    }

}
