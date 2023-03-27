package com.ntloc.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO dto);

    List<Product> toListProduct(List<ProductDTO> listProductDTO);

    ProductDTO toProductDTO(Product Product);

    List<ProductDTO> toListProductDTO(List<Product> listProduct);
}
