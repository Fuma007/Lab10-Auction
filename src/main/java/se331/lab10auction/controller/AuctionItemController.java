package se331.lab10auction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab10auction.entity.AuctionItem;
import se331.lab10auction.service.AuctionItemService;
import se331.lab10auction.util.AuctionMapper;

@RestController
@RequiredArgsConstructor
public class AuctionItemController {
    final AuctionItemService auctionItemService;

    @GetMapping("/auctionitems")
    public ResponseEntity<?> getAuctionItems(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "maxSuccessfulBid", required = false) Double maxSuccessfulBid) {

        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;

        Page<AuctionItem> pageOutput;
        if (keyword != null && !keyword.isEmpty()) {
            pageOutput = auctionItemService.getAuctionItemsByKeyword(keyword, PageRequest.of(page - 1, perPage));
        } else if (maxSuccessfulBid != null) {
            pageOutput = auctionItemService.getAuctionItemsBySuccessfulBidLessThan(maxSuccessfulBid, PageRequest.of(page - 1, perPage));
        } else {
            pageOutput = auctionItemService.getAuctionItems(perPage, page);
        }

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(AuctionMapper.INSTANCE.getAuctionItemDto(pageOutput.getContent()),
                responseHeader, HttpStatus.OK);
    }

    @GetMapping("/auctionitems/{id}")
    public ResponseEntity<?> getAuctionItem(@PathVariable("id") Long id) {
        AuctionItem output = auctionItemService.getAuctionItem(id);
        if (output != null) {
            return ResponseEntity.ok(AuctionMapper.INSTANCE.getAuctionItemDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}