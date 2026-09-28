package se331.lab10auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab10auction.dao.AuctionItemDao;
import se331.lab10auction.entity.AuctionItem;

@Service
@RequiredArgsConstructor
public class AuctionItemServiceImpl implements AuctionItemService {
    final AuctionItemDao auctionItemDao;

    @Override
    public Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page) {
        return auctionItemDao.getAuctionItems(pageSize, page);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable) {
        return auctionItemDao.getAuctionItemsByDescription(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageable) {
        return auctionItemDao.getAuctionItemsBySuccessfulBidLessThan(amount, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByKeyword(String keyword, Pageable pageable) {
        return auctionItemDao.getAuctionItemsByKeyword(keyword, pageable);
    }

    @Override
    public AuctionItem getAuctionItem(Long id) {
        return auctionItemDao.getAuctionItem(id);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem) {
        return auctionItemDao.save(auctionItem);
    }
}