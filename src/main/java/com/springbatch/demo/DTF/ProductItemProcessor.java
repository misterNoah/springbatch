package com.springbatch.demo.DTF;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
/**
 * 把读取的数据进行处理
 */
@Component("DTF_productProcessor")
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product product) throws Exception {
    	String[] names = product.getName().split("\\+");
    	String name = names[0];
        product.setName(name + " + DTF");
        System.out.println("=====处理完成::" + product.toString());
        return product;
    }
}
