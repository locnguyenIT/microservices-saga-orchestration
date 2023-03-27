package com.ntloc.product;

import com.ntloc.product.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.product.ProductConstant.MessagesConstant.PRODUCT_WAS_NOT_FOUND;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<Product> listProduct = productRepository.findAll();
        return productMapper.toListProductDTO(listProduct);
    }

    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(PRODUCT_WAS_NOT_FOUND));
        return productMapper.toProductDTO(product);
    }

}
