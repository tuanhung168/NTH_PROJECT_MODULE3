package ra.model;

import ra.config.Config;
import ra.config.InputMethods;

import java.io.Serializable;

public class Producer implements Serializable {
    private int producerId;
    private String producerName;
    private String producerCountry;

    public Producer() {
    }

    public Producer(int producerId, String producerName, String producerCountry) {
        this.producerId = producerId;
        this.producerName = producerName;
        this.producerCountry = producerCountry;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public void inputDataProducer() {
        System.out.println("Nhập tên Hãng sản xuất");
        String producerName = InputMethods.getString();
        setProducerName(producerName);
        System.out.println("Nhập quốc gia sản xuất");
        String producerCountry = InputMethods.getString();
        setProducerCountry(producerCountry);
    }

    public void displayProducer() {
        System.out.println("-----------------------------------");
        System.out.printf("Mã hãng xe : %d \n", getProducerId());
        System.out.printf("Hãng xe    : %s \n", getProducerName());
        System.out.printf("Quốc gia   : %s \n", getProducerCountry());
        System.out.println("-----------------------------------");
    }
}
