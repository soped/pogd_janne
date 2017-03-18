package dk.mudlogic.connection;

/**
 * Created by soren.pedersen on 22-02-2017.
 */
abstract class Connector_Interface {

    private TcpConnection conn;

    public Connector_Interface(String host,int port) {
        conn = new TcpConnection(host,port);

        ConnectionReader cr = new ConnectionReader(conn,this);
        Thread t = new Thread(cr);
        t.start();
    }

    abstract void readIn(String message);

    public void writeOut(String s) {
        conn.writeln(s);
    }

    public void close_conn() {
        conn.close();
    }

    class ConnectionReader implements Runnable {

        private TcpConnection conn;
        private Connector_Interface callback;

        public ConnectionReader(TcpConnection conn,Connector_Interface callback) {
            this.conn = conn;
            this.callback = callback;
        }

        @Override
        public void run() {

            try {
                String s = conn.readln();
                while (s != null) {      // readLine() giver null når datastrømmen lukkes
                    //System.out.println("modt: "+s);

                    callback.readIn(s);
                    s = conn.readln();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
