package ra.view;

import ra.config.Config;
import ra.model.Bill;

import java.util.List;

public class PayHistory {

    public void payHistory(){
        List<Bill> list = new Config<Bill>().readFormFile(Config.PATH_PAY_HISTORY);
        for (Bill bill: list) {
            bill.displayBill();
        }
    }
}
