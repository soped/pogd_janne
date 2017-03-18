package dk.mudlogic.connection;

import dk.mudlogic.tools.log.LogFactory;
import dk.mudlogic.tools.log.LogTracer;

import java.io.*;
import java.net.Socket;

/**
 * Created by soren.pedersen on 21-02-2017.
 */
public class TcpConnection {

    private LogTracer log = new LogFactory().tracer();

    private String hostname;
    private int port;

    private Socket connection;
    private OutputStream stream_out;
    private InputStream stream_in;
    private PrintWriter writer;
    private BufferedReader reader;

    public TcpConnection(String hostname,int port) {
        log.setTracerTitle(TcpConnection.class);

        this.hostname   = hostname;
        this.port       = port;

        if (init_socket()) {
            if (init_streams()) {
                init_readwrite();
            }
        }
    }

    private boolean init_socket() {
        log.info("Connecting " + hostname+":"+port);

        try {
            connection = new Socket(hostname,port);
            return true;
        }
        catch(IOException e) {
            log.critical("Connection failed");
            log.critical( e.getMessage() );

            return false;
        }
    }

    private boolean init_streams() {
        log.info("Open IO streams");

        try {
            stream_out = connection.getOutputStream();
            stream_in = connection.getInputStream();

            return true;
        }
        catch(IOException e) {
            log.critical("IO streams failed");
            log.critical( e.getMessage() );

            return false;
        }
    }

    private void init_readwrite() {
        log.info("Open read/write");

        writer  = new PrintWriter(stream_out);
        reader  = new BufferedReader(new InputStreamReader(stream_in));
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public void writeln(String s) {
        writer.println(s);
        writer.flush();
    }

    public String readln() throws IOException {
        return reader.readLine();
    }

    public void close() {
        log.info("Closing connection");

        try {
            writer.close();
            reader.close();
            stream_in.close();
            stream_out.close();
            connection.close();
        }
        catch(Exception e) {
            log.critical("Error while closing connection");
            log.critical(e.getMessage());
        }
    }
}