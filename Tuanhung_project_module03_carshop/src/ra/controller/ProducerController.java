package ra.controller;

import ra.model.Producer;
import ra.service.IProducerService;
import ra.service.ProducerServiceIMPL;

import java.util.List;

public class ProducerController {
    IProducerService producerService = new ProducerServiceIMPL();
    public List<Producer> getListProducer(){
        return   producerService.fileAll();
    }
    public void createProducer(Producer producer){
        producerService.save(producer);
    }
    public void updateProducer(Producer producer){
        producerService.save(producer);
    }
    public void deleteProducer(int id){
        producerService.deleteById(id);
    }
    public Producer findById(int id){
        return producerService.findById(id);
    }
}
