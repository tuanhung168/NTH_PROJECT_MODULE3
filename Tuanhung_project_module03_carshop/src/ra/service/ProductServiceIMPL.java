package ra.service;

import ra.config.Config;
import ra.model.Product;

import java.util.List;

public class ProductServiceIMPL implements IProductService {
    List<Product> productList = new Config<Product>().readFormFile(Config.PATH_PRODUCT);
    @Override
    public List<Product> fileAll() {
        return productList;
    }
    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null){
            productList.add(product);
        } else {
            productList.set(productList.indexOf(findById(product.getProductId())),product );
        }
        new Config<Product>().writeFormFile(Config.PATH_PRODUCT,productList);
    }
    @Override
    public Product findById(int id) {
        for (Product product: productList) {
            if (id == product.getProductId()){
                return product;
            }
        }
        return null;
    }
    @Override
    public void deleteById(int id) {
        for (Product product:productList) {
//            System.out.println(product.getProductId());
//            System.out.println(product.getProductId()==id);
            if (product.getProductId() == id){
                productList.remove(product);
                System.out.println("Xóa thành công!");
                new Config<Product>().writeFormFile(Config.PATH_PRODUCT,productList);
                break;
            }
        }

    }
}
