package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.ProductManager;
import individual.individualsem3backend.business.converters.ProductEntityConverter;
import individual.individualsem3backend.business.exception.ProductException;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductManagerImpl implements ProductManager {

    private ProductRepository productRepository;

    @Override
    public List<Product>  getProducts() {
        try{
            return ProductEntityConverter.listOfProductEntitiesConvertedToListOfProducts(productRepository.findAll());
        }
        catch(Exception ex){
            throw new ProductException("Something went wrong with getting all the products.");
        }
    }

    @Override
    public Product createProduct(Product request) {
        if(request != null){
            Product newProduct = Product.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .color(request.getColor())
                    .build();

            return ProductEntityConverter.productEntityConvertedToProduct(productRepository.save(ProductEntityConverter.productConvertedToProductEntity(newProduct)));
        }
        else{
            throw new ProductException("Something went wrong while creating product.");
        }
    }


    @Override
    public void deleteProduct(int productId) {
        if(productId > -1){
            this.productRepository.deleteById(productId);
        }
        else{
            throw new ProductException("Cannot find product with negative id.");
        }
    }

    //change so it checks if its null or not
    @Override
    public Optional<Product> getProduct(int productId) {
       if(productId > -1){
           return Optional.ofNullable(ProductEntityConverter.productEntityConvertedToProduct(productRepository.findById(productId).get()));
       }
       else{
           throw new ProductException("Cannot find product with negative id.");
       }
    }

    @Override
    public void updateProduct(Product request) {
        if(request != null && request.getId() > -1){
            Product product = ProductEntityConverter.productEntityConvertedToProduct(productRepository.findById(request.getId()).get());

            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setColor(request.getColor());
            product.setDescription(request.getDescription());

            productRepository.save(ProductEntityConverter.productConvertedToProductEntity(product));
        }
        else{
            throw new ProductException("Something went wrong while updating product.");
        }

    }


}
