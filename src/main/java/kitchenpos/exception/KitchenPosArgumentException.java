package kitchenpos.exception;

public abstract class KitchenPosArgumentException extends RuntimeException{
    public KitchenPosArgumentException(String s) {
        super(s);
    }
}
