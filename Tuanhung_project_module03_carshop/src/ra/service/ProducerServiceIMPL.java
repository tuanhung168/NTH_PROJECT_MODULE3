package ra.service;

import ra.config.Config;
import ra.model.Producer;

import java.util.List;

public class ProducerServiceIMPL implements IProducerService{
    List<Producer> producerList = new Config<Producer>().readFormFile(Config.PATH_PRODUCER);
    @Override
    public List<Producer> fileAll() {
        return producerList;
    }

    @Override
    public void save(Producer producer) {
        if (findById(producer.getProducerId()) == null){
            producerList.add(producer);
        } else {
            producerList.set(producerList.indexOf(findById(producer.getProducerId())),producer);
        }
        new Config<Producer>().writeFormFile(Config.PATH_PRODUCER,producerList);
    }

    @Override
    public Producer findById(int id) {
        for (Producer producer:producerList) {
            if (producer.getProducerId() == id){
                return producer;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Producer producer:producerList) {
            if (producer.getProducerId() == id){
                producerList.remove(producer);
                new Config<Producer>().writeFormFile(Config.PATH_PRODUCER,producerList);
                System.out.println("Xóa thành công!");
                break;
            }
        }
    }
}
