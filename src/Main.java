public class Main extends Thread{
    public static void main(String[] args) throws InterruptedException {
        Inicio_login il = new Inicio_login();
        HiloServidor hs = new HiloServidor();
        il.start();

    }
}
