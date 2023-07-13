package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.ProducerController;
import ra.model.Producer;
import ra.model.Product;

import java.util.List;

public class ProducersManagement {
    ProducerController producerController = new ProducerController();
    List<Producer> producerList = producerController.getListProducer();

    public void productView() {
        while (true) {
            System.out.println("******************* \033[1;35mQuản lý hãng xe\033[0m ********************");
            System.out.println("*           \033[1;32m1. Thêm hãng sản xuất mới\033[0m                 *");
            System.out.println("*           \033[1;33m2. Hiển thị danh mục hãng sản xuất\033[0m        *");
            System.out.println("*           \033[1;36m3. Thay đổi thông tin hãng sản xuất\033[0m       *");
            System.out.println("*           \033[1;34m4. Xóa hãng sản xuất\033[0m                      *");
            System.out.println("*           \033[1;31m5. Trở về trang admin\033[0m                     *");
            System.out.println("*           \033[1;35m6. Tìm kiếm hãng sản xuất theo tên\033[0m        *");
            System.out.println("********************************************************");
            System.out.println("Nhập để chọn chức năng:");

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    createProducer();
                    break;
                case 2:
                    showListProducer();
                    break;
                case 3:
                    updateProducer();
                    break;
                case 4:
                    deleteProducer();
                    break;
                case 5:
                    new AdminView().adminView();
                    break;
                case 6:
                    searchProducerByName();
                    break;
                default:
                    System.err.println("Chọn không chính xác!");
            }
        }
    }

    public void createProducer() {
        System.out.println("Nhập số hãng xe cần thêm mới :");
        int side = InputMethods.getInteger();
        for (int i = 0; i < side; i++) {
            Producer producer = new Producer();
            if (producerList.size() == 0) {
                producer.setProducerId(1);
            } else {
                producer.setProducerId(producerList.get(producerList.size() - 1).getProducerId() + 1);
            }
            producer.inputDataProducer();
            producerController.createProducer(producer);
        }
        System.out.println("Thêm mới hãng xe thành công");
    }

    public void showListProducer() {
        for (Producer producer : producerList) {
            producer.displayProducer();
        }
    }

    public void updateProducer() {
        Producer producer = new Producer();
        showListProducer();
        System.out.println("Nhập mã hãng xe cần thay đổi thông tin");
        int idUpdate = InputMethods.getInteger();
        if (producerController.findById(idUpdate) == null) {
            System.err.println("Hãng xe không tồn tại!");
        } else {
            producer.setProducerId(idUpdate);
            System.out.println("Nhập lại tên hãng xe");
            producer.setProducerName(InputMethods.getString());
            System.out.println("Nhập lại quốc gia");
            producer.setProducerCountry(InputMethods.getString());
            producerController.updateProducer(producer);
            System.out.println("Thay đổi thông tin thành công");
        }

    }

    public void deleteProducer() {
        showListProducer();
        System.out.println("Nhập mã hãng xe cần xóa");
        int idDel = InputMethods.getInteger();
        producerController.deleteProducer(idDel);
    }
    public void searchProducerByName() {
        System.out.println("Nhập tên hãng xe cần tìm kiếm:");
        String producerName = InputMethods.getString();
        boolean found = false;

        for (Producer producer : producerList) {
            if (producer.getProducerName().trim().contains(producerName.trim())) {
                producer.displayProducer();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy hãng xe với tên '" + producerName + "'.");
        }
    }

}
