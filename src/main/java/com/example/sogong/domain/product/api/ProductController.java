package com.example.sogong.domain.product.api;

import com.example.sogong.domain.product.dto.CreationProductReq;
import com.example.sogong.domain.product.dto.GetProductRes;
import com.example.sogong.domain.product.service.ProductService;
import com.example.sogong.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> productAdd(@RequestBody CreationProductReq request) {
        productService.saveProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.noContent());
    }

    @GetMapping("")
    public ResponseEntity<?> productList() {
        List<GetProductRes> res = productService.findAll();
        return res.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(SuccessResponse.from(res));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> productDetails(@PathVariable Long id) {
        Optional<GetProductRes> res = productService.findProductById(id);
        return res.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(SuccessResponse.from(res.get()));
    }

//    @PutMapping("/{id}") // PUT PATCH
//    public ResponseEntity<?> productModify(@PathVariable Long id, @RequestBody CreationProductReq request) {
//        Optional<GetProductRes> res = productService.modifyProduct(id, request);
//        return res.isPresent() ? ResponseEntity.ok(success(res.get())) : ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> productRemove(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.noContent());
    }
}
