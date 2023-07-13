package ra.service;

import ra.model.CartItem;
import ra.service.IGeneric;

public interface ICartItemService extends IGeneric<CartItem> {
    void payment();
}
