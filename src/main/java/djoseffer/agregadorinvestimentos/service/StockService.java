package djoseffer.agregadorinvestimentos.service;

import djoseffer.agregadorinvestimentos.controller.dto.CreateStockDto;
import djoseffer.agregadorinvestimentos.entity.Stock;
import djoseffer.agregadorinvestimentos.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(createStockDto.stockId(), createStockDto.description());

        stockRepository.save(stock);
    }
}
