public class GiftBoxDecorator extends PizzaDecorator {
    GiftBoxDecorator(PizzaOrder wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Caja Regalo";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + 3.00;
    }
}
