package dk.mudlogic.connection;

import dk.mudlogic.tools.log.LogFactory;
import dk.mudlogic.tools.log.LogTracer;

/**
 * Created by soren.pedersen on 22-02-2017.
 */
public class Connector extends Connector_Interface {

    private LogTracer log = new LogFactory(Connector.class.toString()).tracer();

    public Connector(String host,int port) {
        super(host,port);
    }

    @Override
    void readIn(String message) {
        log.trace("<< " + message);
    }

    public void write(String s) {
        this.writeOut(s);
    }

    public void close() {
        this.close_conn();
    }
}
