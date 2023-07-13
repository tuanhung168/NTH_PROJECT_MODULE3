package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.ProducerController;
import ra.controller.ProductControl;
import ra.model.Producer;
import ra.model.Product;

import java.util.List;

public class ProductsManagement {
    ProductControl productControl = new ProductControl();
    ProducerController producerController = new ProducerController();
    List<Product> productList = productControl.getProducts();
    List<Producer> producerList = producerController.getListProducer();

    public void productView() {
        while (true) {
            System.out.println("************* \033[1;35mQuản lý sản phẩm\033[0m ******************");
            System.out.println("*               \033[1;32m1. Thêm mới xe\033[0m                  *");
            System.out.println("*               \033[1;33m2. Hiển thị danh sách xe\033[0m        *");
            System.out.println("*               \033[1;36m3. Sửa thông tin xe\033[0m             *");
            System.out.println("*               \033[1;34m4. Xóa xe\033[0m                       *");
            System.out.println("*               \033[1;31m5. Trang quản lý hãng xe\033[0m        *");
            System.out.println("*               \033[1;35m6. Tìm kiếm xe theo tên\033[0m         *");
            System.out.println("*               \033[1;32m7. Trở về Admin\033[0m                 *");
            System.out.println("*************************************************");
            System.out.println("Nhập chức năng:");

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    showListProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    new  ProducersManagement().productView();
                    break;
                case 6:
                    searchProductByName();
                    break;
                case 7:
                    new AdminView().adminView();
                    break;
                default:
                    System.out.println("Nhập sai chức năng");
            }
        }
    }


    public void showListProducts() {
        for (Product product : productList) {
            product.displayDataProduct();
        }
    }

    public void createProduct() {
        System.out.println("Nhập số sản phẩm cần thêm mới : ");
        int quantity = InputMethods.getInteger();
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập sản phẩm thú : " + (i + 1));
            Product product = new Product();
            if (productList.size() == 0) {
                product.setProductId(1);
            } else {
                product.setProductId(productList.get(productList.size() - 1).getProductId() + 1);
            }
            product.inputDataProduct(producerList);
            productControl.createProduct(product);
        }
        System.out.println("Thêm mới sản phẩm thành công!");
    }

    public void updateProduct() {
        showListProducts();
        Product product = new Product();
        System.out.println("Nhập mã sản phẩm cần sửa :");
        int id = InputMethods.getInteger();
        if (productControl.findById(id) == null) {
            System.err.println("Sản phẩm không tồn tại");
        } else {
            product.setProductId(id);
            System.out.println("Nhập tên mới");
            product.setProductName(InputMethods.getString());
            new ProducersManagement().showListProducer();
            int id1;
            boolean flag = false;
            while(!flag) {
                System.out.println("Nhập mã hãng xe:");
                id1 = InputMethods.getInteger();
                for (Producer producer1 : producerList) {
                    if (producer1.getProducerId() == id1) {
                        product.setProducer(producer1);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.err.println("Không có danh mục với mã '" + id1 + "'. Vui lòng nhập lại.");
                }
            }
            System.out.println("Nhập thông tin thêm mới");
            product.setTitle(InputMethods.getString());
            System.out.println("Nhập giá mới");
            product.setPrice(InputMethods.getFloat());
            System.out.println("Nhập trạng thái mới");
            System.out.println("1.Đang bán           2.Không bán");
            int choice = InputMethods.getInteger();
            if (choice==1){
                product.setProductStatus(true);
            }else {
                product.setProductStatus(false);
            }
            productControl.updateProduct(product);
            System.out.println("Sửa thành công");
        }
    }

    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm:");
        String productName = InputMethods.getString();
        boolean found = false;

        for (Product product : productList) {
            if (product.getProductName().trim().toLowerCase().contains(productName.toLowerCase())) {
                product.displayDataProduct();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm với tên '" + productName + "'.");
        }
    }

    public void deleteProduct() {
        showListProducts();
        System.out.println("Chọn mã sản phẩm cần xóa :");
        int id = InputMethods.getInteger();
        productControl.deleteProduct(id);
        System.out.println();
    }

}
