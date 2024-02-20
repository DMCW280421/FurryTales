package utility;

public interface CartItemListener {
    void onIncreaseClicked(int cartID);
    void onDecreaseClicked(int cartID);
    void onDeleteClicked(int cartID);
}
