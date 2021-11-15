package sergey.ermakov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class User {
    private String name;
    private String password;
    private int age;

    public User (String name1,int age1, String password1){
              name=name1;
              age=age1;
              password=password1;


    }

    public static boolean login(String login)  {
        char[] b=login.toCharArray();
        boolean f1=true;
        for(char c:b){
            if ((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='1'&&c<='9')){

            } else{
                f1=false;
            }
        };
        if(f1==false||login.length()>=20){
            return false;
        } else {
            return true;
        }


    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
