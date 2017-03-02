package CMD;

/**
 * Created by Afrass on 15/02/2017.
 */
public class BadOrderException extends Exception {

        public BadOrderException(){
            System.out.println("Bad order of commands");
        }
    }
