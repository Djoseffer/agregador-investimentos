package djoseffer.agregadorinvestimentos.controller;

import djoseffer.agregadorinvestimentos.controller.dto.CreateAccountDto;
import djoseffer.agregadorinvestimentos.controller.dto.CreateStockDto;
import djoseffer.agregadorinvestimentos.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stocks")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto) {
        stockService.createStock(createStockDto);
        return ResponseEntity.ok().build();
    }
}
