package com.springbatch.demo.FTF;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("FTF_productProcessor")
public class ProductItemProcessor implements ItemProcessor<Product, Product>{

	@Override
	public Product process(Product product) throws Exception {
		product.setId(product.getId() + 2);
		product.setName(product.getName() + " + FTF");
		System.out.println("===处理完成：" + product.toString());
		return product;
	}

}
