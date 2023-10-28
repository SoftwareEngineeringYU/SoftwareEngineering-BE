package com.example.sogong.domain.product.api;

import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import com.example.sogong.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.OK);
    }

    // 상품 전체 반환
    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,asc") String sort
    ) {
        return new ResponseEntity<>(productService.getAllProduct(page, size, sort), HttpStatus.OK);
    }

    // 상품 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    // 상품 갱신
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequestDto), HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
//=======
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.sogong.global.common.response.ApiResponse.success;
//import static com.example.sogong.global.common.response.ApiResponse.successWithNoContent;
//
//@RestController
//@RequestMapping("/api/v1/products")
//@RequiredArgsConstructor
//@Slf4j
//public class ProductController {
//    private final ProductService productService;
//
//    @PostMapping("")
//    public ResponseEntity<?> productAdd(@RequestBody CreationProductReq request) {
//        productService.saveProduct(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(successWithNoContent());
//    }
//
//    @GetMapping("")
//    public ResponseEntity<?> productList() {
//        List<GetProductRes> res = productService.findAll();
//        return res.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(success(res));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> productDetails(@PathVariable Long id) {
//        Optional<GetProductRes> res = productService.findProductById(id);
//        return res.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(success(res.get()));
//    }
//
////    @PutMapping("/{id}") // PUT PATCH
////    public ResponseEntity<?> productModify(@PathVariable Long id, @RequestBody CreationProductReq request) {
////        Optional<GetProductRes> res = productService.modifyProduct(id, request);
////        return res.isPresent() ? ResponseEntity.ok(success(res.get())) : ResponseEntity.noContent().build();
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> productRemove(@PathVariable Long id) {
//        productService.removeProduct(id);
//        return ResponseEntity.status(HttpStatus.OK).body(successWithNoContent());
//>>>>>>> dev
    }
}
