package HomeWork6;

import HomeWork6.Workers.Loader;
import HomeWork6.Workers.Transporter;
import HomeWork6.Workers.Unloader;

public class Main {
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        new Loader(cart);
        new Transporter(cart);
        new Unloader(cart);
    }
}
