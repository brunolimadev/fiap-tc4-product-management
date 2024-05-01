package br.com.fiap.productmanagement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {

    this.productService = productService;

  }

  @PostMapping("upload")
  public String upload(@RequestParam("file")MultipartFile file) throws Exception {

    productService.uploadFile(file);

    return "Processando arquivo...";

  }

}