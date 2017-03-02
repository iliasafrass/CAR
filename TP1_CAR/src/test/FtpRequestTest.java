import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import nativeCMD.BadOrderException;
import nativeCMD.NativeCMD;
import nativeCMD.NotImplementedException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TestFtpRequest extends TestCase {

    Socket sock;
    FtpRequest ftp;
    NativeCMD command;
    DataChannel dataFactory;
    private Socket data;

    @Before
    public void initialize() throws IOException {
        sock = Mockito.mock(Socket.class);
        InputStream inpStream = new ByteArrayInputStream("".getBytes());
        OutputStream outStream = new ByteArrayOutputStream();
        Mockito.when(sock.getInputStream()).thenReturn(inpStream);
        Mockito.when(sock.getOutputStream()).thenReturn(outStream);
        Mockito.when(sock.getInetAddress()).thenReturn(Mockito.mock(InetAddress.class));

        data = Mockito.mock(Socket.class);
        InputStream inpDataStream = new ByteArrayInputStream("".getBytes());
        OutputStream outDataStream = new ByteArrayOutputStream();
        Mockito.when(data.getInputStream()).thenReturn(inpDataStream);
        Mockito.when(data.getOutputStream()).thenReturn(outDataStream);

        command = Mockito.mock(NativeCMD.class);
        dataFactory = Mockito.mock(DataChannel.class);
        Mockito.when(dataFactory.getNewDataChannel(
                (InetAddress)notNull(),
                anyInt(),
                anyBoolean(),
                (ServerSocket)notNull())).thenReturn(data);

        ftp = Mockito.spy(new FtpRequest(sock,command,dataFactory));
    }

    @Test
    public void testProcessRequestUser() throws IOException, NotImplementedException {
        ftp.processRequest("USER name");
        Mockito.verify(ftp).processUser("name");
    }

    @Test
    public void testProcessRequestPass() throws IOException, BadOrderException, NotImplementedException {
        ftp.processRequest("PASS pwd");
        Mockito.verify(ftp).processPass("pwd");
    }

    @Test
    public void testProcessRequestRetr() throws IOException, BadOrderException, NotImplementedException {
        ftp.processRequest("RETR name");
        Mockito.verify(ftp).processRetr("name");
    }

    @Test
    public void testProcessRequestStor() throws IOException, BadOrderException, NotImplementedException {
        ftp.processRequest("STOR name");
        Mockito.verify(ftp).processStor("name");
    }

    @Test
    public void testProcessRequestList() throws IOException, BadOrderException, NotImplementedException {
        ftp.processRequest("LIST");
        Mockito.verify(ftp).processList();
    }

    @Test
    public void testProcessRequestQuit() throws IOException {
        ftp.processRequest("QUIT");
        Mockito.verify(ftp).processQuit();
    }

    @Test
    public void testProcessRequestUnkownCMDError() throws IOException {
        ftp.processRequest("Coucou");
        Mockito.verify(ftp).sendMessage("500 unkown command");
        // erreur 500
        ;
    }

    @Test(expected=NotImplementedException.class)
    public void testProcessUserFailBecauseNotImplemented() throws NotImplementedException{
        Mockito.when(command.userExist("ilias")).thenThrow(NotImplementedException.class);
        final String rep = ftp.processUser("ilias");
        assertTrue(rep.startsWith("331"));
    }



}