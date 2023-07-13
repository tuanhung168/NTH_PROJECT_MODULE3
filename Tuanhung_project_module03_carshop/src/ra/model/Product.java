package ra.model;

import ra.config.Config;
import ra.config.InputMethods;
import ra.model.Producer;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private Producer producer;
    private String title;
    private float price;
    private boolean productStatus;

    public Product() {
    }

    public Product(int productId, String productName, Producer producer,String title ,float price, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.producer = producer;
        this.price = price;
        this.productStatus = productStatus;
        this.title = title;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void inputDataProduct(List<Producer> list) {
        System.out.println("Nhập tên xe :");
        setProductName(InputMethods.getString());
        for (Producer producer1 : list) {
            producer1.displayProducer();
        }
        int id;
        boolean flag = false;
        while(!flag) {
            System.out.println("Nhập mã hãng xe:");
            id = InputMethods.getInteger();
            for (Producer producer1 : list) {
                if (producer1.getProducerId() == id) {
                    setProducer(producer1);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.err.println("Không có danh mục với mã '" + id + "'. Vui lòng nhập lại.");
            }
        }
        System.out.println("Nhập thông tin thêm của xe :");
        setTitle(InputMethods.getString());
        System.out.println("Nhập giá xe :");
        setPrice(InputMethods.getInteger());
        System.out.println("Nhập trạng thái xe :");
        System.out.println("1.Đang bán           2.Không bán");
        int choice = InputMethods.getInteger();
        if (choice==1){
            setProductStatus(true);
        }else {
            setProductStatus(false);
        }
    }

    public void displayDataProduct() {
        System.out.println("-------------------------------------------------");
        System.out.printf(" Mã xe             : %d \n", productId);
        System.out.printf(" Tên xe            : %s \n", productName);
        System.out.printf(" Hãng sản xuất     : %s \n", producer.getProducerName());
        System.out.printf(" Quốc gia sản xuất : %s \n", producer.getProducerCountry());
        System.out.printf(" Thông tin thêm    : %s \n", title);
        System.out.printf(" Giá xe            : %.1f \n", price);
        System.out.println(" Trạng thái xe    :" + (isProductStatus() ? "Còn xe" : "hết xe" + "\n"));
        System.out.println("-------------------------------------------------");
    }

}
