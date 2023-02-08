package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Users;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonTutorial {
    public static void main (String [] args ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        //object mapper
        /*
        Users users = mapper.readValue(new File("src/main/java/utils/webshop.json"), Users.class);
        System.out.println(users);

         */
        //list mapper
        /*
        List<Users> usersList = mapper.readValue(new File("src/main/java/utils/webshop.json"), new TypeReference<List<Users>>() {});
        System.out.println(usersList);

         */

        //array mapper
        Users[] usersArray = mapper.readValue(new File("src/main/java/utils/users.json"), Users[].class);
        for (Users user : usersArray){
            System.out.println(user);
        }
    }
}
