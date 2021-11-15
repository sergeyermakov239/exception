package sergey.ermakov.exception;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

@RestController
public class ApiController {

    ArrayList<User> users=new ArrayList<>();
//curl -X POST http://localhost:8080/users -H 'Content-Type: application/json' -d '{"name1":"Sergey","age1":"17","password1":"password"}'
    @PostMapping("users")
    public void addUser(@RequestBody User user){
        if(!User.login(user.getName())){
            throw new BadUsernameException();
        }
        for (User u:users){
            if (u.getName().equals(user.getName())){
                throw new BadUserNumberException();
            }
        }
        users.add(user);
    }

    @GetMapping("users/{name}")
    public User getUser(@PathVariable("name") String name){
        boolean f=true;
        User u1=new User("s",1,"s");
        for (User u:users){
            if (u.getName().equals(name)){
                u1=u;
                f=false;
            }
        }
        if (f){
            throw new NoUserException();
        } else{
            return u1;
        }
    }
//curl -X DELETE http://localhost:8080/users/Sergey
    @DeleteMapping("users/{name}")
    public void deleteUser(@PathVariable("name") String name){
        if(name.indexOf("admin")!=0){
            throw new NoAdminInUserNameException();
        } else {
            boolean f = true;
            User u1=new User("f",5,"h");
            for (User u : users) {
                if (u.getName().equals(name)) {
                    u1=u;
                    f = false;
                }
            }
            if (f) {
                throw new NoUserException();
            } else{
                users.remove(u1);
            }
        }
    }
    //curl -X PUT http://localhost:8080/users/Sergey -H 'Content-Type: application/json' -d 'password1'
    @PutMapping("users/{name}")
    public void updateUser(@PathVariable("name") String name,@RequestBody String password){
        if (name.indexOf("update")!=0){
            throw new NoAdminInUserNameException();
        } else{
            if (!User.login(name)){
                throw new BadUsernameException();
            } else{
                boolean f=true;
                for (User u:users){
                    if (u.getName().equals(name)){
                        u.setPassword(password);
                        f=false;
                    }
                }
                if (f){
                    throw new NoUserException();
                }
            }
        }
    }


//---------------------4-------------------------------------------


    //curl -X POST http://localhost:8080/users/password -H 'Content-Type: application/json' -d '{"name1":"Sergey","age1":"17","password1":"password"}'
    @PostMapping("users/{confirmpassword}")
    public void addUserwithconfirmpassword(@PathVariable("confirmpassword") String confirmpassword,@RequestBody User user){
        if(!User.login(user.getName())){
            throw new BadUsernameException();
        }
        if (!confirmpassword.equals(user.getPassword())){
            throw new BadUsernameException();
        }
        for (User u:users){
            if (u.getName().equals(user.getName())){
                throw new BadUserNumberException();
            }
        }
        users.add(user);
    }
    //curl -X PUT http://localhost:8080/users/Sergey/password -H 'Content-Type: application/json' -d 'password1'
    @PutMapping("users/{name}/{confirmpassword}")
    public void updateUserwithconfirmpassword(@PathVariable("name") String name,@PathVariable("confirmpassword") String confirmpassword,@RequestBody String password){
        if (name.indexOf("update")!=0){
            throw new NoAdminInUserNameException();
        } else{
            if (!User.login(name)){
                throw new BadUsernameException();
            } else{
                boolean f=true;
                for (User u:users){
                    if (u.getName().equals(name)){
                        if (!u.getPassword().equals(confirmpassword)){
                            throw new BadUsernameException();
                        }
                        u.setPassword(password);
                        f=false;
                    }
                }
                if (f){
                    throw new NoUserException();
                }
            }
        }
    }


    @GetMapping("users/age/{age}")
    public ArrayList<User> getUserwithage(@PathVariable("age") int age){
        ArrayList<User> u11=new ArrayList<>();
        for (User u:users){
            if (Math.abs(u.getAge()-age)<=5){
                u11.add(u);
            }
        }
        return u11;
    }


    //------------------------5--------------------------
//curl -X GET http://localhost:8080/users/nopassword/getusers/17?sort=up
    @GetMapping("users/nopassword/getusers/{age}")
    public ArrayList<String> getUserwithageandwithoutpassword(@PathVariable("age") int age,@RequestParam("sort") String sort){
        ArrayList<String> us=new ArrayList<>();
        ArrayList<User> u11=new ArrayList<>();
        for (User u:users){
            if (Math.abs(u.getAge()-age)<=5){
                u11.add(u);
            }
        }
        Collections.sort(u11, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (sort.equals("up")) {
                    if (o1.getAge() >= o2.getAge()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else{
                    if (o1.getAge() > o2.getAge()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
        for (User u:u11){
            String s="[name: "+u.getName()+",age: "+u.getAge()+"] ";
            us.add(s);
        }
        return us;
    }

    @GetMapping("users/nopassword/{name}")
    public String getUserwithoutpassword(@PathVariable("name") String name){
        boolean f=true;
        User u1=new User("s",1,"s");
        for (User u:users){
            if (u.getName().equals(name)){
                u1=u;
                f=false;
            }
        }
        if (f){
            throw new NoUserException();
        } else{
            String s="[name: "+u1.getName()+",age: "+u1.getAge()+"] ";
            return  s;
        }
    }




}
