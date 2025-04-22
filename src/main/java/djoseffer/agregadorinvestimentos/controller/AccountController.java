package djoseffer.agregadorinvestimentos.controller;

import djoseffer.agregadorinvestimentos.controller.dto.AccountStockDto;
import djoseffer.agregadorinvestimentos.controller.dto.AccountStockResponseDto;
import djoseffer.agregadorinvestimentos.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId")  String accountId, @RequestBody AccountStockDto accountStockDto) {
        accountService.associateStock(accountId, accountStockDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> associateStock(@PathVariable("accountId")  String accountId) {
        var stocks = accountService.listStocks(accountId);
        return ResponseEntity.ok(stocks);
    }

}
