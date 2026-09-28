package se331.lab10auction.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab10auction.entity.AuctionItem;

public interface AuctionItemDao {
    Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page);
    Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable);
    Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageable);
    AuctionItem getAuctionItem(Long id);
    AuctionItem save(AuctionItem auctionItem);
    Page<AuctionItem> getAuctionItemsByKeyword(String keyword, Pageable pageable);
}