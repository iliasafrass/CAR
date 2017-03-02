package CMD;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Afrass on 15/02/2017.
 */
import java.nio.file.Path;

public interface NativeCMD  {

        boolean userExist(String user) throws NotImplementedException;

        /** demande au systeme d'authentifier l'utilisateur.
         * @param password
         * @return true si le password est correct, false sinon.
         * @throws BadOrderException if no name  have been set before with userExist
         * @throws NotImplementedException  if the method is not fully implemented
         */
        boolean goodPassword(String password) throws BadOrderException, NotImplementedException;

        /** get the complete path for a file with only the basename of file
         * @param name the file's basename
         * @return the file's path
         * @throws BadOrderException if no name  have been set before with userExist
         * @throws NotImplementedException  if the method is not fully implemented
         */
        Path getFilePath(String name) throws BadOrderException, NotImplementedException;

        /** Move to parent directory
         * @throws BadOrderException if no name  have been set before with userExist
         * @throws NotImplementedException  if the method is not fully implemented
         */
        void directoryUp() throws BadOrderException, NotImplementedException;

        /** Move to directory gived
         * @param dir the to go to the next dir
         * @throws BadOrderException if no name  have been set before with userExist
         * @throws NotImplementedException  if the method is not fully implemented
         * @throws NotDirException
         */
        void changeDirectory(String dir) throws BadOrderException, NotImplementedException, NotDirException;

        /**
         *
         * @return the current directory as a string.
         * @throws BadOrderException
         * @throws NotImplementedException  if the method is not fully implemented
         */
        String currentDir() throws BadOrderException, NotImplementedException;

        /**
         *
         * @return return the files and directory list of the current dir
         * @throws BadOrderException
         * @throws NotImplementedException  if the method is not fully implemented
         */
        String getFilesList() throws BadOrderException, NotImplementedException;

        /**
         * @throws NotImplementedException  if the method is not fully implemented
         */
        boolean isAuthenticated() throws NotImplementedException;
    }

